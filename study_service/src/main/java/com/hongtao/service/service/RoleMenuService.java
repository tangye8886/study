package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.entity.RoleMenu;

/**
 * (RoleMenu)表服务接口
 *
 * @author makejava
 * @since 2020-01-04 01:07:16
 */
public interface RoleMenuService extends IService<RoleMenu> {

    public Integer deleteRoleMenuByMenuID(String menuID);

    public Integer deleteRoleMenuByRoleID(String roleid);
}