package com.hongtao.admin.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.ChapterDTO;
import com.hongtao.common.dto.CommentDTO;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.dto.CourseTypeDTO;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.CourseType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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

    @RequestMapping(value = "api/course/insert",method = RequestMethod.POST)
    public R insert(@RequestBody Course course);

    @RequestMapping(value = "api/course/modify",method = RequestMethod.PUT)
    public R update(@RequestBody Course course);

    @RequestMapping(value = "api/course/delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList);

    /*
        课程-章节
     */
    @RequestMapping(value ="api/chapter/query",method = RequestMethod.POST)
    public Map<String,Object> chapterQuery(@RequestBody ChapterDTO chapterDTO);

    @RequestMapping(value ="api/chapter/queryChapter",method = RequestMethod.GET)
    public  Map<String,Object>  queryChapter();

    @RequestMapping(value = "api/chapter/query/{id}",method = RequestMethod.GET)
    public R chapterQueryOne(@PathVariable("id") String id);

    @RequestMapping(value = "api/chapter/insert",method = RequestMethod.POST)
    public R chapterInsert(@RequestBody Chapter chapter);

    @RequestMapping(value = "api/chapter/modify",method = RequestMethod.PUT)
    public R chapterUpdate(@RequestBody Chapter chapter);

    @RequestMapping(value = "api/chapter/delete",method = RequestMethod.DELETE)
    public R chapterDelete(@RequestParam("idList") List<String> idList);

    /*
        课程-评论
     */
    @RequestMapping(value = "api/comment/query",method = RequestMethod.POST)
    public  Map<String,Object> commentQuery(@RequestBody CommentDTO commentDTO);

    @RequestMapping(value = "api/comment/query/{id}",method = RequestMethod.GET)
    public R commentQueryOne(@PathVariable("id") String id);

    @RequestMapping(value = "api/comment/insert",method = RequestMethod.POST)
    public R commentInsert(@RequestBody Comment comment);

    @RequestMapping(value = "api/comment/modify",method = RequestMethod.PUT)
    public R commentUpdate(@RequestBody Comment comment);

    @RequestMapping(value = "api/comment/delete",method = RequestMethod.DELETE)
    public R commentDelete(@RequestParam("idList") List<String> idList);

    // 查询用户是否已经评论过
    @RequestMapping(value = "api/comment/userIsComment",method = RequestMethod.POST)
    public boolean userIsComment(@RequestBody CommentDTO commentDTO);

    /*
        课程- 类型
     */

    @RequestMapping(value = "api/courseType/query",method = RequestMethod.POST)
    public Map<String,Object> courseSelectAll(@RequestBody CourseTypeDTO courseTypeDTO);

    @RequestMapping(value = "api/courseType/queryAll",method = RequestMethod.GET)
    public List<CourseType> courseTypeQuery();

    @RequestMapping(value = "api/courseType/query/{id}",method = RequestMethod.GET)
    public R courseTypeselectOne(@PathVariable("id") Serializable id);

    @RequestMapping(value = "api/courseType/insert",method = RequestMethod.POST)
    public R courseTypeinsert(@RequestBody CourseType courseType);

    @RequestMapping(value = "api/courseType/modify",method = RequestMethod.PUT)
    public R courseTypeupdate(@RequestBody CourseType courseType);

    @RequestMapping(value = "api/courseType/delete",method = RequestMethod.DELETE)
    public R courseTypedelete(@RequestParam("idList") List<String> idList);
}
