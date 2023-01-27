package com.moore.core.systems;

import lombok.Data;

/**
 * @author ：imoore
 * @date ：created in 2022/11/14 21:30
 * @description：邮件
 * @version: v
 */
@Data
public class Mail {
    private String from;
    private String to;
    private String cc;
    private String subject;
    private String text;

}
