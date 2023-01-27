package com.moore.core.security;

import cn.dev33.satoken.exception.SaTokenException;
import com.moore.core.common.CoreErrorType;
import com.moore.core.result.Result;
import com.moore.core.security.exceptions.BizException;
import com.moore.core.security.exceptions.ServiceException;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：imoore
 * @date ：created in 2022/10/24 19:11
 * @description：共通异常捕获
 * @version: v1.0
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result resultHandle(Exception e) {
        if (e instanceof BizException) {
            log.error(CoreErrorType.SERVER_ERROR.getName(),e);
            return Result.fail(e);
        }
        if(e instanceof ServiceException){
            log.error(CoreErrorType.SERVER_ERROR.getName(),e);
            return Result.fail(e);
        }
        if(e instanceof SaTokenException){
            log.error(CoreErrorType.SERVER_ERROR.getName(),e);
            return Result.loginFail((SaTokenException) e);
        }
        log.error(CoreErrorType.SYSTEM_ERROR.getName(),e);

        return Result.fail(CoreErrorType.SERVER_ERROR.getName());
    }
}
