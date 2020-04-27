package com.hongtao.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.MenuService;
import com.hongtao.common.entity.Menu;
import com.hongtao.common.entity.RoleMenu;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/admin/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public List<Menu> demo(@RequestParam("roleid") String roleid){
        return menuService.queryALL(roleid);
    }

    //获取所有一级菜单
    @RequestMapping(value = "/queryOneMenu",method = RequestMethod.GET)
    public List<Menu> demo(){
        return menuService.queryOneMenu();
    }

    @RequestMapping(value = "/queryOneMenuByRole",method = RequestMethod.GET)
    public List<Menu>  queryOneMenuByRole(@RequestParam("roleid") String roleid){
        return menuService.queryOneMenuByRole(roleid);
    }


    @RequestMapping(value = "/query/{id}",method = RequestMethod.GET)
    public R queryOne(@PathVariable("id") String id){
        return menuService.queryOne(id);
    }

    @RequestMapping(value ="/delete",method = RequestMethod.DELETE)
    public R deleteMenu(@RequestParam("idList") String idList){
        return menuService.deleteMenu(idList);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public R addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @RequestMapping(value = "/modify",method = RequestMethod.PUT)
    public R modifyMenu(@RequestBody Menu menu){
        return menuService.modifyMenu(menu);
    }


    @RequestMapping(value = "roleMenu/insert",method = RequestMethod.POST)
    public R addRoleMenu(@RequestBody RoleMenu roleMenu){
        return menuService.addRoleMenu(roleMenu);
    }

    @RequestMapping(value = "roleMenu/deleteByRoleid",method = RequestMethod.DELETE)
    public Integer deleteByRoleid(@RequestParam("idList") List<String> idList){
        return menuService.deleteByRoleid(idList);
    }

    @RequestMapping(value = "roleMenu/addManyRoleMenu",method = RequestMethod.GET)
    public void addManyRoleMenu(@RequestParam("roleid")String roleid,@RequestParam("menuid")String menuid[]){
        menuService.addManyRoleMenu(roleid,menuid);
    }

}
