package com.hongtao.service.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.CourseTypeDTO;
import com.hongtao.common.entity.CourseType;
import com.hongtao.service.service.CourseTypeService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CourseType)表控制层
 *
 * @author makejava
 * @since 2020-01-09 21:55:03
 */
@CrossOrigin
@RestController
@RequestMapping("api/courseType")
public class CourseTypeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseTypeService courseTypeService;


    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody CourseTypeDTO courseTypeDTO)
    {
        PageHelper.startPage(courseTypeDTO.getPageNum(), courseTypeDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<CourseType> allCourse = courseTypeService.getCourseTypeByCondition(courseTypeDTO);
        PageInfo<CourseType> page = new PageInfo<CourseType>(allCourse);
        map.put("data",page);
        return map;
    }


    @RequestMapping(value = "queryAll",method = RequestMethod.GET)
    public List<CourseType> selectAll() {
        return courseTypeService.list();
    }


    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id) {
        return success(this.courseTypeService.getById(id));
    }


    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody CourseType courseType) {
        courseType.setId(MyUtils.makeCommentNo());
        courseType.setCreatime(new Date());
        return success(this.courseTypeService.save(courseType));
    }

    /**
     * 修改数据
     *
     * @param courseType 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody CourseType courseType) {
        return success(this.courseTypeService.updateById(courseType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.courseTypeService.removeByIds(idList));
    }
}