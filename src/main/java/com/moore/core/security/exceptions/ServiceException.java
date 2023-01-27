package com.moore.core.security.exceptions;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Getter;

/**
 * @author ：imoore
 * @date ：created in 2022/10/24 18:00
 * @description：服务异常类
 * @version: v1.0
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;
    private String message;

    public ServiceException() {
        super();
    }

    public ServiceException(String code, String message) {
        super(code + StringPool.COLON + message);
    }

    public ServiceException(String code) {
        this.code = code;
    }
}
