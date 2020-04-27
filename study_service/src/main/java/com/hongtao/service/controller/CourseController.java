package com.hongtao.service.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.CourseDTO;
import com.hongtao.common.entity.Chapter;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.common.entity.Vedio;
import com.hongtao.service.service.*;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Course)表控制层
 *
 * @author makejava
 * @since 2019-11-27 21:35:46
 */
@CrossOrigin
@RestController
@RequestMapping("api/course")
public class CourseController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;
    
    @Resource
    private VedioService vedioService;
    
    @Resource
    private ChapterService chapterService;

    @Resource
    private UserService userService;

    @Resource
    private FdfsService fdfsService;

    /**
     * 分页查询数据
     */
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public Map<String,Object> query(@RequestBody CourseDTO courseDTO)
    {
        PageHelper.startPage(courseDTO.getPageNum(), courseDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<Course> allCourse = courseService.getAllByCondition(courseDTO);
        PageInfo<Course> page = new PageInfo<Course>(allCourse);
        map.put("data",page);
        return map;
    }

    @RequestMapping(value = "queryCourse",method = RequestMethod.GET)
    public Map<String,Object> queryCourse()
    {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Course> allCourse = courseService.getAll();
        map.put("data",allCourse);
        return map;
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据  +  所有章节 + 视频
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public Course selectOne(@PathVariable Serializable id) {
        Course course = courseService.getById(id);
        if(course!=null)
        {
            List<Chapter> chapterList = chapterService.getChapterByCourse(course.getId());
            //如果该课程存在章节的话
            if(chapterList.size()>0)
            {
                 for(Chapter c:chapterList)
                 {
                     List<Vedio> vedioList = vedioService.findVedioByChapter(c.getId());
                     // 如果该章节下面有视频
                     if(vedioList.size()>0)
                     {
                         c.setVedioChildren(vedioList);
                     }
                 }
                course.setChapterChildren(chapterList);
            }
            //如果有教师
            if(course.getTeacher()!=null)
            {
                UserInfo user = userService.getById(course.getTeacher());
                course.setTeacherDetail(user);
            }
        }
        return course;
    }

    /**
     * 新增数据
     *
     * @param course 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody Course course) {
        course.setId(MyUtils.getCourseNo());
        course.setCreatime(new Date());
        return success(this.courseService.save(course));
    }

    /**
     * 修改数据
     *
     * @param course 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody Course course) {
        course.setUpdatime(new Date());
        return success(this.courseService.updateById(course));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {

        for(String id:idList)
        {
            Course course = courseService.getById(id);
            if(course.getImage()!=null && !course.getImage().equals(""))
            {
                fdfsService.deleteFile(course.getImage());
            }
        }
        return success(this.courseService.removeByIds(idList));
    }

}