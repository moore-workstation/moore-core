package com.moore.core.security.exceptions;

import com.baomidou.mybatisplus.core.toolkit.StringPool;

/**
 * @author ：imoore
 * @date ：created in 2023/1/13 20:36
 * @description：数据异常
 * @version: v
 */
public class DataException extends ServiceException {
    private String code;
    private String message;

    public DataException(String code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public DataException(String message) {
        super(message);
        this.message = message;
    }
}
