package com.hongtao.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2019-12-21 00:18:40
 */
@SuppressWarnings("serial")
@TableName("user_info")
public class UserInfo extends Model<UserInfo> {

    private String id; //主键

    private String username;//用户名

    private String password;//密码

    private String phone;//联系方式

    private String address;//地址

    private Integer score;//积分
    
    private Double yue;//余额
    
    private Integer status;//状态
    //角色
    private Integer role;//角色

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date updatime;//修改时间
    
    private String updater;//修改人

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date creatime;//创建时间
    
    private String creater;//创建人

    private Integer flag;//是否管理员标记

    private String image;  //头像

    private String info;//个人描述

    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @TableField(exist = false)
    private UserRole roleDetail;


    public UserRole getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(UserRole roleDetail) {
        this.roleDetail = roleDetail;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getYue() {
        return yue;
    }

    public void setYue(Double yue) {
        this.yue = yue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatime() {
        return updatime;
    }

    public void setUpdatime(Date updatime) {
        this.updatime = updatime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", yue=" + yue +
                ", status=" + status +
                ", role=" + role +
                ", updatime=" + updatime +
                ", updater='" + updater + '\'' +
                ", creatime=" + creatime +
                ", creater='" + creater + '\'' +
                ", flag=" + flag +
                ", image='" + image + '\'' +
                ", info='" + info + '\'' +
                ", roleDetail=" + roleDetail +
                '}';
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