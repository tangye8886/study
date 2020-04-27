package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.dto.CommentDTO;
import com.hongtao.common.entity.Comment;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2019-11-27 21:36:02
 */
public interface CommentService extends IService<Comment> {

    //多条件查询
    public List<Comment> getAllCommnetByCondition(CommentDTO commentDTO);

}