package com.hongtao.user.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.ChapterDTO;
import com.hongtao.common.dto.CommentDTO;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.dto.CourseTypeDTO;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.CourseType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "study-service")
public interface CourseService {

    /*
       课程
     */
    @RequestMapping(value = "api/course/query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody CourseDTO courseDTO);

    @RequestMapping(value = "api/course/queryCourse",method = RequestMethod.GET)
    public Map<String,Object> queryCourse();

    @RequestMapping(value = "api/course/query/{id}",method = RequestMethod.GET)
    public Course selectOne(@PathVariable("id") String id);


    /*
        课程-章节
     */
    @RequestMapping(value ="api/chapter/query",method = RequestMethod.POST)
    public Map<String,Object> chapterQuery(@RequestBody ChapterDTO chapterDTO);

    @RequestMapping(value ="api/chapter/queryChapter",method = RequestMethod.GET)
    public  Map<String,Object>  queryChapter();

    @RequestMapping(value = "api/chapter/query/{id}",method = RequestMethod.GET)
    public R chapterQueryOne(@PathVariable("id") String id);

    /*
        课程-评论
     */
    @RequestMapping(value = "api/comment/query",method = RequestMethod.POST)
    public  Map<String,Object> commentQuery(@RequestBody CommentDTO commentDTO);

    @RequestMapping(value = "api/comment/query/{id}",method = RequestMethod.GET)
    public R commentQueryOne(@PathVariable("id") String id);


    // 查询用户是否已经评论过
    @RequestMapping(value = "api/comment/userIsComment",method = RequestMethod.POST)
    public boolean userIsComment(@RequestBody CommentDTO commentDTO);


    @RequestMapping(value = "api/comment/insert",method = RequestMethod.POST)
    public R commentInsert(@RequestBody Comment comment);


    @RequestMapping(value = "api/comment/delete",method = RequestMethod.DELETE)
    public R commentDelete(@RequestParam("idList") List<String> idList);



    /*
        课程- 类型
     */

    @RequestMapping(value = "api/courseType/query",method = RequestMethod.POST)
    public Map<String,Object> courseSelectAll(@RequestBody CourseTypeDTO courseTypeDTO);

    @RequestMapping(value = "api/courseType/queryAll",method = RequestMethod.GET)
    public List<CourseType> courseTypeQuery();
}
