package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.entity.Menu;
import com.hongtao.service.dao.MenuDao;
import com.hongtao.service.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2020-01-03 23:18:02
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Resource
    MenuDao dao;


    @Override
    public List<Menu> getRootMenuByRole(String roleid) {
        return dao.getRootMenuByRole(roleid);
    }

    @Override
    public List<Menu> getAllMenu() {
        return dao.selectList(null);
    }

    @Override
    public List<Menu> getMenuChildren(Menu menu) {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<Menu>();
        queryWrapper.eq("pid",menu.getId());
        queryWrapper.eq("status",1);
        return dao.selectList(queryWrapper);
    }

    @Override
    public List<Menu> getOneMenu() {
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<Menu>();
        queryWrapper.eq("pid","0");
        queryWrapper.eq("status",1);
        return dao.selectList(queryWrapper);
    }
}