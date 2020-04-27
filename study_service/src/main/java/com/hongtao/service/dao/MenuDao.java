package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuDao extends BaseMapper<Menu>{

    @Select("select m.* from menu m where id in (select menuid from role_menu r where m.status=1 and m.pid='0' and r.roleid=#{roleid})")
    List<Menu> getRootMenuByRole(@Param(value = "roleid") String roleid);
}
