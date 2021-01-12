package com.wt.dao.impl;

import com.wt.dao.MessageDao;
import com.wt.entity.Message;
import com.wt.utils.FormatUtil;
import com.wt.utils.JdbcUtil;
import com.wt.vo.ContactVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MessageDaoImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 16:27
 **/
public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> selectAll(String clientId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select * from t_message WHERE receive_id= '"+clientId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Message> list = new ArrayList<>();
        while (rs.next()) {
            Message message = Message.builder()
                    .messageContent(rs.getString("message_content"))
                    .messageId(rs.getString("message_id"))
                    .messageTitle(rs.getString("message_title"))
                    .planId(rs.getString("plan_id"))
                    .receiveId(rs.getString("receive_id"))
                    .sendId(rs.getString("send_id"))
                    .build();
            list.add(message);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return list;
    }

    @Override
    public Message selectMessageDetail(String messageId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "select * from t_message WHERE message_id= '"+messageId+"'";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Message message=null;
        while (rs.next()) {
            message = Message.builder()
                    .messageContent(rs.getString("message_content"))
                    .messageId(rs.getString("message_id"))
                    .messageTitle(rs.getString("message_title"))
                    .planId(rs.getString("plan_id"))
                    .receiveId(rs.getString("receive_id"))
                    .sendId(rs.getString("send_id"))
                    .build();
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return message;
    }

    @Override
    public void newMessage(Message message,String clientId) throws SQLException {
        JdbcUtil jdbcUtil = JdbcUtil.getInitJdbcUtil();
        Connection connection = jdbcUtil.getConnection();
        message.setMessageTitle("您好，您有一份推送");
        String sql = "insert into t_message(message_id,send_id,receive_id,plan_id,message_title,message_content) " +
                "values('"+message.getMessageId()+"','"+message.getSendId()+"','"+clientId+"','"+message.getPlanId()+"','"+message.getMessageTitle()+"','"+message.getMessageContent()+"')";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.execute();
        pstmt.close();
        jdbcUtil.closeConnection();
    }
}
