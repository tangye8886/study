package com.hongtao.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.FdfsService;
import com.hongtao.admin.service.VedioService;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.common.entity.Options;
import com.hongtao.common.entity.Vedio;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/admin/vedio/")
public class VedioController {

    @Resource
    VedioService vedioService;

    @Resource
    FdfsService fdfsService;

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO){
        return vedioService.selectAll(vedioDTO);
    }

    @RequestMapping(value="query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id)
    {
        return vedioService.selectOne(id);
    }

    @RequestMapping(value="insert",method = RequestMethod.POST)
    public R insert(@RequestBody Vedio courseVedio){
        return vedioService.insert(courseVedio);
    }


    @RequestMapping(value="modify",method = RequestMethod.PUT)
    public R update(@RequestBody Vedio courseVedio){
        return vedioService.update(courseVedio);
    }


    //文件上传
    @RequestMapping(value="fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestPart("file") MultipartFile file){
        return fdfsService.fileUpload(file);
    }


    @RequestMapping(value="delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList){
        return vedioService.delete(idList);
    }


    @RequestMapping(value="queryJLByCourse",method = RequestMethod.POST)
    public List<Options> queryJLByCourse(@RequestBody CourseDTO courseDTO){
        return vedioService.queryJLByCourse(courseDTO);
    }
}
