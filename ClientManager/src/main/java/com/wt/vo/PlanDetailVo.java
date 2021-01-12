package com.wt.vo;

/**
 * @ClassName PlanDetailVo
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 8:16
 **/
public class PlanDetailVo {
    private static String planDetailId;

    public static String getPlanDetailId() {
        return planDetailId;
    }

    public static void setPlanDetailId(String planDetailId) {
        PlanDetailVo.planDetailId = planDetailId;
    }
}
