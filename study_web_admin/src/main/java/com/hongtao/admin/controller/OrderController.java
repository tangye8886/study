package com.hongtao.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.OrderService;
import com.hongtao.admin.utils.MyUtils;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.CourseOrder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/order/")
public class OrderController {


    @Resource
    OrderService orderService;

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody OrderDTO orderDTO)
    {
        return orderService.query(orderDTO);
    }


    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable(value = "id") Serializable id) {
       return orderService.selectOne(id);
    }


    @RequestMapping(value = "insert",method =RequestMethod.POST)
    public R insert(@RequestBody CourseOrder courseOrder) {
        courseOrder.setId(MyUtils.makeCommentNo());
        return orderService.insert(courseOrder);
    }


    @RequestMapping(value = "modify",method =RequestMethod.PUT)
    public R update(@RequestBody CourseOrder courseOrder) {
        return orderService.update(courseOrder);
    }


    @RequestMapping(value = "delete",method =RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return orderService.delete(idList);
    }
}
