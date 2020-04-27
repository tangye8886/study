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
 * (Comment)表实体类
 *
 * @author makejava
 * @since 2019-11-27 00:05:00
 */
@SuppressWarnings("serial")
@TableName("comment")
public class Comment extends Model<Comment> {

    private String id;//主键
    
    private String pid;//课程ID
    
    private String content;//内容
    
    private String user;//评论者ID
    
    private Integer status;//状态

    private String parent;//评论

    @TableField(exist = false)
    private Comment parentComment;

    private Integer root;//是否是一级评论

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date updatime;//修改时间
    
    private String updater;//修改人

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date creatime;//创建时间
    
    private String creater;///创建人

    @TableField(exist = false)
    private UserInfo userDetail;

    @TableField(exist = false)
    private Course courseDetail;

    @TableField(exist = false)
    private List<Comment> children; //二级评论

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    public UserInfo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserInfo userDetail) {
        this.userDetail = userDetail;
    }

    public Course getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(Course courseDetail) {
        this.courseDetail = courseDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
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