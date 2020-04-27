package com.hongtao.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.CourseOrder;
import com.hongtao.user.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user/order")
public class OrderController {


    @Resource
    OrderService orderService;


    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody OrderDTO orderDTO)
    {
        return orderService.query(orderDTO);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable(value = "id") Serializable id) {
       return orderService.selectOne(id);
    }

     /**
     * 新增订单
     *
     * @param courseOrder 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "insert",method =RequestMethod.POST)
    public R insert(@RequestBody CourseOrder courseOrder) {
        courseOrder.setId(AliPayController.getOrderIdByTime());
        return orderService.insert(courseOrder);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method =RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return orderService.delete(idList);
    }
}
