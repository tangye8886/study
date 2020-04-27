package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.entity.Course;

import java.util.List;

/**
 * (Course)表服务接口
 *
 * @author makejava
 * @since 2019-11-27 21:35:46
 */
public interface CourseService extends IService<Course> {

    List<Course> getAll();

    List<Course> getAllByCondition(CourseDTO courseDTO);
}