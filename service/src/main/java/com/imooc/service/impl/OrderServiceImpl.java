package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.OrderException;
import com.imooc.product.DecreaseStockInput;
import com.imooc.product.ProductClient;
import com.imooc.product.ProductInfoOutput;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.OrderService;
import com.imooc.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by XuQin on 2018/7/5.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtils.getUniqueId("o");

        //订单详情
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> productIds = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<ProductInfoOutput> productInfoList = productClient.findByProductIdIn(productIds);

        for (ProductInfoOutput product : productInfoList) {
            for (OrderDetail orderDetail : orderDetailList) {
                if (product.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = product.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(product, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtils.getUniqueId("od"));
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //库存
        List<DecreaseStockInput> cartDTOS = orderDetailList
                .stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productClient.decreaseStock(cartDTOS);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO finish(String orderId) {
        Optional<OrderMaster> orderOptional = orderMasterRepository.findById(orderId);
        if (!orderOptional.isPresent()){
            throw new OrderException(ResultEnum.ORDER_NOT_EXIT);
        }
        OrderMaster orderMaster = orderOptional.get();
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //查询订单详情信息
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_ERROR);
        }

        //将订单状态调整为已完成
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
