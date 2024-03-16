package com.dly.result;

import com.dly.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;//状态码
    private String msg;//消息
    private T data;//数据


    //    public static <T> Result<T> success() {
//        Result<T> result = new Result<T>();
//        result.code = "200";
//        return result;
//    }
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 200;
        return result;
    }


    //    public static <T> Result<T> success(T object) {
//        Result<T> result = new Result<T>();
//        result.setCode("200");
//        result.setData(object);
//        result.setMsg("操作成功");
//        return result;
//    }
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 200;
        return result;
    }

//    public static <T> Result<T> error(String msg) {
//        Result<T> result = new Result<T>();
//        result.setCode(ResultCodeEnum.PARAM_FORMAT_ERROR.getCode());
//        result.setData(null);
//        result.setMsg(msg);
//        return result;
//
//    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }
}
