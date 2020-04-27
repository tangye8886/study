package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.dao.UserDao;
import com.hongtao.service.service.CourseService;
import com.hongtao.service.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Course)表服务实现类
 *
 * @author makejava
 * @since 2019-11-27 21:35:46
 */
@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {

    @Resource
    CourseDao courseDao;

    @Resource
    UserDao userDao;

    @Resource
    RedisUtil redisUtil;

    @Resource
    HttpServletRequest httpServletRequest;

    @Override
    public List<Course> getAll() {
        List<Course> courses = courseDao.selectList(null);
        for(Course c:courses)
        {
            UserInfo user = userDao.selectById(c.getTeacher());
            c.setTeacherDetail(user);
        }
        return courses;
    }

    @Override
    public List<Course> getAllByCondition(CourseDTO courseDTO) {
        QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(courseDTO.getTitle()))
        {
            queryWrapper.like("title",courseDTO.getTitle());
        }
        if(StringUtils.isNotBlank(courseDTO.getType()))
        {
            queryWrapper.eq("type",courseDTO.getType());
        }
        if(courseDTO.getStatus()!=null && !"".equals(courseDTO.getStatus())) {
            queryWrapper.eq("status", courseDTO.getStatus());
        }
        if(StringUtils.isNotBlank(courseDTO.getTeacher()))
        {
            queryWrapper.eq("teacher",courseDTO.getTeacher());
        }
        if(StringUtils.isNotBlank(courseDTO.getIsFree()))
        {
            if(courseDTO.getIsFree().equals("1"))  //收费标记
            {
                queryWrapper.gt("price",0);  //大于0
            }
        }
        //角色条件过滤
        if(StringUtils.isNotBlank(courseDTO.getCreater()))
        {
            if(courseDTO.getRole()!=null && courseDTO.getRole()==2) //教师才过滤 用户跟管理员都显示所有数据
            {
                queryWrapper.eq("creater",courseDTO.getCreater());
            }
            if(courseDTO.getRole()!=null && courseDTO.getRole()!=1 && courseDTO.getRole()!=2) //用户 只显示正常状态下的课程
            {
                queryWrapper.eq("status",1);
            }
        }

        List<Course> courses = courseDao.selectList(queryWrapper);
        for(Course c:courses)
        {
            UserInfo user = userDao.selectById(c.getTeacher());
            int love= redisUtil.getSetMember("love:course:"+c.getId(),0).size();
            c.setTeacherDetail(user);
            c.setLoveNumber(love);
        }
        return courses;
    }

}