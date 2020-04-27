package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Vedio;
import com.hongtao.service.dao.ChapterDao;
import com.hongtao.service.dao.VedioDao;
import com.hongtao.service.service.VedioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (CourseVedio)表服务实现类
 *
 * @author makejava
 * @since 2019-11-27 21:36:16
 */
@Service("courseVedioService")
public class VedioServiceImpl extends ServiceImpl<VedioDao, Vedio> implements VedioService {

    @Resource
    VedioDao dao;

    @Resource
    ChapterDao chapterDao;

    @Resource
    HttpServletRequest httpServletRequest;


    @Override
    public List<Vedio> getAll() {
        return dao.selectList(null);
    }

    @Override
    public List<Vedio> getAllByCondition(VedioDTO vedioDTO) {
        QueryWrapper<Vedio> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(vedioDTO.getTitle()))
        {
            queryWrapper.like("title",vedioDTO.getTitle());
        }

        //创建者条件过滤   只有创建的人才能看到自己的数据
        if(StringUtils.isNotBlank(vedioDTO.getCreater()))
        {
            if(vedioDTO.getRole()!=null && vedioDTO.getRole()==2)  //教师
            {
                queryWrapper.eq("creater",vedioDTO.getCreater());
            }

            if(vedioDTO.getRole()!=null && vedioDTO.getRole()!=1 && vedioDTO.getRole()!=2) //用户 只显示正常状态下的课程
            {
                queryWrapper.eq("status",1);
            }
        }

        queryWrapper.orderByAsc("creatime");

        List<Vedio> vedios = dao.selectList(queryWrapper);
        for(Vedio v:vedios)
        {
            Chapter chapter = chapterDao.selectById(v.getChapter());
            v.setChapterDetail(chapter);
        }
        return vedios;
    }

    @Override
    public List<Vedio> findVedioByChapter(String chapterID) {
        QueryWrapper<Vedio> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter",chapterID);
        return dao.selectList(queryWrapper);
    }

    @Override
    public Vedio getOneVedio(String id) {
        return dao.getOneVedio(id);
    }
}