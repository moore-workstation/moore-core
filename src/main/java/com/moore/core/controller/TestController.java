package com.moore.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.moore.core.result.Result;
import com.moore.core.systems.config.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：imoore
 * @date ：created in 2022/11/14 18:39
 * @description：ceshi
 * @version: v
 */
@Slf4j
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    IdGeneratorSnowflake idGeneratorSnowflake;
    @GetMapping("t1")
    public Result test(@RequestParam("id")String id ,@RequestParam("password")String password){
        return Result.data("hello;Id:"+id+"password:"+password);
    }

    @GetMapping("t2")
    public Result test2(){

        String msg = "loginId:"+StpUtil.getLoginId()+";tokenInfo:"+StpUtil.getTokenInfo();
        return Result.data(msg);
    }

    @GetMapping("login")
    public Result<String> login(@RequestParam("userId")String id,@RequestParam("pass")String pass){
        log.info("snowflake:{}",idGeneratorSnowflake.snowflakeId());
        StpUtil.login(id);
        log.info(StpUtil.getLoginDevice());
        log.info("tokeninfo{}",StpUtil.getTokenInfo());
        log.info(StpUtil.getLoginDevice());
        return Result.data(StpUtil.getTokenValue());
    }
    @GetMapping("logout")
    public Result<String> logout(){
        StpUtil.logout();
        return Result.data("登陆退出");

    }

}
