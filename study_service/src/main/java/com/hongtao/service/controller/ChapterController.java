package com.hongtao.service.controller;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.ChapterDTO;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.Vedio;
import com.hongtao.service.service.ChapterService;
import com.hongtao.service.service.CourseService;
import com.hongtao.service.service.VedioService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Chapter)表控制层
 *
 * @author makejava
 * @since 2020-01-04 18:27:06
 */
@CrossOrigin
@RestController
@RequestMapping("api/chapter")
public class ChapterController extends ApiController {


    @Resource
    private ChapterService chapterService;

    @Resource
    private CourseService courseService;

    @Resource
    private VedioService vedioService;


    @RequestMapping(value ="query",method = RequestMethod.POST)
    public Map<String,Object> getChapterByPage(@RequestBody ChapterDTO chapterDTO)
    {
        PageHelper.startPage(chapterDTO.getPageNum(), chapterDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<Chapter> allChapter = chapterService.getAllByCondition(chapterDTO);
        PageInfo<Chapter> page = new PageInfo<Chapter>(allChapter);
        map.put("data",page);
        return map;
    }

    @RequestMapping(value ="queryChapter",method = RequestMethod.GET)
    public  Map<String,Object>  queryChapter()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Chapter> allChapter = chapterService.getAll();
        map.put("data",allChapter);
        return map;
    }

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable Serializable id) {
        Chapter chapter = this.chapterService.getById(id);
        Course course = courseService.getById(chapter.getCourse());
        List<Vedio> vedios = vedioService.findVedioByChapter(chapter.getId());
        chapter.setCourseDetail(course);
        chapter.setVedioChildren(vedios);
        return success(chapter);
    }


    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody Chapter chapter) {
        chapter.setId(MyUtils.makeCommentNo());
        chapter.setCreatime(new Date());
        return success(this.chapterService.save(chapter));
    }


    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody Chapter chapter) {
        return success(this.chapterService.updateById(chapter));
    }


    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.chapterService.removeByIds(idList));
    }
}