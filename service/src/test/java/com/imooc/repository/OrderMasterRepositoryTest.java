package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.utils.KeyUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by XuQin on 2018/7/5.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void create(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("樟坑二村");
        orderMaster.setBuyerName("kay");
        orderMaster.setBuyerOpenid("1111111111100000000001");
        orderMaster.setBuyerPhone("18903030033");
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setOrderAmount(new BigDecimal(1));
        orderMaster.setOrderId(KeyUtils.getUniqueId("order"));
        OrderMaster master = orderMasterRepository.save(orderMaster);
        String order = KeyUtils.getUniqueId("order");
        System.out.println(order);
        Assert.assertTrue(master != null);
    }


}