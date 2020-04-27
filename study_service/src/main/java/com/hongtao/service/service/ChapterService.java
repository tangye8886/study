package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.ChapterDTO;
import com.hongtao.common.entity.Chapter;

import java.util.List;

/**
 * (Chapter)表服务接口
 *
 * @author makejava
 * @since 2019-12-29 21:39:17
 */
public interface ChapterService extends IService<Chapter> {

    public List<Chapter> getAll();

    public List<Chapter> getAllByCondition(ChapterDTO chapterDTO);

    //根据课程ID 查询该课程的所有章节
    public List<Chapter> getChapterByCourse(String courseID);

}