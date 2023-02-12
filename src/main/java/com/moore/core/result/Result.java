package com.moore.core.result;

import cn.dev33.satoken.exception.SaTokenException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author ：imoore
 * @date ：created in 2022/10/24 18:01
 * @description：共通返回
 * @version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 成功
     */
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer SUCCESS_STATE = 200;
    private static final String SUCCESS_MESSAGE = "SUCCESS";
    /**
     * 失败
     */
    private static final Integer FAILURE_CODE = 400;
    private static final Integer FAILURE_STATE = 500;
    private static final String FAILURE_MESSAGE = "FAIL";

    private int code;
    private int state;
    private String message;
    private T data;

    public static Result success() {
        return new Result(SUCCESS_CODE, SUCCESS_STATE, SUCCESS_MESSAGE, null);
    }

    public static Result success(String message) {
        return new Result(SUCCESS_CODE, SUCCESS_STATE, message, null);
    }

    public static <T> Result data(T data) {
        return new Result(SUCCESS_CODE, SUCCESS_STATE, SUCCESS_MESSAGE, data);
    }

    public static <T> Result data(String message, T data) {
        return new Result(SUCCESS_CODE, SUCCESS_STATE, message, data);
    }

    public static Result fail() {
        return new Result(FAILURE_CODE, FAILURE_STATE, FAILURE_MESSAGE, null);
    }

    public static Result fail(String message) {
        return new Result(FAILURE_CODE, FAILURE_STATE, message, null);
    }

    public static <T extends Exception> Result fail(T e) {
        return new Result(FAILURE_CODE, FAILURE_STATE, e.getMessage(), null);
    }

//    登陆异常时，抛出登陆错误异常代码
    public static <T extends SaTokenException> Result loginFail(T e) {
        return new Result(HttpStatus.UNAUTHORIZED.value(), FAILURE_STATE, e.getMessage(), null);
    }
}
