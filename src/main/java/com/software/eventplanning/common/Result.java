package com.software.eventplanning.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Object data;


    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    public static Result success(String msg, Object data) {
        return new Result(Constants.CODE_200, msg, data);
    }

    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
