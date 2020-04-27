package com.hongtao.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.UserService;
import com.hongtao.admin.utils.Result;
import com.hongtao.common.dto.UserDTO;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/admin/user/")
public class UserController {

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService service;

    @Resource
    PasswordEncoder passwordEncoder;
    /**
     * 分页查询数据
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> getUserByPage(@RequestBody UserDTO userDTO)
    {
        logger.info("访问query接口");
        return service.getPageUser(userDTO);
    }

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R get(@PathVariable("id") String id)
    {
        logger.info("访问query接口");
        return service.getUser(id);
    }

    @RequestMapping(value = "queryTeacher",method = RequestMethod.GET)
    public Map<String,Object> queryTeacher()
    {
        logger.info("访问queryTeacher接口");
       return  service.queryTeacher();
    }

    @RequestMapping(value = "queryUser",method = RequestMethod.GET)
    public Map<String,Object> queryUser()
    {
        logger.info("访问queryUser接口");
        return  service.queryUser();
    }

    @RequestMapping("parseOldPassword")
    public boolean parseOldPassword(String username,String password)
    {
        UserInfo userInfo = service.userLogin(username);
        boolean matches = passwordEncoder.matches(password,userInfo.getPassword());
        return matches;
    }

    @RequestMapping(value = "queryUserByRole",method = RequestMethod.GET)
    public R getUserByRole(@RequestParam(value="role")String role)
    {
        logger.info("getUserByRole");
        return service.getUserByRole(role);
    }

    @RequestMapping(value="insert",method = RequestMethod.POST)
    public R insert(@RequestBody UserInfo user) {
        logger.info("insert");
        String password = user.getPassword();
        user.setPassword( passwordEncoder.encode(password));
        return service.insert(user);
    }

    @RequestMapping(value="modify",method = RequestMethod.PUT)
    public R update(@RequestBody UserInfo user)
    {
        logger.info("update");
        if(user.getPassword()!=null) {
            String pwd=user.getPassword();
            user.setPassword(passwordEncoder.encode(pwd));
        }
        return service.update(user);
    }

    //重置密码
    @RequestMapping(value = "resetPassword",method = RequestMethod.PUT)
    public boolean resetPassword(@RequestParam("idList") List<String> idList)
    {
        String password=passwordEncoder.encode("000000");
        boolean result=false;
        for (String id:idList)
        {
            service.resetPwd(id, password);
            result=true;
        }
        return result;
    }

    @RequestMapping(value="delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList){
        logger.info("delete");
        return service.delete(idList);
    }


    /**
     * 角色
     */

    @RequestMapping(value = "userRole/query",method = RequestMethod.GET)
    public Map<String,Object> userRoleSelectAll(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize){
        logger.info("userRoleSelectAll");
        return service.userRoleSelectAll(pageNum,pageSize);
    }

    @RequestMapping(value = "userRole/queryRole",method = RequestMethod.GET)
    public Map<String,Object> queryRole()
    {
        logger.info("queryRole");
        return  service.queryRole();
    }

    @RequestMapping(value = "userRole/query/{id}",method = RequestMethod.GET)
    public R userRoleSelectOne(@PathVariable("id") Serializable id){
        logger.info("userRoleSelectOne");
        return  service.userRoleSelectOne(id);
    }

    @RequestMapping(value = "userRole/insert",method = RequestMethod.POST)
    public R userRoleInsert(@RequestBody UserRole userRole){
        logger.info("userRoleInsert");
        return  service.userRoleInsert(userRole);
    }


    @RequestMapping(value = "userRole/modify",method = RequestMethod.PUT)
    public R userRoleUpdate(@RequestBody UserRole userRole){
        logger.info("userRoleUpdate");
        return  service.userRoleUpdate(userRole);
    }

    @RequestMapping(value = "userRole/delete",method = RequestMethod.DELETE)
    public R userRoleDelete(@RequestParam("idList") List<String> idList){
        logger.info("userRoleDelete");
        return  service.userRoleDelete(idList);
    }

}
