package com.wt.service;

import com.wt.entity.User;
import com.wt.utils.ResultEntity;
import com.wt.vo.ClientVo;
import com.wt.vo.ContactVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29
 **/
public interface UserService {
    ResultEntity login(String uName, String password);
    void clientRegister(User user);
    void contactRegister(User user);
    List<ContactVo> selectAll();
    List<ClientVo> selectClientAll();
    void deleteContact(String userId);
    ContactVo selectByContact(String contact_Id);
    List<ClientVo> selectByClient(String clientId,String address,String ClientCredit);
    void updateContact(String user_id,User user);
    List<ContactVo> searchInfo(String contactName, String depId, String proID);
    void deleteClient(String clientId);
    void updateClient(ClientVo clientVo);
    ClientVo selectByClient(String clientId);
    ContactVo selectContactIdByName(String contactName);
}
