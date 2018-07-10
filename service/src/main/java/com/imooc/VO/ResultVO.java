package com.imooc.VO;

import com.imooc.enums.ResultEnum;
import lombok.Data;

/**
 * Created by XuQin on 2018/7/5.
 */
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO() {
    }

    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        resultVO.setData(o);
        return resultVO;
    }

    public ResultVO(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }
}
