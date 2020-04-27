package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.UserDTO;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.entity.UserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//@FeignClient(value = "study-service",fallback = UserServiceHystrix.class)
@FeignClient(value = "study-service")
public interface UserService {

    @RequestMapping(value = "api/user/query",method = RequestMethod.POST)
    public Map<String,Object> getPageUser(@RequestBody UserDTO userDTO);

    @RequestMapping(value = "api/user/queryTeacher",method = RequestMethod.GET)
    public Map<String,Object> queryTeacher();

    @RequestMapping(value = "api/user/queryUser",method = RequestMethod.GET)
    public Map<String,Object> queryUser();

    @RequestMapping(value = "api/user/query/{id}",method = RequestMethod.GET)
    public R getUser(@RequestParam("id") String id);

    @RequestMapping(value = "api/user/queryUserByRole",method = RequestMethod.GET)
    public R getUserByRole(@RequestParam(value = "role") String role);

    @RequestMapping(value = "api/user/userLogin",method = RequestMethod.GET)
    public UserInfo userLogin(@RequestParam(value = "username") String username);

    @RequestMapping(value="api/user/insert",method = RequestMethod.POST)
    public R insert(@RequestBody UserInfo user);

    @RequestMapping(value="api/user/modify",method = RequestMethod.PUT)
    public R update(@RequestBody UserInfo user);

    @RequestMapping(value="api/user/resetPwd",method = RequestMethod.PUT)
    public void resetPwd(@RequestParam("id") String id, @RequestParam("password") String password);


    @RequestMapping(value="api/user/delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList);


    /*
    角色
     */
    @RequestMapping(value = "api/userRole/queryRole",method = RequestMethod.GET)
    public Map<String,Object> queryRole();


    @RequestMapping(value = "api/userRole/query",method = RequestMethod.GET)
    public Map<String,Object> userRoleSelectAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);


    @RequestMapping(value = "api/userRole/query/{id}",method = RequestMethod.GET)
    public R userRoleSelectOne(@PathVariable("id") Serializable id);

    @RequestMapping(value = "api/userRole/insert",method = RequestMethod.POST)
    public R userRoleInsert(@RequestBody UserRole userRole);


    @RequestMapping(value = "api/userRole/modify",method = RequestMethod.PUT)
    public R userRoleUpdate(@RequestBody UserRole userRole);

    @RequestMapping(value = "api/userRole/delete",method = RequestMethod.DELETE)
    public R userRoleDelete(@RequestParam("idList") List<String> idList);


}
