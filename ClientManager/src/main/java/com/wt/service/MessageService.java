package com.wt.service;

import com.wt.entity.Message;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12
 **/
public interface MessageService {
    List<Message> selectAll(String clientId);
    Message selectMessageDetail(String messageId);
    void newMessage(Message message,String clientId);
}
