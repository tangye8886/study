package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.CourseTypeDTO;
import com.hongtao.common.entity.CourseType;

import java.util.List;

/**
 * (CourseType)表服务接口
 *
 * @author makejava
 * @since 2020-01-09 21:55:03
 */
public interface CourseTypeService extends IService<CourseType> {

    List<CourseType> getCourseTypeByCondition(CourseTypeDTO courseTypeDTO);

}