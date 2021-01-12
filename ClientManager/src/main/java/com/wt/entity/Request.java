package com.wt.entity;

import com.mysql.cj.conf.PropertyDefinitions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Request
 * @Description TODO
 * @Author OXH
 * @Date 2021/1/12 8:05
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String request_id;
    private String client_id;
    private String client_name;
    private String contact_name;
    private Date request_time;
    private String request_content;
    private String order_id;
}
