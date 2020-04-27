package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.entity.Menu;
import com.hongtao.common.entity.RoleMenu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "study-service")
public interface MenuService {

    @RequestMapping(value = "api/menu/query",method = RequestMethod.GET)
    public List<Menu> queryALL(@RequestParam("roleid") String roleid);

    @RequestMapping(value = "api/menu/queryOneMenu",method = RequestMethod.GET)
    public List<Menu> queryOneMenu();

    @RequestMapping(value = "api/menu/queryOneMenuByRole",method = RequestMethod.GET)
    public List<Menu>  queryOneMenuByRole(@RequestParam("roleid") String roleid);

    @RequestMapping(value = "api/menu/query/{id}",method = RequestMethod.GET)
    public R queryOne(@PathVariable("id") String id);

    @RequestMapping(value ="api/menu/delete",method = RequestMethod.DELETE)
    public R deleteMenu(@RequestParam("idList") String idList);

    @RequestMapping(value = "api/menu/insert",method = RequestMethod.POST)
    public R addMenu(@RequestBody Menu menu);

    @RequestMapping(value = "api/menu/modify",method = RequestMethod.PUT)
    public R modifyMenu(@RequestBody Menu menu);


    /*
        菜单- 角色
     */

    @RequestMapping(value = "api/roleMenu/insert",method = RequestMethod.POST)
    public R addRoleMenu(@RequestBody RoleMenu roleMenu);


    @RequestMapping(value = "api/roleMenu/addManyRoleMenu",method = RequestMethod.GET)
    public void addManyRoleMenu(@RequestParam("roleid") String roleid, @RequestParam("menuid") String menuid[]);

    //根据角色ID 删除数据
    @RequestMapping(value = "api/roleMenu/deleteByRoleid",method = RequestMethod.DELETE)
    public Integer deleteByRoleid(@RequestParam("idList") List<String> idList);


}
