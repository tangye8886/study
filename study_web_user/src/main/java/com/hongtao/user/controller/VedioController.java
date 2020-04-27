package com.hongtao.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.user.service.VedioService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user/vedio/")
public class VedioController {

    @Resource
    VedioService vedioService;

    @RequestMapping(value = "query",method = RequestMethod.POST,produces="application/json")
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO){
        return vedioService.selectAll(vedioDTO);
    }

    @RequestMapping(value="query/{id}",method = RequestMethod.GET,produces="application/json")
    public R selectOne(@PathVariable("id") Serializable id)
    {
        return vedioService.selectOne(id);
    }

}
