package com.moore.core.systems.config;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import com.moore.core.entity.BaseEntity;
import com.moore.core.systems.properties.GlobalSystemProperties;
import com.moore.core.util.CommonUtils;
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
import java.time.LocalDateTime;
import java.util.Objects;

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

        injectEntity(args);
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        //不齐实体类中默认值

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


    /**
     * 通过参数判断是否与当前实体类相关
     * 如果是相关的实体类，则将ID，时间补齐，存在则不进行修改
     * @param args
     */
    private void injectEntity(Object... args) {
        if (Objects.isNull(args) || args.length == 0) {
            return;
        }
        for (Object arg : args) {
            if (arg instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) arg;

                int loginId = 0;
                String str = (String) CommonUtils.catchParam(StpUtil::getLoginId);
                if(Objects.nonNull(str)){
                    loginId = Integer.parseInt(str);
                }
                if (Objects.isNull(baseEntity.getCreatedId())) {
                    baseEntity.setCreatedId(loginId);
                }
                if (Objects.isNull(baseEntity.getModifiedId())) {
                    baseEntity.setModifiedId(loginId);
                }
                if (Objects.isNull(baseEntity.getCreatedTime())) {
                    baseEntity.setCreatedTime(LocalDateTime.now());
                }
                if (Objects.isNull(baseEntity.getModifiedTime())) {
                    baseEntity.setModifiedTime(LocalDateTime.now());
                }
            }
        }
    }

}
