package com.hongtao.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.ApiLogService;
import com.hongtao.common.dto.CommonDTO;
import com.hongtao.common.entity.ApiLog;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/admin/log")
public class ApiLogController {

    @Resource
    ApiLogService service;

    @RequestMapping(value ="query",method = RequestMethod.POST)
    public Map<String,Object> getApiLogByPage(@RequestBody CommonDTO commonDTO){
        return  service.getApiLogByPage(commonDTO);
    }

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable Serializable id){
        return service.selectOne(id);
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody ApiLog ApiLog){
        return service.insert(ApiLog);
    }

    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody ApiLog ApiLog){
        return service.update(ApiLog);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList,HttpServletRequest request){
        service.delete(idList);
        return null;
    }

}
