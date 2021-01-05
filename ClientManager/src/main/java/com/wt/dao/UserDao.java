package com.wt.dao;

import com.wt.entity.User;
import com.wt.vo.ClientVo;
import com.wt.vo.ContactVo;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:49
 **/
public interface UserDao {
    User logins(String account) throws SQLException;
    void clientRegister(User user)throws SQLException;
    void contactRegister(User user)throws SQLException;
    List<ContactVo> selectAll()throws SQLException;
    List<ClientVo> selectClientAll()throws SQLException;
    void deleteContact(String contactId) throws SQLException;
    ContactVo selectByContact(String contact_Id)throws SQLException;
    void updateContact(String user_id,User user) throws SQLException;
    List<ContactVo> searchInfo(String contactName, String depId, String proID)throws SQLException;
}
