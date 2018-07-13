package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * Created by XuQin on 2018/7/5.
 */
public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    /**
     * 订单完单
     * @param orderId
     * @return
     */
    OrderDTO finish(String orderId);
}
