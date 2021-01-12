package com.wt.dao;

import com.wt.entity.Mission;
import com.wt.vo.MissionVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName PlanDao
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11
 **/
public interface PlanDao {
    List<MissionVo> selectAll() throws SQLException;
    List<MissionVo> selectAllClient() throws SQLException;
    List<MissionVo> searchPlan(String state,String type,String realName) throws SQLException;
    List<MissionVo> searchPlanClient(String state,String type,String realName) throws SQLException;
    void deletePlanById(String id)throws SQLException;
    void newPlan(Mission mission)throws SQLException;
    MissionVo selectPlanById(String planId)throws SQLException;
    void updatePlan(MissionVo missionVo)throws SQLException;
}
