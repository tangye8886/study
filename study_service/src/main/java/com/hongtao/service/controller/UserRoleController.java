package com.hongtao.service.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.entity.UserRole;
import com.hongtao.service.service.UserRoleService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserRole)表控制层
 *
 * @author makejava
 * @since 2019-11-27 00:05:40
 */
@CrossOrigin
@RestController
@RequestMapping("api/userRole")
public class UserRoleController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 分页查询数据
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public Map<String,Object> selectAll(@RequestParam("pageNum")Integer pageNum,
                                        @RequestParam("pageSize")Integer pageSize)
    {
        PageHelper.startPage(pageNum, pageSize);
        Map<String,Object> map=new HashMap<String,Object>();
        List<UserRole> allUserRole = userRoleService.getAllRole();
        PageInfo<UserRole> page = new PageInfo<UserRole>(allUserRole);
        map.put("data",page);
        return map;
    }

    //返回所有角色
    @RequestMapping("queryRole")
    public Map<String,Object> queryUser()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data",userRoleService.getAllRole());
        return map;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userRole 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody UserRole userRole) {
        userRole.setId(MyUtils.makeRoleID());
        return success(this.userRoleService.save(userRole));
    }

    /**
     * 修改数据
     *
     * @param userRole 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody UserRole userRole) {
        return success(this.userRoleService.updateById(userRole));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.userRoleService.removeByIds(idList));
    }
}