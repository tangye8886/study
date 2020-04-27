package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.entity.Menu;

import java.util.List;

/**
 * (Menu)表服务接口
 *
 * @author makejava
 * @since 2020-01-03 23:18:02
 */
public interface MenuService extends IService<Menu> {


    //根据角色ID 获取一级根菜单
    public List<Menu> getRootMenuByRole(String roleid);

    public List<Menu> getAllMenu();

    public List<Menu> getMenuChildren(Menu menu);

    public List<Menu> getOneMenu();//一级菜单
}