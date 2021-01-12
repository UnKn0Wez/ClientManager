package com.wt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Message
 * @Description TODO
 * @Author UnKnW
 * @Date 2021/1/12 16:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String messageId;
    private String sendId;
    private String receiveId;
    private String planId;
    private String messageTitle;
    private String messageContent;
}
