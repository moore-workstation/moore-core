package com.moore.core.systems.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：imoore
 * @date ：created in 2022/11/10 20:30
 * @description：core配置文件
 * @version: v
 */
@Data
@Component
@ConfigurationProperties(prefix = "moore-core.aop")
public class GlobalSystemProperties {
    private List<String> logExcludeList;
    private List<String> interceptorPathPattern;
    private List<String> excludePathPattern;
}
