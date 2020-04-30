package com.hongtao.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.utils.TokenUtils;
import com.hongtao.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user/userInfo")
public class UserController {

    @Resource
    UserService service;

    @Resource
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R get(@PathVariable("id") String id)
    {
        return service.getUser(id);
    }

    @RequestMapping(value = "queryTeacher",method = RequestMethod.GET)
    public Map<String,Object> queryTeacher()
    {
       return  service.queryTeacher();
    }

    @RequestMapping(value = "userLogin",method = RequestMethod.GET)
    public Map<String,Object>  userLogin(@RequestParam(value="username") String username,
                   @RequestParam(value="password")String password,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        UserInfo userInfo = service.userLogin(username);
        if(userInfo!=null)
        {
            String pwd=userInfo.getPassword();
            boolean matches = passwordEncoder.matches(password, pwd);
            if(matches)  //密码匹配成功
            {
                String token = TokenUtils.makeToken(username, password);
                session.setAttribute("userInfo",userInfo);
                session.setAttribute("token",token);
                map.put("data",userInfo);
                map.put("token",token);
                map.put("result",1);
            }else {
                map.put("data",null);
                map.put("result",0);
            }
        }else {
            map.put("data",null);
            map.put("result",0);
        }

        return map;
    }

    @RequestMapping(value = "existUsername",method = RequestMethod.GET)
    public boolean existUsername(@RequestParam(value="username")String username)
    {
        UserInfo userInfo = service.userLogin(username);
        if(userInfo!=null)
        {
            return true;
        }
        return false;
    }



    @RequestMapping(value = "queryUserByRole",method = RequestMethod.GET)
    public R getUserByRole(@RequestParam(value="role")String role)
    {
        return service.getUserByRole(role);
    }

    //用于用户注册
    @RequestMapping(value="insert",method = RequestMethod.POST)
    public R insert(@RequestBody UserInfo user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return service.insert(user);
    }

    @RequestMapping(value="modify",method = RequestMethod.PUT)
    public R update(@RequestBody UserInfo user)
    {
        return service.update(user);
    }

    @RequestMapping(value="changPwd",method = RequestMethod.PUT)
    public void changPwd(@RequestBody UserInfo user)
    {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        service.update(user);
    }
}
