package com.wt.vo;

/**
 * @ClassName depDetailVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/6 14:32
 **/
public class DepDetailVo {
    private static String depId;

    public static String getDepId() {
        return depId;
    }

    public static void setDepId(String depId) {
        DepDetailVo.depId = depId;
    }
}
