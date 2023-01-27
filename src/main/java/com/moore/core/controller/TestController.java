package com.moore.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.moore.core.result.Result;
import com.moore.core.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        StpUtil.login(id);
        return Result.data(StpUtil.getTokenValue());
    }
    @GetMapping("logout")
    public Result<String> logout(){
        StpUtil.logout();
        return Result.data("登陆退出");

    }
}
