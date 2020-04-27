package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.entity.UserRole;
import com.hongtao.service.dao.UserRoleDao;
import com.hongtao.service.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserRole)表服务实现类
 *
 * @author makejava
 * @since 2019-11-27 00:05:40
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    @Resource
    UserRoleDao userRoleDao;

    @Override
    public List<UserRole> getAllRole() {
       return userRoleDao.selectList(null);
    }
}