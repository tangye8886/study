package com.hongtao.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.admin.service.CourseService;
import com.hongtao.admin.service.VedioService;
import com.hongtao.common.dto.*;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.CourseType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
       课程Controller (章节，视频,评论)
 */

@CrossOrigin
@RestController
@RequestMapping("api/admin/course")
public class CourseController {

    @Resource
    CourseService service;

    @Resource
    VedioService vedioService;


    //  --------------------------------课程-------------------------------------------------
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody CourseDTO courseDTO)
    {
        return service.query(courseDTO);
    }


    @RequestMapping("queryCourse")
    public Map<String,Object>  queryCourse(){
        return service.queryCourse();
    }

    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public Course selectOne(@PathVariable("id") String id) {
        return service.selectOne(id);
    }

    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody Course course) {
        return service.insert(course);
    }

    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody Course course) {
        return service.update(course);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return service.delete(idList);
    }


    //  --------------------------------评论-------------------------------------------------
    @RequestMapping(value = "comment/query",method = RequestMethod.POST)
    public  Map<String,Object> commentQuery(@RequestBody CommentDTO commentDTO){
        return service.commentQuery(commentDTO);
    }

    @RequestMapping(value = "comment/query/{id}",method = RequestMethod.GET)
    public R commentQueryOne(@PathVariable("id") String id){
        return service.commentQueryOne(id);
    }

    @RequestMapping(value = "comment/insert",method = RequestMethod.POST)
    public R commentInsert(@RequestBody Comment comment){
        return service.commentInsert(comment);
    }

    @RequestMapping(value = "comment/modify",method = RequestMethod.PUT)
    public R commentUpdate(@RequestBody Comment comment){
        return service.commentUpdate(comment);
    }

    @RequestMapping(value = "comment/delete",method = RequestMethod.DELETE)
    public R commentDelete(@RequestParam("idList") List<String> idList){
        return service.commentDelete(idList);
    }

    //  --------------------------------章节-------------------------------------------------

    @RequestMapping(value = "chapter/query",method = RequestMethod.POST)
    public Map<String,Object> chapterQuery(@RequestBody ChapterDTO chapterDTO){
        return service.chapterQuery(chapterDTO);
    }
    @RequestMapping(value ="chapter/queryChapter",method = RequestMethod.GET)
    public  Map<String,Object> queryChapter(){
        return service.queryChapter();
    }

    @RequestMapping(value = "chapter/query/{id}",method = RequestMethod.GET)
    public R chapterQueryOne(@PathVariable("id") String id){
        return service.chapterQueryOne(id);
    }

    @RequestMapping(value = "chapter/insert",method = RequestMethod.POST)
    public R chapterInsert(@RequestBody Chapter chapter){
        return service.chapterInsert(chapter);
    }

    @RequestMapping(value = "chapter/modify",method = RequestMethod.PUT)
    public R chapterUpdate(@RequestBody Chapter chapter){
        return service.chapterUpdate(chapter);
    }

    @RequestMapping(value = "chapter/delete",method = RequestMethod.DELETE)
    public R chapterDelete(@RequestParam("idList") List<String> idList){
        return service.chapterDelete(idList);
    }
    
    //  --------------------------------类型------------------------------------------------
    @RequestMapping(value = "type/query",method = RequestMethod.POST)
    public Map<String,Object> courseTypequery(@RequestBody CourseTypeDTO courseTypeDTO) { return service.courseSelectAll(courseTypeDTO); }
    
    @RequestMapping(value = "type/queryAll",method = RequestMethod.GET)
    public List<CourseType> courseTypeQuery(){
        return service.courseTypeQuery();
    }
    
    @RequestMapping(value = "type/query/{id}",method = RequestMethod.GET)
    public R courseTypeselectOne(@PathVariable("id") Serializable id){
        return service.courseTypeselectOne(id);
    }
    
    @RequestMapping(value = "type/insert",method = RequestMethod.POST)
    public R courseTypeinsert(@RequestBody CourseType courseType){
        return service.courseTypeinsert(courseType);
    }
    
    @RequestMapping(value = "type/modify",method = RequestMethod.PUT)
    public R courseTypeupdate(@RequestBody CourseType courseType){
        return service.courseTypeupdate(courseType);
    }
    
    @RequestMapping(value = "type/delete",method = RequestMethod.DELETE)
    public R courseTypedelete(@RequestParam("idList") List<String> idList){
        return service.courseTypedelete(idList);
    }
}
