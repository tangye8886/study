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
 * (Chapter)表实体类      章节
 *
 * @author makejava
 * @since 2019-12-29 21:40:19
 */

@SuppressWarnings("serial")
@TableName("chapter")
public class Chapter extends Model<Chapter> {

    //@TableId(type = IdType.UUID)
    private String id;   //主键ID
    
    private String name;//名称
    
    private String content;//内容
    
    private String course; //所属课程ID

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss:")
    private Date creatime;

    private String creater;

    @TableField(exist = false)
    private List<Vedio> vedioChildren;

    @TableField(exist = false)
    private Course courseDetail;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Vedio> getVedioChildren() {
        return vedioChildren;
    }

    public void setVedioChildren(List<Vedio> vedioChildren) {
        this.vedioChildren = vedioChildren;
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