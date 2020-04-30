package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.common.entity.Options;
import com.hongtao.common.entity.Vedio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@FeignClient(value = "study-service")
public interface VedioService {

    @RequestMapping(value = "api/vedio/query",method = RequestMethod.POST)
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO);

    @RequestMapping(value="api/vedio/query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id);

    @RequestMapping(value="api/vedio/insert",method = RequestMethod.POST)
    public R insert(@RequestBody Vedio courseVedio);


    @RequestMapping(value="api/vedio/modify",method = RequestMethod.PUT)
    public R update(@RequestBody Vedio courseVedio);

    @RequestMapping(value="api/vedio/delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList);

    @RequestMapping(value="api/vedio/queryJLByCourse",method = RequestMethod.POST)
    public List<Options> queryJLByCourse(@RequestBody CourseDTO courseDTO);



}
