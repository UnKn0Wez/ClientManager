package com.wt.dao.impl;

import com.wt.dao.PlanDao;
import com.wt.entity.Department;
import com.wt.entity.Mission;
import com.wt.entity.Product;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;
import com.wt.vo.MissionVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PlanDaoImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/11 12:37
 **/
public class PlanDaoImpl implements PlanDao {
    @Override
    public List<MissionVo> selectAll() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT plan_id,client_num,plan_profit,start_time,finish_time,plan_state,product_type,product_name,realname " +
                "FROM t_plan,t_product,t_user " +
                "where t_plan.product_id=t_product.product_id and t_plan.product_id=t_user.product_id\n";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MissionVo> missionList = new ArrayList<>();
        while(rs.next()){
            MissionVo mission = new MissionVo();
            mission.setPlanId(rs.getString("plan_id"));
            mission.setPlanState(rs.getString("plan_state"));
            mission.setPlanProfit(rs.getDouble("plan_profit"));
            mission.setClientNum(rs.getInt("client_num"));
            mission.setProductType(rs.getString("product_type"));
            mission.setProductName(rs.getString("product_name"));
            mission.setStartTime(rs.getDate("start_time"));
            mission.setFinishTime(rs.getDate("finish_time"));
            mission.setRealName(rs.getString("realname"));
            missionList.add(mission);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return missionList;
    }

    @Override
    public List<MissionVo> selectAllClient() throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT plan_id,start_time,finish_time,plan_state,product_type,product_name,realname " +
                "FROM t_plan,t_product,t_user " +
                "WHERE t_plan.product_id=t_product.product_id and t_plan.product_id=t_user.product_id\n";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MissionVo> missionList = new ArrayList<>();
        while(rs.next()){
            MissionVo mission = new MissionVo();
            mission.setPlanId(rs.getString("plan_id"));
            mission.setPlanState(rs.getString("plan_state"));
            mission.setProductType(rs.getString("product_type"));
            mission.setProductName(rs.getString("product_name"));
            mission.setStartTime(rs.getDate("start_time"));
            mission.setFinishTime(rs.getDate("finish_time"));
            mission.setRealName(rs.getString("realname"));
            missionList.add(mission);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return missionList;
    }

    @Override
    public List<MissionVo> searchPlan(String state,String type,String realName) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT plan_id,client_num,plan_profit,start_time,finish_time,plan_state,product_type,product_name,realname " +
                "FROM t_plan,t_product,t_user " +
                "where t_plan.product_id=t_product.product_id and t_plan.product_id=t_user.product_id";
        if(!("请选择完成情况".equals(state))&&state!=null){
            sql+=" and plan_state='"+state+"'";
        }
        if(!("请选择产品类型".equals(type))&&type!=null){
            sql+=" and product_type='"+type+"'";
        }
        if(!("".equals(realName))&&realName!=null){
            sql+=" and realname like '%"+realName+"%'";
        }
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MissionVo> missionList = new ArrayList<>();
        while(rs.next()){
            MissionVo mission = new MissionVo();
            mission.setPlanId(rs.getString("plan_id"));
            mission.setPlanState(rs.getString("plan_state"));
            mission.setPlanProfit(rs.getDouble("plan_profit"));
            mission.setClientNum(rs.getInt("client_num"));
            mission.setProductType(rs.getString("product_type"));
            mission.setProductName(rs.getString("product_name"));
            mission.setStartTime(rs.getDate("start_time"));
            mission.setFinishTime(rs.getDate("finish_time"));
            mission.setRealName(rs.getString("realname"));
            missionList.add(mission);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return missionList;
    }

    @Override
    public List<MissionVo> searchPlanClient(String state, String type, String realName) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT plan_id,start_time,finish_time,plan_state,product_type,product_name,realname \n" +
                "FROM t_plan,t_product,t_user \n" +
                "where t_plan.product_id=t_product.product_id and t_plan.product_id=t_user.product_id";
        if(!("请选择完成情况".equals(state))&&state!=null){
            sql+=" and plan_state='"+state+"'";
        }
        if(!("请选择产品类型".equals(type))&&type!=null){
            sql+=" and product_type='"+type+"'";
        }
        if(!("".equals(realName))&&realName!=null){
            sql+=" and realname like '%"+realName+"%'";
        }
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MissionVo> missionList = new ArrayList<>();
        while(rs.next()){
            MissionVo mission = new MissionVo();
            mission.setPlanId(rs.getString("plan_id"));
            mission.setPlanState(rs.getString("plan_state"));
            mission.setProductType(rs.getString("product_type"));
            mission.setProductName(rs.getString("product_name"));
            mission.setStartTime(rs.getDate("start_time"));
            mission.setFinishTime(rs.getDate("finish_time"));
            mission.setRealName(rs.getString("realname"));
            missionList.add(mission);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return missionList;
    }

    @Override
    public void deletePlanById(String id) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql="delete from t_plan where plan_id='"+id+"'";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    public static String DATE_FORMAT = "yyyy-MM-dd";
    @Override
    public void newPlan(Mission mission) throws SQLException {
        java.sql.Date finishTime = new java.sql.Date(mission.getFinishTime().getTime());
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Date date = new Date();
        String mybirth= FormatUtil.formatDate(date);
        long time = 0;
        try {
            time = FormatUtil.longFormat(mybirth, DATE_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(time);
        String date1 = LocalDateTime.now().format(df);
        String sql="insert into t_plan(plan_id,client_num,plan_profit,start_time,finish_time,plan_state,product_id) " +
                "values('"+date1+"',"+mission.getClientNum()+","+ mission.getPlanProfit()+",'"+sqlDate+"','"
                +finishTime+"','"+mission.getPlanState()+"','"+mission.getProductId()+"')";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }

    @Override
    public MissionVo selectPlanById(String planId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT start_time,finish_time,plan_state,product_type,product_name,realname,client_num,plan_profit \n" +
                "FROM t_plan,t_product,t_user \n" +
                "where t_plan.product_id=t_product.product_id and t_plan.product_id=t_user.product_id and t_plan.plan_id='"+planId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        MissionVo missionVo = null;
        while (rs.next()) {
            missionVo = MissionVo.builder()
                    .planProfit(rs.getDouble("plan_profit"))
                    .clientNum(rs.getInt("client_num"))
                    .startTime(rs.getDate("start_time"))
                    .finishTime(rs.getDate("finish_time"))
                    .planState(rs.getString("plan_state"))
                    .productType(rs.getString("product_type"))
                    .productName(rs.getString("product_name"))
                    .realName(rs.getString("realname"))
                    .build();
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return missionVo;
    }

    @Override
    public void updatePlan(MissionVo missionVo) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String sql = "update t_plan \n" +
                "set client_num="+missionVo.getClientNum()+",plan_profit="+missionVo.getPlanProfit()+"," +
                "plan_state='"+missionVo.getPlanState()+"',finish_time='"+sdf.format(missionVo.getFinishTime())+"',product_id='"+missionVo.getProductId()+"'\n" +
                "where plan_id='"+missionVo.getPlanId()+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }
}
