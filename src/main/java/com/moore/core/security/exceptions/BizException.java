package com.moore.core.security.exceptions;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


/**
 * @author ：imoore
 * @date ：created in 2022/10/24 18:01
 * @description：业务异常
 * @version: v1.0
 */
@Getter
public class BizException extends ServiceException {
    private String code;
    private String message;

    public BizException(String code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public BizException(String message) {
        this.message = message;
    }
}
