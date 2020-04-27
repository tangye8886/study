package com.hongtao.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Order)表实体类
 *
 * @author makejava
 * @since 2019-12-26 23:23:14
 */
@SuppressWarnings("serial")
@TableName(value = "course_order")
public class CourseOrder extends Model<CourseOrder> {

    private String id;  //主键ID
    
    private String uid; //提交订单者ID
    
    private String cid;// 课程ID
    
    private String address; //收件地址
    
    private String phone;//联系电话
    
    private String person; //收件人
    
    private Integer count;//数量
    
    private Double price;//价格
    
    private Integer status;//状态

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date creatime;//创建时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date paytime;//支付时间
    
    private Integer paystatus;//支付状态

    //  默认一个订单一个课程

    @TableField(exist = false)
    private Course detailCourse;  //订单详情

    @TableField(exist = false)
    private UserInfo detailUser;  //订单详情


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public UserInfo getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(UserInfo detailUser) {
        this.detailUser = detailUser;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    public Course getDetailCourse() {
        return detailCourse;
    }

    public void setDetailCourse(Course detailCourse) {
        this.detailCourse = detailCourse;
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