package com.hongtao.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Course)表实体类
 *
 * @author makejava
 * @since 2019-11-27 00:05:00
 */
@SuppressWarnings("serial")
@TableName("course")
public class Course extends Model<Course> {

    //@TableId(type = IdType.UUID)
    private String id; //主键ID
    
    private String title;//标题u
    
    private String content;//内容
    
    private String teacher;//教师
    
    private Double price;//价格
    
    private Integer status;//状态
    
    private String image;//图片

    private String type;//类型

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date updatime;
    
    private String updater;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date creatime;
    
    private String creater;

    @TableField(exist = false)
    private List<Chapter> chapterChildren;  //章节

    @TableField(exist = false)
    private UserInfo teacherDetail;   //教师

    ///收藏人数
    @TableField(exist = false)
    private Integer loveNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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

    public List<Chapter> getChapterChildren() {
        return chapterChildren;
    }

    public void setChapterChildren(List<Chapter> chapterChildren) {
        this.chapterChildren = chapterChildren;
    }

    public UserInfo getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(UserInfo teacherDetail) {
        this.teacherDetail = teacherDetail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(Integer loveNumber) {
        this.loveNumber = loveNumber;
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