package com.moore.core.security;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.moore.core.systems.properties.GlobalSystemProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：imoore
 * @date ：created in 2022/10/31 18:07
 * 访问控制
 * @version: v1.0
 */
@Component
@Aspect
@Slf4j
public class ControllerHandler {

    private final GlobalSystemProperties properties;

    public ControllerHandler(GlobalSystemProperties properties) {
        this.properties = properties;
    }


    @Pointcut("execution(public * com.moore..*.controller.*Controller.*(..))")
    public void controllerPointCutHandle() {
    }

    @Before("controllerPointCutHandle()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        log.info("------------- 开始 -------------");
        log.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
//        log.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
//        log.info("远程地址: {}", request.getRemoteAddr());

        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }

        if (!properties.getLogPrintExcludePattern().isEmpty()) {
            //过滤字段 不将字段打印到控制台中
            SimplePropertyPreFilter preFilter = new SimplePropertyPreFilter();
            preFilter.getExcludes().addAll(properties.getLogPrintExcludePattern());
            log.info("请求参数: {}", JSON.toJSONString(arguments, preFilter));
        } else {
            log.info("请求参数: {}", JSON.toJSONString(arguments));
        }
    }

    @Around("controllerPointCutHandle()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        if (!properties.getLogPrintExcludePattern().isEmpty()) {
            //过滤字段 不将字段打印到控制台中
            SimplePropertyPreFilter preFilter = new SimplePropertyPreFilter();
            preFilter.getExcludes().addAll(properties.getLogPrintExcludePattern());
            log.info("返回结果: {}", JSON.toJSONString(result, preFilter));
        } else {
            log.info("返回结果: {}", JSON.toJSONString(result));
        }

        log.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
