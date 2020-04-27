package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.CourseOrder;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.dao.OrderDao;
import com.hongtao.service.dao.UserDao;
import com.hongtao.service.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author makejava
 * @since 2019-12-26 23:24:42
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, CourseOrder> implements OrderService {

    @Resource
    OrderDao dao;

    @Resource
    CourseDao courseDao;

    @Resource
    UserDao userDao;

    @Override
    public List<CourseOrder> getAll() {
        return dao.selectList(null);
    }

    @Override
    public List<CourseOrder> getAllByCondition(OrderDTO orderDTO) {
        QueryWrapper<CourseOrder> queryWrapper=new QueryWrapper<>();
        if(orderDTO.getStatus()!=null && !"".equals(orderDTO.getStatus()))
        {
            queryWrapper.eq("status",orderDTO.getStatus());
        }
        if(orderDTO.getPayStatus()!=null && !"".equals(orderDTO.getPayStatus())) {
            queryWrapper.eq("paystatus",orderDTO.getPayStatus());
        }

        if(StringUtils.isNotBlank(orderDTO.getOrderID()))
        {
            queryWrapper.eq("id",orderDTO.getOrderID());
        }

        if(StringUtils.isNotBlank(orderDTO.getCid()))
        {
            queryWrapper.eq("cid",orderDTO.getCid());
        }
        if(orderDTO.getRole()!=1)
        {           //非管理员只显示用户内容
            if(StringUtils.isNotBlank(orderDTO.getUid()))
            {
                queryWrapper.eq("uid",orderDTO.getUid());
            }
        }

        List<CourseOrder> orderList = dao.selectList(queryWrapper);

        for(CourseOrder order:orderList){
            order.setDetailCourse(courseDao.selectById(order.getCid()));
            order.setDetailUser(userDao.selectById(order.getUid()));
        }
        return orderList;
    }

    @Override
    public List<CourseOrder> getTeacherOrder(String teacher) {
        List<CourseOrder> teacherOrder = dao.getTeacherOrder(teacher);
        for(CourseOrder order:teacherOrder){
            order.setDetailCourse(courseDao.selectById(order.getCid()));
            order.setDetailUser(userDao.selectById(order.getUid()));
        }
        return teacherOrder;
    }
}