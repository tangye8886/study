package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.CourseOrder;

import java.util.List;

/**
 * (Order)表服务接口
 *
 * @author makejava
 * @since 2019-12-26 23:24:42
 */
public interface OrderService extends IService<CourseOrder> {
    public  List<CourseOrder> getAll();

    public  List<CourseOrder> getAllByCondition(OrderDTO rderDTO);

    public List<CourseOrder>  getTeacherOrder(String teacher);
}