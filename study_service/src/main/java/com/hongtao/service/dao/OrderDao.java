package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.CourseOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Order)表数据库访问层
 *
 * @author makejava
 * @since 2019-12-26 23:24:42
 */
public interface OrderDao extends BaseMapper<CourseOrder> {

    //查询老师的订单
    @Select(value = "select * from course_order where cid in (select id from course where teacher=#{teacher})")
    public List<CourseOrder>  getTeacherOrder(@Param("teacher") String teacher);

}