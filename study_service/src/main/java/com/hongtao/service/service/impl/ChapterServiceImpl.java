package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.ChapterDTO;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Course;
import com.hongtao.service.dao.ChapterDao;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.service.ChapterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Chapter)表服务实现类
 *
 * @author makejava
 * @since 2019-12-29 21:39:17
 */
@Service("chapterService")
public class ChapterServiceImpl extends ServiceImpl<ChapterDao, Chapter> implements ChapterService {

    @Resource
    ChapterDao dao;

    @Resource
    CourseDao courseDao;

    @Resource
    HttpServletRequest httpServletRequest;

    @Override
    public List<Chapter> getAll() {
        List<Chapter> chapters = dao.selectList(null);
        for(Chapter c:chapters)
        {
            Course course = courseDao.selectById(c.getCourse());
            c.setCourseDetail(course);
        }
        return chapters;
    }

    @Override
    public List<Chapter> getAllByCondition(ChapterDTO chapterDTO) {
        QueryWrapper<Chapter> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(chapterDTO.getCourse()))
        {
            queryWrapper.eq("course",chapterDTO.getCourse());
        }
        if(StringUtils.isNotBlank(chapterDTO.getContent())) {
            queryWrapper.like("content", chapterDTO.getContent());
        }
        //创建者条件过滤 教师角色只能显示自己创建的记录
        if(StringUtils.isNotBlank(chapterDTO.getCreater()))
        {
            if(chapterDTO.getRole()!=null && chapterDTO.getRole()==2)
            {
                queryWrapper.eq("creater",chapterDTO.getCreater());
            }
            //普通用户 只显示正常状态下的章节
            if(chapterDTO.getRole()!=null && chapterDTO.getRole()!=1 && chapterDTO.getRole()!=2)
            {
                queryWrapper.eq("status",1);
            }
        }

        queryWrapper.orderByAsc("creatime");
        List<Chapter> chapters = dao.selectList(queryWrapper);
        for(Chapter c:chapters)
        {
            Course course = courseDao.selectById(c.getCourse());
            c.setCourseDetail(course);
        }
        return  chapters;
    }

    @Override
    public List<Chapter> getChapterByCourse(String courseID) {
        QueryWrapper<Chapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course",courseID);
        queryWrapper.orderByAsc("creatime");
        return dao.selectList(queryWrapper);
    }
}