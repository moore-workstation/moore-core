package com.moore.core.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author ：imoore
 * @date ：created in 2022/11/14 18:27
 * @description：共通工具类
 * @version: v
 */
public class CommonUtils {
    private CommonUtils() {
        throw new IllegalStateException("CommonUtils不允许初始化");
    }


    public static String currentMethodName() {
//        1是本方法 2是父方法
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }


    public static <T> T catchParam(Supplier<T> t){
        try{
            return t.get();
        }catch (Exception e){
            return null;
        }
    }

}
