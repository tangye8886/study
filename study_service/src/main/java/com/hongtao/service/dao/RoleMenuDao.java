package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * (RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-01-04 01:07:16
 */
public interface RoleMenuDao extends BaseMapper<RoleMenu> {


    //根据菜单ID删除菜单跟角色关联表的记录
    @Delete("delete from role_menu where menuid=#{menuID}")
    public Integer deleteRoleMenuByMenuID(@Param(value = "menuID") String menuID);

    //根据角色ID删除菜单跟角色关联表的记录
    @Delete("delete from role_menu where roleid=#{roleid}")
    public Integer deleteRoleMenuByRoleID(@Param(value = "roleid") String roleid);

}