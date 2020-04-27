package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.entity.RoleMenu;
import com.hongtao.service.dao.RoleMenuDao;
import com.hongtao.service.service.RoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-01-04 01:07:16
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

    @Resource
    RoleMenuDao roleMenuDao;


    @Override
    public Integer deleteRoleMenuByMenuID(String menuID) {
        return roleMenuDao.deleteRoleMenuByMenuID(menuID);
    }

    @Override
    public Integer deleteRoleMenuByRoleID(String roleid) {
        return roleMenuDao.deleteRoleMenuByRoleID(roleid);
    }

}