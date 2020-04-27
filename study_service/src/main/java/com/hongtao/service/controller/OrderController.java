package com.hongtao.service.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.CourseOrder;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.service.service.CourseService;
import com.hongtao.service.service.OrderService;
import com.hongtao.service.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2019-12-26 23:24:42
 */
@CrossOrigin
@RestController
@RequestMapping("api/order")
public class OrderController extends ApiController {



    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;


    /**
     * 分页查询数据
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody OrderDTO orderDTO)
    {
        PageHelper.startPage(orderDTO.getPageNum(), orderDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<CourseOrder> allOrder =null;

        if(orderDTO.getRole()!=null && !orderDTO.getRole().equals(""))
        {
            if(orderDTO.getRole()==2) // 教师角色查询自己的课程的订单
            {
                allOrder=orderService.getTeacherOrder(orderDTO.getUid());
            }else   //管理员跟其他用户根据条件查询
            {
                allOrder=orderService.getAllByCondition(orderDTO);
            }
        }else {
            throw new RuntimeException("角色不能为空!");
        }

        PageInfo<CourseOrder> page = new PageInfo<CourseOrder>(allOrder);
        map.put("data",page);
        return map;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "query/{id}",method =RequestMethod.GET)
    public R selectOne(@PathVariable(value = "id") Serializable id) {
        //查询某个订单
        CourseOrder courseOrder = this.orderService.getById(id);
        if(courseOrder !=null)
        {
            //订单重对应的课程
            Course course = courseService.getById(courseOrder.getCid());
            UserInfo user = userService.getById(courseOrder.getUid());
            courseOrder.setDetailCourse(course);
            courseOrder.setDetailUser(user);
        }
        return success(courseOrder);
    }

    @RequestMapping(value = "query2/{id}",method =RequestMethod.GET)
    public CourseOrder query2(@PathVariable(value = "id") Serializable id) {
        //查询某个订单
        return orderService.getById(id);
    }

    /**
     * 新增订单
     *
     * @param courseOrder 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "insert",method =RequestMethod.POST)
    public R insert(@RequestBody CourseOrder courseOrder) {
        //courseOrder.setId(MyUtils.makeCommentNo());
        //courseOrder.setCid(MyUtils.getCourseNo());
        courseOrder.setCreatime(new Date());
        boolean saveResult= this.orderService.save(courseOrder);
        return success(saveResult);
    }

    /**
     * 修改数据
     *
     * @param courseOrder 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "modify",method =RequestMethod.PUT)
    public R update(@RequestBody CourseOrder courseOrder) {
        return success(this.orderService.updateById(courseOrder));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method =RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.orderService.removeByIds(idList));
    }
}