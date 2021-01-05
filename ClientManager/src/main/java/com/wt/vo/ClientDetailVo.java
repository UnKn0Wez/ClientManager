package com.wt.vo;

/**
 * @ClassName ClientDetailVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/5 18:47
 **/
public class ClientDetailVo {
    private static String clientDetailId;
    private static String clientDetailImg;

    public String getClientDetailId() {
        return clientDetailId;
    }

    public static void setClientDetailImg(String clientDetailImg) {
        ClientDetailVo.clientDetailImg = clientDetailImg;
    }

    public static String getClientDetailImg() {
        return clientDetailImg;
    }

    public void setClientDetailId(String clientDetailId) {
        this.clientDetailId = clientDetailId;
    }
}
