package com.dly.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Integer code;//状态码：1成功，0和其他失败
    private String msg;//消息
    private T data;//数据


    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }


    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.code = 1;
        result.data = object;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = msg;
        return result;

    }
}
