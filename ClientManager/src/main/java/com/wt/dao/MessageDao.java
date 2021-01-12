package com.wt.dao;

import com.wt.entity.Message;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * @ClassName MessageDao
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12
 **/
public interface MessageDao {
    List<Message> selectAll(String clientId)throws SQLException;
    Message selectMessageDetail(String messageId)throws SQLException;
    void newMessage(Message message,String clientId) throws SQLException;
}
