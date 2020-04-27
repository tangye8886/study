package com.hongtao.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongtao.common.entity.Vedio;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * (CourseVedio)表数据库访问层
 *
 * @author makejava
 * @since 2019-11-27 21:36:16
 */
public interface VedioDao extends BaseMapper<Vedio> {

    @Select("select * from vedio where id=#{id}")
    public Vedio getOneVedio(@Param("id") String id);

}