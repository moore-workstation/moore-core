package com.moore.core.systems.config;

import cn.dev33.satoken.config.SaCookieConfig;
import cn.dev33.satoken.config.SaTokenConfig;
import com.moore.core.systems.properties.GlobalSystemProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：imoore
 * @date ：created in 2023/1/28 21:25
 * @description：框架基础配置
 * @version: v
 */

@Configuration
public class BaseCoreConfig {
    @ConditionalOnMissingBean(value = SaTokenConfig.class)
    @Bean
    public SaTokenConfig saTokenConfig(){
        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName("token");
        return config;
    }

    @ConditionalOnBean(SaTokenConfig.class)
    @Bean
    public SaTokenConfig saTokenConfig(SaTokenConfig saTokenConfig){
        if(!StringUtils.hasText(saTokenConfig.getTokenName())){
            saTokenConfig.setTokenName("token");
        }
        return saTokenConfig;
    }

}
