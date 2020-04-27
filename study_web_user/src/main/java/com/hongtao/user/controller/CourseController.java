package com.hongtao.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.hongtao.common.dto.*;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.CourseType;
import com.hongtao.user.service.CourseService;
import com.hongtao.user.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
       课程Controller (章节，视频,评论)
 */

@CrossOrigin
@RestController
@RequestMapping("api/user/course")
public class CourseController {

    @Resource
    CourseService service;

    @Resource
    RedisService redisService;

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


    //  --------------------------------评论-------------------------------------------------
    @RequestMapping(value = "comment/query",method = RequestMethod.POST)
    public  Map<String,Object> commentQuery(@RequestBody CommentDTO commentDTO){
        return service.commentQuery(commentDTO);
    }

    @RequestMapping(value = "comment/query/{id}",method = RequestMethod.GET)
    public R commentQueryOne(@PathVariable("id") String id){
        return service.commentQueryOne(id);
    }


    //判断用户是否已经评论过  参数  pid   user 或者用户是否学习过
    @RequestMapping(value = "comment/userIsComment",method = RequestMethod.POST)
    public Map<String,Object> queryCommentByUser(@RequestBody CommentDTO commentDTO){
        boolean isComment = service.userIsComment(commentDTO);
        boolean isStudy = redisService.isExisitStudy(commentDTO.getCid(), commentDTO.getUser());
        Map<String,Object> map=new HashMap();
        map.put("isStudy",isStudy);
        map.put("isComment",isComment);
        return map;
    }

    @RequestMapping(value = "comment/insert",method = RequestMethod.POST)
    public R commentInsert(@RequestBody Comment comment){
        return service.commentInsert(comment);
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



    //  --------------------------------类型------------------------------------------------
    @RequestMapping(value = "type/query",method = RequestMethod.POST)
    public Map<String,Object> courseTypequery(@RequestBody CourseTypeDTO courseTypeDTO)
    {
        return service.courseSelectAll(courseTypeDTO);
    }

    @RequestMapping(value = "type/queryAll",method = RequestMethod.GET)
    public List<CourseType> courseTypeQuery(){
        return service.courseTypeQuery();
    }

    //-----------------------------------redis--------------------------------

    //加入浏览记录
    @RequestMapping(value = "redis/addSeeRecord",method = RequestMethod.POST)
    public void addSeeRecord(@RequestBody RedisDTO redisDTO)
    {
        redisService.addSeeRecord(redisDTO);
    }

    //返回用户浏览记录(课程List)
    @RequestMapping(value = "redis/querySeeRecord",method = RequestMethod.POST)
    public Map<String,Object> querySeeRecordList(@RequestBody RedisDTO redisDTO)
    {
        return redisService.querySeeRecordList(redisDTO);
    }

    //新增用户收藏的课程  增加课程的关注人数
    @RequestMapping(value = "redis/addLoveCourse",method = RequestMethod.POST)
    public void addLoveCourse(@RequestBody RedisDTO redisDTO)
    {
        redisService.addLoveCourse(redisDTO);
    }

    //查询用户收藏的课程
    @RequestMapping(value = "redis/queryLoveCourse",method = RequestMethod.POST)
    public Map<String,Object> queryLoveCourse(@RequestBody RedisDTO redisDTO)
    {
            return redisService.queryLoveCourse(redisDTO);
    }

    //删除用户收藏的课程
    @RequestMapping(value = "redis/delLoveCourse",method = RequestMethod.GET)
    public void delLoveCourse(@RequestParam("uid") String uid,@RequestParam("cid") String cid)
    {
        redisService.delLoveCourse(uid,cid);
    }

    //新增课程学习人数
    @RequestMapping(value = "redis/addCourseStudy",method = RequestMethod.POST)
    public void addCourseStudy(@RequestBody RedisDTO redisDTO)
    {
        redisService.addCourseStudy(redisDTO);
    }

    //查询用户的学习记录
    @RequestMapping(value = "redis/queryUserStudy",method = RequestMethod.POST)
    public Map<String,Object> queryStudyCourse(@RequestBody RedisDTO redisDTO){
        return redisService.queryUserStudy(redisDTO);
    }

    //返回课程学习人数和收藏人数
    @RequestMapping(value = "redis/queryLoveStudy",method = RequestMethod.GET)
    public Map<String,Object>  queryCourseStudy(@RequestParam("cid") String cid)
    {
        return redisService.queryCourseStudy(cid);
    }

    //初始化课程排行榜
    @RequestMapping(value = "redis/initCourseRank",method = RequestMethod.GET)
    public void initCourseRank()
    {
        redisService.initCourseRank();
    }

    //返回课程排行榜
    @RequestMapping(value = "redis/queryCourseRank",method = RequestMethod.GET)
    public Map<String,Object> queryCourseRank(@RequestParam("start") String start,@RequestParam("end") String end)
    {
       return redisService.queryCourseRank(start,end);
    }

    //判断某个课程  是否已被用户收藏
    @RequestMapping(value = "redis/isExisit",method = RequestMethod.GET)
    public Map<String,Object>  isExisit(@RequestParam("cid") String cid,@RequestParam("uid") String uid)
    {
        return redisService.isExisit(cid,uid);
    }

}
