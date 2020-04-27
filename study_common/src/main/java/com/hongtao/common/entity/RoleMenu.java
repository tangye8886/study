package com.hongtao.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (RoleMenu)表实体类    菜单-角色关联实体
 *
 * @author makejava
 * @since 2019-12-20 23:02:37
 */
@SuppressWarnings("serial")
@TableName("role_menu")
public class RoleMenu extends Model<RoleMenu> {

    private String id;//主键ID
    
    private String roleid; //角色ID
    
    private String menuid;//菜单


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public RoleMenu(String id, String roleid, String menuid) {
        this.id = id;
        this.roleid = roleid;
        this.menuid = menuid;
    }

    public RoleMenu() {
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }