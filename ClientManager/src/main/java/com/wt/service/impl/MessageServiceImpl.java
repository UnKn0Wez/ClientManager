package com.wt.service.impl;

import com.wt.dao.DepDao;
import com.wt.dao.MessageDao;
import com.wt.entity.Department;
import com.wt.entity.Message;
import com.wt.factory.DaoFactory;
import com.wt.service.MessageService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName MessageServiceImpl
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 16:29
 **/
public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao= DaoFactory.getMessageInstance();
    @Override
    public List<Message> selectAll(String clientId) {
        List<Message> messageList=null;
        try {
            messageList=messageDao.selectAll(clientId);
        } catch (SQLException e) {
            System.err.println("查询邮件出现错误！");
        }
        return messageList;
    }

    @Override
    public Message selectMessageDetail(String messageId) {
        Message message = null;
        try {
            message=messageDao.selectMessageDetail(messageId);
        } catch (SQLException e) {
            System.err.println("详细邮件出现错误！");
        }
        return message;
    }

    @Override
    public void newMessage(Message message, String clientId) {
        try {
            messageDao.newMessage(message,clientId);
        } catch (SQLException e) {
            System.err.println("发送邮件出现错误！");
        }
    }
}
