package com.wt.service.impl;

import com.wt.dao.UserDao;
import com.wt.entity.User;
import com.wt.factory.DaoFactory;
import com.wt.service.UserService;
import com.wt.utils.ResultEntity;
import com.wt.vo.ClientVo;
import com.wt.vo.ContactVo;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author OXH
 * @Date 2020/12/29 17:50
 **/
public class UserServiceImpl implements UserService {
    private final UserDao userDao= DaoFactory.getUserDaoInstance();
    @Override
    public ResultEntity login(String uName, String password) {
        ResultEntity resultEntity;
        User user=null;
        try {
            user=userDao.logins(uName);
        } catch (SQLException e) {
            System.err.println("根据账号查找用户信息出现SQL异常");
        }
        if(user!=null){
            if(DigestUtils.md5Hex(password).equals(user.getPassword())){
                resultEntity=ResultEntity.builder().code(0).message("登录成功").data(user).build();
            }
            else {
                resultEntity=ResultEntity.builder().code(1).message("密码错误").build();
            }
        }
        else {
            resultEntity=ResultEntity.builder().code(3).message("账号不存在").build();
        }
        return resultEntity;
    }

    @Override
    public void clientRegister(User user) {
        try {
            userDao.clientRegister(user);
        } catch (SQLException e) {
            System.err.println("新增客户信息发生错误");
        }
    }

    @Override
    public void contactRegister(User user) {
        try {
            userDao.contactRegister(user);
        } catch (SQLException e) {
            System.err.println("新增员工信息发生错误");
        }
    }

    @Override
    public List<ContactVo> selectAll() {
        List<ContactVo> contactList = null;
        try {
            contactList = userDao.selectAll();
        } catch (SQLException e) {
            System.err.println("查询员工信息发生错误");
        }
        return contactList;
    }

    @Override
    public List<ClientVo> selectClientAll() {
        List<ClientVo> clientVos = null;
        try {
            clientVos = userDao.selectClientAll();
        } catch (SQLException e) {
            System.err.println("查询客户信息发生错误");
        }
        return clientVos;
    }
    @Override
    public void deleteContact(String userId) {
        try{
            userDao.deleteContact(userId);
        }catch (SQLException e){
            System.err.println("删除联系人信息出现异常");
        }
    }

    @Override
    public ContactVo selectByContact(String contact_Id) {
        ContactVo contactList = null;
        try {
            contactList = userDao.selectByContact(contact_Id);
        } catch (SQLException e) {
            System.err.println("查询联系人信息发生错误");
        }
        return contactList;
    }

    @Override
    public void updateContact(String user_id, User user) {
        try{
            userDao.updateContact(user_id,user);
        }catch (SQLException e){
            System.err.println("修改联系人信息出现异常");
        }
    }
}
