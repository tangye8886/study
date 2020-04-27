package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.common.entity.Vedio;

import java.util.List;

/**
 * (CourseVedio)表服务接口
 *
 * @author makejava
 * @since 2019-11-27 21:36:16
 */
public interface VedioService extends IService<Vedio> {

    public List<Vedio> getAll();

    public List<Vedio> getAllByCondition(VedioDTO vedioDTO);

    public List<Vedio> findVedioByChapter(String chapterID);

    public Vedio getOneVedio(String id);
}