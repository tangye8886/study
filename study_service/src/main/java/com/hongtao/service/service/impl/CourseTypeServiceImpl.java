package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.CourseTypeDTO;
import com.hongtao.common.entity.CourseType;
import com.hongtao.service.dao.CourseTypeDao;
import com.hongtao.service.service.CourseTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CourseType)表服务实现类
 *
 * @author makejava
 * @since 2020-01-09 21:55:03
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseType> implements CourseTypeService {

    @Resource
    CourseTypeDao courseTypeDao;

    @Override
    public List<CourseType> getCourseTypeByCondition(CourseTypeDTO courseTypeDTO) {
        QueryWrapper<CourseType> queryWrapper=new QueryWrapper<>();

        if(courseTypeDTO.getName()!=null && !"".equals(courseTypeDTO.getName()))
        {
            queryWrapper.like("name",courseTypeDTO.getName());
        }

        //创建者条件过滤 教师角色只能显示自己创建的记录
        if(courseTypeDTO.getCreater()!=null && !"".equals(courseTypeDTO.getCreater()))
        {
            if(courseTypeDTO.getRole()!=null && courseTypeDTO.getRole()==2)
            {
                queryWrapper.eq("creater",courseTypeDTO.getCreater());
            }

            if(courseTypeDTO.getRole()!=null && courseTypeDTO.getRole()!=1 && courseTypeDTO.getRole()!=2) //用户 只显示正常状态下的课程
            {
                queryWrapper.eq("status",1);
            }
        }

        return courseTypeDao.selectList(queryWrapper);
    }
}