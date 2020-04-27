package com.hongtao.service.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtao.common.entity.RoleMenu;
import com.hongtao.common.entity.RoleMenu;
import com.hongtao.service.service.RoleMenuService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (RoleMenu)表控制层
 *
 * @author makejava
 * @since 2020-01-07 23:25:34
 */
@CrossOrigin
@RestController
@RequestMapping("api/roleMenu")
public class RoleMenuController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;


    @RequestMapping(value = "query",method = RequestMethod.GET)
    public R selectAll(Page<RoleMenu> page, RoleMenu roleMenu) {
        return success(this.roleMenuService.page(page, new QueryWrapper<>(roleMenu)));
    }


    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id) {
        return success(this.roleMenuService.getById(id));
    }


    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody RoleMenu roleMenu) {
        roleMenu.setId(MyUtils.makeCommentNo());
        return success(this.roleMenuService.save(roleMenu));
    }

    @RequestMapping(value = "addManyRoleMenu",method = RequestMethod.GET)
    public void addManyRoleMenu(@RequestParam("roleid")String roleid,@RequestParam("menuid")String menuid[]) {
        //先清空角色的关联菜单
        roleMenuService.deleteRoleMenuByRoleID(roleid);
        if(menuid.length>0)
        {
            for(int i=0;i<menuid.length;i++)
            {
                RoleMenu roleMenu=new RoleMenu(MyUtils.makeCommentNo(),roleid,menuid[i]);
                roleMenuService.save(roleMenu);
            }
        }
    }

    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody RoleMenu roleMenu) {
        return success(this.roleMenuService.updateById(roleMenu));
    }


    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.roleMenuService.removeByIds(idList));
    }

    @RequestMapping(value = "deleteByRoleid",method = RequestMethod.DELETE)
    public Integer deleteByRoleid(@RequestParam("idList") List<String> idList) {
        for(String rid:idList){
            roleMenuService.deleteRoleMenuByRoleID(rid);
        }
        return 1;
    }
}