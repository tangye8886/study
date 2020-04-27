package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.UserDTO;
import com.hongtao.common.entity.UserInfo;

import java.util.List;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2019-11-26 23:12:18
 */
public interface UserService extends IService<UserInfo> {

    public UserInfo findUserByUserName(String username);

    public List<UserInfo> getUserByRole(String roleid);

    public List<UserInfo> getAll();  //获取所有表记录

    public List<UserInfo> getAllTeacher();//获取教师

    public List<UserInfo> getAllUser(); //获取用户

    public List<UserInfo> getAllByCondition(UserDTO userDTO); //待条件查询

    public Integer resetPwd(String id, String password);
}