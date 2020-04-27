package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.CommentDTO;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.service.dao.CommentDao;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.dao.UserDao;
import com.hongtao.service.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2019-11-27 21:36:02
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements CommentService {

    @Resource
    CommentDao dao;

    @Resource
    UserDao userDao;

    @Resource
    CourseDao courseDao;


    @Override
    public List<Comment> getAllCommnetByCondition(CommentDTO commentDTO) {
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        if(commentDTO.getStatus()!=null && !"".equals(commentDTO.getStatus()))
        {
            queryWrapper.eq("status",commentDTO.getStatus());
        }
        if(StringUtils.isNotBlank(commentDTO.getPid())) {
            queryWrapper.eq("pid",commentDTO.getPid());
        }
        if(StringUtils.isNotBlank(commentDTO.getUser())) {
            queryWrapper.eq("user",commentDTO.getUser());
        }
        if(StringUtils.isNotBlank(commentDTO.getContent())) {
            queryWrapper.like("content",commentDTO.getContent());
        }
        if(commentDTO.getRoot()!=null) {
            queryWrapper.like("root",commentDTO.getRoot());
        }
        queryWrapper.orderByDesc("creatime");

        List<Comment> comments=null;

        if(commentDTO.getRole()!=null && !"".equals(commentDTO.getRole())) //教师  只返回教师创建的课程的评论
        {
            if(commentDTO.getRole()==2)  //讲师查询讲师的课程有关的评论
            {
                comments=dao.queryCommentByTeacherCourse(commentDTO.getCreater());
            }
            if(commentDTO.getRole()==1)  //管理员查询所有
            {
                comments=dao.selectList(queryWrapper);
            }
            if(commentDTO.getRole()==3||commentDTO.getRole()==4) // 学生查询自己评论的内容
            {
                QueryWrapper<Comment> studyWrapper=new QueryWrapper<>();
                studyWrapper.eq("user",commentDTO.getUser());
                comments = dao.selectList(studyWrapper);
            }
        }else{   //课程信息界面  查询跟课程相关的评论
            QueryWrapper<Comment> courseWrapper=new QueryWrapper<>();
            courseWrapper.eq("root",1); //只从一级菜单开始列起
            courseWrapper.eq("status",1);  //只显示正常状态下的评论
            courseWrapper.eq("pid",commentDTO.getPid());  //课程ID
            List<Comment> commentList = dao.selectList(courseWrapper);
            comments=initComment(commentList);
        }

        for(Comment c:comments)
        {
            UserInfo user = userDao.selectById(c.getUser());
            Course course = courseDao.selectById(c.getPid());
            c.setUserDetail(user);
            c.setCourseDetail(course);
            if(c.getParent()!=null && !c.getParent().equals(""))
            {
                Comment comment = dao.selectById(c.getParent());
                c.setParentComment(comment);
            }
        }
        return comments;
    }


    public List<Comment> initComment(List<Comment> comments)
    {
        if(comments.size()>0)
        {
            for(Comment comment:comments)
            {
                comment.setChildren(getChildren(comment));
            }
        }
        return comments;
    }

    public List<Comment> getChildren(Comment parentComment){
        List<Comment> commentList=new ArrayList<>();
        if(parentComment!=null)
        {
            List<Comment> commentChildren = dao.getCommentChildren(parentComment.getId());
            if(commentChildren.size()>0)
            {
                for(Comment c:commentChildren)
                {
                    UserInfo user = userDao.selectById(c.getUser());
                    Course course = courseDao.selectById(c.getPid());
                    c.setUserDetail(user);
                    c.setCourseDetail(course);
                    if(c.getParent()!=null && !c.getParent().equals(""))
                    {
                        Comment comment = dao.selectById(c.getParent());
                        c.setParentComment(comment);
                    }
                    c.setChildren(getChildren(c));
                    commentList.add(c);
                }
            }
        }
        return commentList;
    }



}