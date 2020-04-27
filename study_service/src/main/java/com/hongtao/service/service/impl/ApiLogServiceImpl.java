package com.hongtao.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.common.entity.ApiLog;
import com.hongtao.service.dao.ApiLogDao;
import com.hongtao.service.service.ApiLogService;
import org.springframework.stereotype.Service;

/**
 * (ApiLog)表服务实现类
 *
 * @author makejava
 * @since 2020-03-15 22:57:27
 */
@Service("apiLogService")
public class ApiLogServiceImpl extends ServiceImpl<ApiLogDao, ApiLog> implements ApiLogService {

}