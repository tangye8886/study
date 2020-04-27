package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.UserDTO;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.entity.UserRole;
import com.hongtao.service.dao.UserDao;
import com.hongtao.service.dao.UserRoleDao;
import com.hongtao.service.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2019-11-26 23:12:19
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserInfo> implements UserService {

    @Resource
    UserDao dao;

    @Resource
    UserRoleDao userRoleDao;

    @Override
    public UserInfo findUserByUserName(String username) {
        QueryWrapper wrapper=new QueryWrapper<UserInfo>();
        wrapper.eq("username",username);
        return dao.selectOne(wrapper);
    }

    @Override
    public List<UserInfo> getUserByRole(String roleid) {
        QueryWrapper wrapper=new QueryWrapper<UserInfo>();
        wrapper.eq("role",roleid);
        return dao.selectList(wrapper);
    }

    @Override
    public List<UserInfo> getAll() {
        return dao.selectList(null);
    }

    @Override
    public List<UserInfo> getAllTeacher() {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("role","2");
        return dao.selectList(queryWrapper);
    }

    @Override
    public List<UserInfo> getAllUser() {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.notIn("role","1","2");
        return dao.selectList(queryWrapper);
    }

    @Override
    public List<UserInfo> getAllByCondition(UserDTO userDTO) {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        if(userDTO.getRole()!=null && !"".equals(userDTO.getRole()))
        {
            queryWrapper.eq("role",userDTO.getRole());
        }
        if(userDTO.getStatus()!=null && !"".equals(userDTO.getStatus())) {
            queryWrapper.eq("status", userDTO.getStatus());
        }
        if(StringUtils.isNotBlank(userDTO.getUsername()))
        {
            queryWrapper.like("username",userDTO.getUsername());
        }
        List<UserInfo> users = dao.selectList(queryWrapper);
        for(UserInfo u:users)
        {
            UserRole userRole = this.userRoleDao.selectById(u.getRole());
            u.setRoleDetail(userRole);
        }
        return users;
    }

    @Override
    public Integer resetPwd(String id,String password) {
        return dao.resetPwd(id,password);
    }
}