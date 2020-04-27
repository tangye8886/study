package com.hongtao.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.CommonDTO;
import com.hongtao.common.entity.ApiLog;
import com.hongtao.service.service.ApiLogService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ApiLog)表控制层
 *
 * @author makejava
 * @since 2020-03-15 22:57:27
 */
@RestController
@RequestMapping("api/log")
public class ApiLogController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ApiLogService apiLogService;

    @RequestMapping(value ="query",method = RequestMethod.POST)
    public Map<String,Object> getApiLogByPage(@RequestBody CommonDTO commonDTO)
    {
        PageHelper.startPage(commonDTO.getPageNum(), commonDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<ApiLog> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("time");
        List<ApiLog> allApiLog = apiLogService.list(queryWrapper);
        PageInfo<ApiLog> page = new PageInfo<ApiLog>(allApiLog);
        map.put("data",page);
        return map;
    }

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id) {
        ApiLog chapter = this.apiLogService.getById(id);
        return success(chapter);
    }


    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody ApiLog ApiLog) {
        ApiLog.setId(MyUtils.makeCommentNo());
        ApiLog.setTime(new Date());
        return success(this.apiLogService.save(ApiLog));
    }


    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody ApiLog ApiLog) {
        return success(this.apiLogService.updateById(ApiLog));
    }


    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.apiLogService.removeByIds(idList));
    }
}