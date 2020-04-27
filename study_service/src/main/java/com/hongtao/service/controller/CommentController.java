package com.hongtao.service.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongtao.common.dto.CommentDTO;
import com.hongtao.common.entity.Comment;
import com.hongtao.common.entity.Course;
import com.hongtao.common.entity.UserInfo;
import com.hongtao.service.dao.CommentDao;
import com.hongtao.service.service.CommentService;
import com.hongtao.service.service.CourseService;
import com.hongtao.service.service.UserService;
import com.hongtao.service.utils.MyUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2019-11-27 21:36:02
 */
@CrossOrigin
@RestController
@RequestMapping("api/comment")
public class CommentController extends ApiController {

    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    @Resource
    private CommentDao commentDao;

    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @RequestMapping(value = "query",method = RequestMethod.POST)
    public  Map<String,Object> selectAll(@RequestBody CommentDTO commentDTO) {
        PageHelper.startPage(commentDTO.getPageNum(), commentDTO.getPageSize());
        Map<String,Object> map=new HashMap<String,Object>();
        List<Comment> comments = commentService.getAllCommnetByCondition(commentDTO);
        PageInfo<Comment> page = new PageInfo<Comment>(comments);
        map.put("data",page);
        return map;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping(value = "query/{id}",method = RequestMethod.GET)
    public R selectOne(@PathVariable Serializable id) {
        Comment comment = this.commentService.getById(id);
        UserInfo user = userService.getById(comment.getUser());
        Course course = courseService.getById(comment.getPid());
        comment.setUserDetail(user);
        comment.setCourseDetail(course);
        return success(comment);
    }

    /**
     * 新增数据
     *
     * @param comment 实体对象
     * @return 新增结果
     */
    @RequestMapping(value = "insert",method = RequestMethod.POST)
    public R insert(@RequestBody Comment comment) {
        comment.setCreatime(new Date());
        comment.setId(MyUtils.makeCommentNo());
        return success(this.commentService.save(comment));
    }

    /**
     * 修改数据
     *
     * @param comment 实体对象
     * @return 修改结果
     */
    @RequestMapping(value = "modify",method = RequestMethod.PUT)
    public R update(@RequestBody Comment comment) {
        comment.setUpdatime(new Date());
        return success(this.commentService.updateById(comment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.commentService.removeByIds(idList));
    }

    // 查询用户是否已经评论过
    @RequestMapping(value = "userIsComment",method = RequestMethod.POST)
    public boolean queryCommentByUser(@RequestBody CommentDTO commentDTO)
    {
        List<Comment> comments = commentService.getAllCommnetByCondition(commentDTO);
        if(comments.size()>0){
            return true;
        }else{
            return  false;
        }
    }
}