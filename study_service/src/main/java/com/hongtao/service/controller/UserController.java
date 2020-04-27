package com.hongtao.service.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.UserDTO;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.service.service.FdfsService;
import com.hongtao.service.service.UserRoleService;
import com.hongtao.service.service.UserService;
import com.hongtao.service.utils.ExcelUtil;
import com.hongtao.service.utils.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2019-11-26 23:12:20
 */
@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController extends ApiController {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private FdfsService fdfsService;


    @Value("${server.port}")
    String port;

    /**
     * 分页查询数据
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> selectAll(@RequestBody UserDTO userDTO)
   {
       PageHelper.startPage(userDTO.getPageNum(), userDTO.getPageSize());
       Map<String,Object> map=new HashMap<String,Object>();
       List<UserInfo> allUser = userService.getAllByCondition(userDTO);
       PageInfo<UserInfo> page = new PageInfo<UserInfo>(allUser);
       map.put("data",page);
       return map;
    }


    @RequestMapping("queryTeacher")
    public Map<String,Object> queryTeacher()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data",userService.getAllTeacher());
        return map;
    }

    /**
     * 根据查询数据
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R getUser(@PathVariable("id") String id)
    {
        UserInfo user = this.userService.getById(id);
        user.setRoleDetail(userRoleService.getById(user.getRole()));
        return success(user);
    }

    @RequestMapping("queryUser")
    public Map<String,Object> queryUser()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("data",userService.getAllUser());
        return map;
    }

    /**
     * 用户登陆
     */
    @RequestMapping(value = "userLogin",method = RequestMethod.GET)
    public UserInfo userLogin(@RequestParam(value="username")String username)
    {
        return userService.findUserByUserName(username);
    }

    /**
     * 根据角色ID查询用户
     */
    @RequestMapping(value = "queryUserByRole",method = RequestMethod.GET)
    public R getUserByRole(@RequestParam(value="role")String role)
    {
        return success(this.userService.getUserByRole(role));
    }

    /**
     * 新增数据
     */
    @RequestMapping(value="insert",method = RequestMethod.POST)
    public R insert(@RequestBody UserInfo user) {
        user.setId(MyUtils.makeCommentNo());
        user.setYue(0.00);
        user.setScore(0);
        user.setStatus(1);
        user.setCreatime(new Date());
        if(user.getRole()!=null)
        {
            user.setFlag(user.getRole()==1?1:user.getRole()==2?1:0); //1 2 管理员
        }
        return success(this.userService.save(user));
    }

    /**
     * 修改数据
     */
    @RequestMapping(value="modify",method = RequestMethod.PUT)
    public R update(@RequestBody UserInfo user) {
        user.setUpdatime(new Date());
        return success(this.userService.updateById(user));
    }

    @RequestMapping(value="resetPwd",method = RequestMethod.PUT)
    public void resetPwd(@RequestParam("id") String id,@RequestParam("password")String password) {
        userService.resetPwd(id,password);
    }

    /**
     * 删除数据
     */
    @RequestMapping(value="delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        if(idList.size()>0)
        {
            for(String id:idList)
            {
                if(StringUtils.isNotBlank(userService.getById(id).getImage()))
                {
                    fdfsService.deleteFile(userService.getById(id).getImage());  //删除掉文件服务器用户的头像
                }
            }
        }
        return success(this.userService.removeByIds(idList));
    }


    //导出用户数据
    @RequestMapping("/ExcelUserInfo")
    public void excelDownload(HttpServletResponse response) throws IOException {

        List<List<String>> excelData = new ArrayList<>();

        List<String> head = new ArrayList<>();

        head.add("编号");
        head.add("用户名");
        head.add("密码");
        head.add("联系电话");
        head.add("地址");
        head.add("状态");
        head.add("个人描述");
        excelData.add(head);
        for (UserInfo user:userService.list(null))
        {
            List<String> data=Arrays.asList(user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getAddress(),user.getStatus()+"",user.getInfo());
            excelData.add(data);
        }

        String sheetName = "UserInfo";
        String fileName = "UserInfo.xls";
        ExcelUtil.exportExcel(response, excelData, sheetName, fileName, 15);
    }
}