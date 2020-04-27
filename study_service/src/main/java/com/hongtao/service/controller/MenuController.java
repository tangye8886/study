package com.hongtao.service.controller;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.entity.Menu;
import com.hongtao.common.entity.RoleMenu;
import com.hongtao.service.service.MenuService;
import com.hongtao.service.service.RoleMenuService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author makejava
 * @since 2020-01-02 23:58:20
 */
@CrossOrigin
@RestController
@RequestMapping("api/menu")
public class MenuController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;


    /**
     * 获取菜单树
     */
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public List<Menu>  demo(@RequestParam("roleid") String roleid){
        List<Menu> menulist=getMenu(roleid);
        return menulist;
    }

    /**
     * 获取所有一级菜单
     */
    @RequestMapping(value = "/queryOneMenu",method = RequestMethod.GET)
    public List<Menu>  queryOneMenu(){
        return menuService.getOneMenu();
    }


    /**
     * 根据 角色RoleID 获取所有一级菜单
     */
    @RequestMapping(value = "/queryOneMenuByRole",method = RequestMethod.GET)
    public List<Menu>  queryOneMenuByRole(@RequestParam("roleid") String roleid){
        return menuService.getRootMenuByRole(roleid);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "/query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable Serializable id) {
        return success(this.menuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param menu 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public R insert(@RequestBody Menu menu){
        String no=MyUtils.makeCommentNo();
        menu.setId(no);
        menu.setCreatime(new Date());
        if(menu.getPid().equals("0"))
        {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setId(MyUtils.getUUID());
            roleMenu.setRoleid("1");
            roleMenu.setMenuid(no);
            roleMenuService.save(roleMenu);
        }
        return success(this.menuService.save(menu));
    }

    /**
     * 修改数据
     *
     * @param menu 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "/modify",method = RequestMethod.PUT)
    public R update(@RequestBody Menu menu) {
        menu.setUpdatime(new Date());
        return success(this.menuService.updateById(menu));
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        for(String s:idList)
        {
            Menu menu = menuService.getById(s);
            //如果是一级菜单  需要删除角色-菜单表的关联
            if(menu.getPid().equals("0"))
            {
               roleMenuService.deleteRoleMenuByMenuID(menu.getId());
            }
        }
        return success(this.menuService.removeByIds(idList));
    }


    public List<Menu> getMenu(String roleid)
    {
        //获得所有一级菜单
        List<Menu> oneMenu=menuService.getRootMenuByRole(roleid);
        List<Menu> result=new ArrayList<Menu>();
        if(oneMenu.size()>0) {
            //遍历一级家菜单
            for(Menu menu:oneMenu)
            {
                menu.setChildren(getMenuChildren(menu));
                result.add(menu);
            }
        }
        return result;
    }

    public List<Menu> getMenuChildren(Menu menu)
    {
        List<Menu> list=new ArrayList<Menu>();
        if(menu!=null)
        {
            //查询当前菜单的下一级菜单
            List<Menu> childrenList = menuService.getMenuChildren(menu);
            if(childrenList.size()>0)
            {
                //遍历下一级菜单，
                for(Menu chile:childrenList)
                {
                    chile.setChildren(getMenuChildren(chile));

                    list.add(chile);
                }
            }
        }
        return list;
    }
}