package com.hongtao.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.user.service.FdfsService;
import com.hongtao.user.service.VedioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/user/vedio/")
public class VedioController {

    @Resource
    VedioService vedioService;

    @Resource
    FdfsService fdfsService;

    @RequestMapping(value = "query",method = RequestMethod.POST,produces="application/json")
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO){
        return vedioService.selectAll(vedioDTO);
    }

    @RequestMapping(value="query/{id}",method = RequestMethod.GET,produces="application/json")
    public R selectOne(@PathVariable("id") Serializable id)
    {
        return vedioService.selectOne(id);
    }

    //文件上传
    @RequestMapping(value="fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestPart("file") MultipartFile file){
        return fdfsService.fileUpload(file);
    }

    //文件删除
    @RequestMapping(value="fileDelete",method = RequestMethod.DELETE)
    public Integer fileDelete(@RequestParam("path")String path) {
        return fdfsService.fileDelete(path);
    }

}
