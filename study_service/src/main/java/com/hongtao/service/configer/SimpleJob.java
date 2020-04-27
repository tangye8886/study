package com.hongtao.service.configer;

import com.hongtao.common.entity.Course;
import com.hongtao.service.dao.CourseDao;
import com.hongtao.service.utils.RedisUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling //开启任务调度
public class SimpleJob {

    @Resource
    RedisUtil redisUtil;

    @Resource
    CourseDao courseDao;


    // 课程排行榜 实施更新  每隔1分钟执行一次
    @Scheduled(cron = "0 */1 * * * ?")
   public void run(){
        List<Course> courseList = courseDao.selectList(null);
        for(Course course:courseList)
        {
            //课程的学习人数
            int studyCount = redisUtil.getSetMember("study:course:" + course.getId(), 0).size();
            redisUtil.zadd("course:rank",studyCount,course.getId(),0);
        }
       System.out.println("执行任务："+LocalDateTime.now());
   }

}
