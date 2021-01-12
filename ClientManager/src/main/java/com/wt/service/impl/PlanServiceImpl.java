package com.wt.service.impl;

import com.wt.dao.PlanDao;
import com.wt.entity.Mission;
import com.wt.entity.Product;
import com.wt.factory.DaoFactory;
import com.wt.service.PlanService;
import com.wt.vo.MissionVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName PlanServiceImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 12:47
 **/
public class PlanServiceImpl implements PlanService {
    private final PlanDao planDao = DaoFactory.getPlanDaoInstance();
    @Override
    public List<MissionVo> selectAll() {
        List<MissionVo> missions = null;
        try {
            missions=planDao.selectAll();
        } catch (SQLException e) {
            System.err.println("查询所有计划发生错误！");
        }
        return missions;
    }

    @Override
    public List<MissionVo> selectAllClient() {
        List<MissionVo> missions = null;
        try {
            missions=planDao.selectAllClient();
        } catch (SQLException e) {
            System.err.println("查询客户计划发生错误！");
        }
        return missions;
    }

    @Override
    public List<MissionVo> searchPlan(String state, String type, String realName) {
        List<MissionVo> missions = null;
        try {
            missions=planDao.searchPlan(state,type,realName);
        } catch (SQLException e) {
            System.err.println("根据关键词查询计划发生错误！");
        }
        return missions;
    }

    @Override
    public List<MissionVo> searchPlanClient(String state, String type, String realName) {
        List<MissionVo> missions = null;
        try {
            missions=planDao.searchPlanClient(state,type,realName);
        } catch (SQLException e) {
            System.err.println("根据关键词查询客户计划发生错误！");
        }
        return missions;
    }

    @Override
    public void deletePlanById(String planId) {
        try {
            planDao.deletePlanById(planId);
        } catch (SQLException e) {
            System.err.println("根据ID删除任务发生错误！");
        }
    }

    @Override
    public void newPlan(Mission mission) {
        try {
            planDao.newPlan(mission);
        } catch (SQLException e) {
            System.err.println("新增任务计划发生错误");
        }
    }

    @Override
    public MissionVo selectPlanById(String planId) {
        MissionVo missionVo = null;
        try {
            missionVo=planDao.selectPlanById(planId);
        } catch (SQLException e) {
            System.err.println("根据Id查询计划发生错误");
        }
        return missionVo;
    }

    @Override
    public void updatePlan(MissionVo missionVo) {
        try {
            planDao.updatePlan(missionVo);
        } catch (SQLException e) {
            System.err.println("更新任务计划发生错误！");
        }
    }


}
