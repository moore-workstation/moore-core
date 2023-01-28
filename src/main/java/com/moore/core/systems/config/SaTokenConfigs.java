package com.moore.core.systems.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.moore.core.systems.properties.GlobalSystemProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：imoore
 * @date ：created in 2022/12/17 16:48
 * @description：saToken
 * @version: v
 */
@Configuration
public class SaTokenConfigs implements WebMvcConfigurer {
    private final GlobalSystemProperties properties;


    public SaTokenConfigs(GlobalSystemProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()));
//        添加过滤内容
        if (!properties.getInterceptorPathPattern().isEmpty()) {
            registration.addPathPatterns(properties.getInterceptorPathPattern());
        }
//        添加排除内容
        if (!properties.getExcludePathPattern().isEmpty()) {
            registration.excludePathPatterns(properties.getExcludePathPattern());
        }
    }
}
