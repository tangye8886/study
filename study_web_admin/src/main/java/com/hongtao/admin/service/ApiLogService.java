package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.CommonDTO;
import com.hongtao.common.entity.ApiLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@FeignClient(value = "study-service")
public interface ApiLogService {

    @RequestMapping(value ="api/log/query",method = RequestMethod.POST)
    public Map<String,Object> getApiLogByPage(@RequestBody CommonDTO commonDTO);

    @RequestMapping(value = "api/log/query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id);

    @RequestMapping(value = "api/log/insert",method = RequestMethod.POST)
    public R insert(@RequestBody ApiLog ApiLog);

    @RequestMapping(value = "api/log/modify",method = RequestMethod.PUT)
    public R update(@RequestBody ApiLog ApiLog);

    @RequestMapping(value = "api/log/delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList);

}
