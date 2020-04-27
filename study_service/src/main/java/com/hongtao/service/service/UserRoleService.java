package com.hongtao.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hongtao.common.entity.UserRole;

import java.util.List;

/**
 * (UserRole)表服务接口
 *
 * @author makejava
 * @since 2019-11-27 00:05:40
 */
public interface UserRoleService extends IService<UserRole> {

    public List<UserRole> getAllRole();

}