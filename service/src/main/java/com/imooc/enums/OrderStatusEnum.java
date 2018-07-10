package com.imooc.enums;

import lombok.Getter;

/**
 * Created by XuQin on 2018/7/5.
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新下单"),
    FINISHED(1, "已完结"),
    CANCEL(2, "已取消");

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
