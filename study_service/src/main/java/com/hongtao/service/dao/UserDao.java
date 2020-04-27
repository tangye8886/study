package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2019-11-26 23:12:16
 */
public interface UserDao extends BaseMapper<UserInfo> {

    /*
        根据username 查询用户的角色code
     */
    @Select("select r.code from user_info u,user_role r where u.role=r.id and u.username=#{username}")
    String findRoleCodeByUserName(@Param("username") String username);


    @Update("update user_info set password=#{password} where id=#{id}")
    public Integer resetPwd(@Param("id") String id, @Param("password") String password);

}