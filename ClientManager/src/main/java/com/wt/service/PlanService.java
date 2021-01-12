package com.wt.service;

import com.wt.entity.Mission;
import com.wt.entity.Product;
import com.wt.vo.MissionVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName PlanService
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11
 **/
public interface PlanService {
    List<MissionVo> selectAll();
    List<MissionVo> selectAllClient();
    List<MissionVo> searchPlan(String state,String type,String realName);
    List<MissionVo> searchPlanClient(String state,String type,String realName);
    void deletePlanById(String planId);
    void newPlan(Mission mission);
    MissionVo selectPlanById(String planId);
    void updatePlan(MissionVo missionVo);

}
