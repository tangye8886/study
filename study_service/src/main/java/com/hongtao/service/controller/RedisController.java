package com.hongtao.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.RedisDTO;
import com.hongtao.common.entity.Course;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private CourseDao courseDao;

    // ---------------------------redis 接口------------------------

    //新增用户浏览记录 (set)
    //uid:用户ID  cid：课程ID
    @RequestMapping(value = "addSeeRecord",method = RequestMethod.POST)
    public void addSeeRecord(@RequestBody RedisDTO redisDTO)
    {
        redisUtil.sadd("see:user:"+redisDTO.getUid(),redisDTO.getCid(),0);
    }

    //返回用户浏览记录(课程List)
    @RequestMapping(value = "querySeeRecord",method = RequestMethod.POST)
    public Map<String,Object> querySeeRecordList(@RequestBody RedisDTO redisDTO)
    {
        //PageHelper.startPage(redisDTO.getPageNum(), redisDTO.getPageSize());
        System.out.println(redisDTO.getPageNum());
        System.out.println(redisDTO.getPageSize());
        Map<String,Object> map=new HashMap<>();
        List<Course> courseList=new ArrayList<>();
        Set<String> members = redisUtil.getSetMember("see:user:" + redisDTO.getUid(), 0);
        for(String s:members)
        {
            Course course = courseDao.selectById(s);
            courseList.add(course);
        }
        System.out.println("赋值后"+courseList.size());
        //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
        PageInfo<Course> page = new PageInfo<Course>(courseList,redisDTO.getPageSize());
        //PageInfo<Course> page = new PageInfo<Course>(courseList);
        System.out.println("分页后"+page.getList().size());
        map.put("data",page);
        map.put("count",courseList.size());
        return map;
    }



    //新增用户收藏的课程  增加课程的关注人数
    @RequestMapping(value = "addLoveCourse",method = RequestMethod.POST)
    public void addLoveCourse(@RequestBody RedisDTO redisDTO)
    {
        redisUtil.sadd("love:user:"+redisDTO.getUid(),redisDTO.getCid(),0);
        redisUtil.sadd("love:course:"+redisDTO.getCid(),redisDTO.getUid(),0);
    }

    //查询用户收藏的课程
    @RequestMapping(value = "queryLoveCourse",method = RequestMethod.POST)
    public Map<String,Object> queryLoveCourse(@RequestBody RedisDTO redisDTO)
    {
        PageHelper.startPage(redisDTO.getPageNum(), redisDTO.getPageSize());
        Map<String,Object> map=new HashMap<>();
        List<Course> courseList=new ArrayList<>();
        Set<String> members = redisUtil.getSetMember("love:user:" + redisDTO.getUid(), 0);
        for(String s:members)
        {
            Course course = courseDao.selectById(s);
            courseList.add(course);
        }
        PageInfo<Course> page = new PageInfo<Course>(courseList);
        map.put("data",page);
        map.put("count",courseList.size());
        return map;
    }

    //删除用户收藏的课程
    @RequestMapping(value = "delLoveCourse",method = RequestMethod.GET)
    public void delLoveCourse(@RequestParam("uid") String uid,@RequestParam("cid") String cid)
    {
        //删除用户的收藏夹内容
        redisUtil.delSet("love:user:" + uid,cid,0);
        //删除课程收藏人
        redisUtil.delSet("love:course:" + cid,uid,0);
    }

    //新增课程学习人数+（用户学习记录）
    @RequestMapping(value = "addCourseStudy",method = RequestMethod.POST)
    public void addCourseStudy(@RequestBody RedisDTO redisDTO)
    {
        redisUtil.sadd("study:course:"+redisDTO.getCid(),redisDTO.getUid(),0);//课程的学习人数
        redisUtil.sadd("study:user:"+redisDTO.getUid(),redisDTO.getCid(),0); //用户的学习记录
    }

    //查询用户收藏的课程
    @RequestMapping(value = "queryUserStudy",method = RequestMethod.POST)
    public Map<String,Object> queryUserStudy(@RequestBody RedisDTO redisDTO)
    {
        Map<String,Object> map=new HashMap<>();
        List<Course> courseList=new ArrayList<>();
        Set<String> members = redisUtil.getSetMember("study:user:" + redisDTO.getUid(), 0);
        for(String s:members)
        {
            Course course = courseDao.selectById(s);
            courseList.add(course);
        }
        map.put("data",courseList);
        map.put("count",courseList.size());
        return map;
    }



    //返回课程学习人数和收藏人数
    @RequestMapping(value = "queryLoveStudy",method = RequestMethod.GET)
    public Map<String,Object>  queryCourseStudy(@RequestParam("cid") String cid)
    {
        Map<String,Object> map=new HashMap<>();
        int c1=redisUtil.getSetMember("study:course:"+cid,0).size();
        int c2= redisUtil.getSetMember("love:course:"+cid,0).size();
        map.put("study",c1);
        map.put("love",c2);
        return map;
    }

    //判断某个课程  是否已被用户收藏
    @RequestMapping(value = "isExisit",method = RequestMethod.GET)
    public Map<String,Object>  isExisit(@RequestParam("cid") String cid,@RequestParam("uid") String uid)
    {
        Map<String,Object> map=new HashMap<>();
        map.put("result",redisUtil.isExisit(cid,uid,0));
        return map;
    }



    //初始化课程排行榜
    @RequestMapping(value = "initCourseRank",method = RequestMethod.GET)
    public void initCourseRank()
    {
        QueryWrapper<Course> queryWrapper=new QueryWrapper();
        queryWrapper.eq("status",1);
        List<Course> courseList = courseDao.selectList(queryWrapper);
        System.out.println(courseList.size());
        for(Course course:courseList)
        {
            if(course!=null)
            {
                //课程的学习人数
                int studyCount = redisUtil.getSetMember("study:course:" + course.getId(), 0).size();
                redisUtil.zadd("course:rank",studyCount,course.getId(),0);
            }
        }

    }

    //返回课程排行榜
    @RequestMapping(value = "queryCourseRank",method = RequestMethod.GET)
    public Map<String,Object> queryCourseRank(@RequestParam("start") String start,@RequestParam("end") String end)
    {
        Set<String> members = redisUtil.zRevrange("course:rank", Long.valueOf(start), Long.valueOf(end), 0);
        Map<String,Object> map=new HashMap<>();
        List<Course> courseList=new ArrayList<>();
        for(String s:members)
        {
            Course course = courseDao.selectById(s);
            if(course!=null)courseList.add(course);
        }
        map.put("data",courseList);

        Set<String> members2 = redisUtil.zRevrange("course:rank", 0,-1, 0);
        map.put("count",members2.size());
        return map;
    }

    //用户是否已经学习过
    @RequestMapping(value = "isExisitStudy",method = RequestMethod.GET)
    public boolean  isExisitStudy(@RequestParam("cid") String cid,@RequestParam("uid") String uid)
    {
        return redisUtil.isExisitStudy(cid,uid,0);
    }

}
