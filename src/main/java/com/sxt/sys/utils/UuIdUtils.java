package com.sxt.sys.utils;

import java.util.UUID;

/**
 * @Author Winter
 * @Date 2019/12/2 16:05
 * UUID 工具类
 */
public class UuIdUtils {
    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        //将"-"替换成"" 大小写转换 转大写 toUpperCase 转小写 toLowerCase
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
