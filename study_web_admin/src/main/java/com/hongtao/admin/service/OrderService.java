package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.OrderDTO;
import com.hongtao.common.entity.CourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@FeignClient(value = "study-service")
public interface OrderService {


    @RequestMapping(value = "api/order/query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody OrderDTO orderDTO);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value ="api/order/query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable(value = "id") Serializable id);

    @RequestMapping(value = "api/order/query2/{id}",method =RequestMethod.GET)
    public CourseOrder query2(@PathVariable(value = "id") Serializable id);

    /**
     * 新增订单
     *
     * @param courseOrder 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "api/order/insert",method =RequestMethod.POST)
    public R insert(@RequestBody CourseOrder courseOrder);

    /**
     * 修改数据
     *
     * @param courseOrder 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "api/order/modify",method =RequestMethod.PUT)
    public R update(@RequestBody CourseOrder courseOrder);

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "api/order/delete",method =RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList);

}
