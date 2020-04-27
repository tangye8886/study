package com.hongtao.user.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.VedioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.Map;

@FeignClient(value = "study-service")
public interface VedioService {

    @RequestMapping(value = "api/vedio/query",method = RequestMethod.POST)
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO);

    @RequestMapping(value="api/vedio/query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id);

}
