package com.hongtao.user.service;

import com.hongtao.common.dto.RedisDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "study-service")
public interface RedisService {

    //增加浏览记录
    @RequestMapping(value = "api/redis/addSeeRecord",method = RequestMethod.POST)
    public void addSeeRecord(@RequestBody RedisDTO redisDTO);

    //返回用户浏览记录(课程List)
    @RequestMapping(value = "api/redis/querySeeRecord",method = RequestMethod.POST)
    public Map<String,Object> querySeeRecordList(@RequestBody RedisDTO redisDTO);

    //新增用户收藏的课程  增加课程的关注人数
    @RequestMapping(value = "api/redis/addLoveCourse",method = RequestMethod.POST)
    public void addLoveCourse(@RequestBody RedisDTO redisDTO);

    //查询用户收藏的课程
    @RequestMapping(value = "api/redis/queryLoveCourse",method = RequestMethod.POST)
    public Map<String,Object> queryLoveCourse(@RequestBody RedisDTO redisDTO);

    //删除用户收藏的课程
    @RequestMapping(value = "api/redis/delLoveCourse",method = RequestMethod.GET)
    public void delLoveCourse(@RequestParam("uid") String uid, @RequestParam("cid") String cid);
    //新增课程学习人数
    @RequestMapping(value = "api/redis/addCourseStudy",method = RequestMethod.POST)
    public void addCourseStudy(@RequestBody RedisDTO redisDTO);

    //返回课程学习人数和收藏人数
    @RequestMapping(value = "api/redis/queryLoveStudy",method = RequestMethod.GET)
    public Map<String,Object>  queryCourseStudy(@RequestParam("cid") String cid);

    @RequestMapping(value = "api/redis/isExisit",method = RequestMethod.GET)
    public Map<String,Object> isExisit(@RequestParam("cid") String cid, @RequestParam("uid") String uid);


    //初始化课程排行榜
    @RequestMapping(value = "api/redis/initCourseRank",method = RequestMethod.GET)
    public void initCourseRank();

    //返回课程排行榜
    @RequestMapping(value = "api/redis/queryCourseRank",method = RequestMethod.GET)
    public Map<String,Object> queryCourseRank(@RequestParam("start") String start, @RequestParam("end") String end);

    //判断某个课程 用户是否已经学习过
    @RequestMapping(value = "api/redis/isExisitStudy",method = RequestMethod.GET)
    public boolean  isExisitStudy(@RequestParam("cid") String cid, @RequestParam("uid") String uid);

    @RequestMapping(value = "api/redis/queryUserStudy",method = RequestMethod.POST)
    public Map<String,Object> queryUserStudy(@RequestBody RedisDTO redisDTO);

}
