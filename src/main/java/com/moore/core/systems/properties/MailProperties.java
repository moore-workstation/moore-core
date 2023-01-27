package com.moore.core.systems.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：imoore
 * @date ：created in 2022/11/14 21:35
 * @description：mail属性绑定
 * @version: v
 */
@Component

@ConfigurationProperties(prefix = "moore-core.mail")
@Data
public class MailProperties {
    private String user;
    private String password;
    private String from;
    private String to;
    private String cc;
    private String host;
    private String encoding;
}
