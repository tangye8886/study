package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2019-11-27 21:36:02
 */
public interface CommentDao extends BaseMapper<Comment> {

    //用户教师评论管理 只显示教师的课程里面的评论
    @Select(value = "select * from comment where pid in (select id from course WHERE teacher=#{teacher})")
    public List<Comment> queryCommentByTeacherCourse(@Param("teacher") String teacher);


    //返回一级评论的二级菜单
    @Select(value = "SELECT * from  comment where parent=#{id}")
    public List<Comment> getCommentChildren(@Param("id") String id);

}