package com.imooc.enums;

import lombok.Getter;

/**
 * Created by XuQin on 2018/7/5.
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),
    ORDER_NOT_EXIT(3, "订单不存在"),
    ORDER_STATUS_ERROR(4, "订单状态异常"),
    ORDER_DETAIL_ERROR(5, "订单异常");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
