package com.wt.vo;

/**
 * @ClassName RequestDetailVo
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 11:02
 **/
public class RequestDetailVo {
    private static String requestId;

    public static String getrequestId() {
        return requestId;
    }

    public static void setrequestId(String requestId) {
        RequestDetailVo.requestId = requestId;
    }
}
