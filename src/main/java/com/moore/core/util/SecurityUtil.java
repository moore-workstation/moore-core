package com.moore.core.util;

/**
 * @author ：imoore
 * @date ：created in 2022/11/25 20:26
 * @description：安全相关
 * @version: v1.0
 */
public class SecurityUtil {

    private SecurityUtil() {
        throw new IllegalStateException("该方法不允许实例化");
    }


    /***
     * 查找范围属于那个区间
     * 例如：sum:50,maxRange:10
     * ,index:46-50之间 返回 10
     * ,index:40-45之间 返回 9
     * ,index:1-5之间 返回 1
     * @param sum 区间总数
     * @param maxRange 最大区间数
     * @param index 查询索引
     * @return 属于那个区间
     */
    public static Integer rangeOf(int sum, int maxRange, int index) {
        return rangeOf(sum, maxRange, sum / maxRange * 2, index, 0);
    }

    /***
     * 范围查找
     * @param sum 范围总数
     * @param maxRange 范围最大区间
     * @param findStartIndex 迭代开始索引 起始索引默认 sum/maxRange*2
     * @param index 查找索引
     * @param memory 记录
     * @return 当前所在的范围区间
     * @throws
     */
    private static Integer rangeOf(int sum, int maxRange, int findStartIndex, int index, int memory) {
        if (findStartIndex > sum) {
            return -1;
        }
        if (index <= findStartIndex && index >= sum / maxRange * 2) {
            memory = (findStartIndex - index) >= sum / maxRange ? memory + 1 : memory + 2;
            return memory;
        }
        findStartIndex = sum / maxRange * 2;
        return rangeOf(sum, maxRange, findStartIndex, index, memory + 2);

    }
}
