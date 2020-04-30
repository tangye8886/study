package com.hongtao.service.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.dto.VedioDTO;
import com.hongtao.common.entity.*;
import com.hongtao.service.service.ChapterService;
import com.hongtao.service.service.CourseService;
import com.hongtao.service.service.FdfsService;
import com.hongtao.service.service.VedioService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * (CourseVedio)表控制层
 *
 * @author makejava
 * @since 2019-11-27 21:36:16
 */
@CrossOrigin
@RestController
@RequestMapping("api/vedio")
public class VedioController extends ApiController {

    @Resource
    VedioService service;

    @Resource
    FdfsService fdfsService;


    @Resource
    private CourseService courseService;
    
    @Resource
    private ChapterService chapterService;
    
    @Resource
    private VedioService vedioService;


    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> selectAll(@RequestBody VedioDTO vedioDTO)
    {
        PageHelper.startPage(vedioDTO.getPageNum(), vedioDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<Vedio> allUser = vedioService.getAllByCondition(vedioDTO);
        PageInfo<Vedio> page = new PageInfo<Vedio>(allUser);
        map.put("data",page);
        return map;
    }

    @RequestMapping(value="query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable("id") Serializable id) {
        return success(this.vedioService.getById(id));
    }

    @RequestMapping(value="insert",method = RequestMethod.POST)
    public R insert(@RequestBody Vedio courseVedio) {
        courseVedio.setId(MyUtils.makeCommentNo());
        courseVedio.setCreatime(new Date());
        return success(this.vedioService.save(courseVedio));
    }


    @RequestMapping(value="modify",method = RequestMethod.PUT)
    public R update(@RequestBody Vedio courseVedio) {
        courseVedio.setUpdatime(new Date());
        return success(this.vedioService.updateById(courseVedio));
    }


    @RequestMapping(value="delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        for(String id:idList)
        {
            Vedio vedio = vedioService.getById(id);
            if(vedio.getSrc()!=null && !vedio.getSrc().equals(""))
            {
                fdfsService.deleteFile(vedio.getSrc());
            }
        }
        return success(this.vedioService.removeByIds(idList));
    }

    @RequestMapping(value="fileUpload",method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        return fdfsService.fileUpload(file);
    }

    @RequestMapping(value="fileDelete",method = RequestMethod.DELETE)
    public Integer fileDelete(@RequestParam("path")String path) {
        return fdfsService.deleteFile(path);
    }


    //  视频页面  新增视频的级联
    //返回所有课程跟对应的章节
    @RequestMapping(value="queryJLByCourse",method = RequestMethod.POST)
    public List<Options> queryByjl(@RequestBody CourseDTO courseDTO) {
        List<Options> options=new ArrayList<Options>();

        List<Course> courses = courseService.getAllByCondition(courseDTO);

        for(Course c:courses)
        {
            Options o=new Options(c.getTitle(),c.getId());
            List<Chapter> chapters = chapterService.getChapterByCourse(c.getId());
            List<Options> childrenOptions=new ArrayList<Options>();
            for(Chapter ca:chapters)
            {
                Options co=new Options(ca.getContent(),ca.getId());
                childrenOptions.add(co);
            }
            o.setChildren(childrenOptions);
            options.add(o);
        }
        return options;
    }

}