package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * Created by XuQin on 2018/7/5.
 */
public class OrderException extends RuntimeException{
    private Integer code;

    public OrderException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
