package com.moore.core.common;

import lombok.Getter;

/**
 * @author ：imoore
 * @date ：created in 2022/10/27 20:11
 * @description：系统异常类型
 * @version: v
 */
@Getter

public enum CoreErrorType {
    SERVER_ERROR("5001","服务异常"),
    SYSTEM_ERROR("5002","系统异常"),
    BUSINESS_ERROR("5003","业务异常");
    private String code;
    private String name;

    CoreErrorType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
