package com.moore.core.systems.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：imoore
 * @date ：created in 2023/2/12 15:14
 * @description：
 * @version: v
 */
@Slf4j
@Component
public class IdGeneratorSnowflake {

    private long workerId = 0;  //第几号机房
    private final long datacenterId = 1;  //第几号机器
    private final Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);

    @PostConstruct  //构造后开始执行，加载初始化工作
    public void init() {
        try {
            //获取本机的ip地址编码
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId: " + workerId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workerId获取失败 ----> " + e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }
}