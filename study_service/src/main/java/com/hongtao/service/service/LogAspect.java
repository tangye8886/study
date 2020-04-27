package com.hongtao.service.service;

import com.hongtao.common.entity.ApiLog;
import com.hongtao.service.dao.ApiLogDao;
import com.hongtao.service.utils.MyUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAspect {

    @Resource
    ApiLogDao apiLogDao;


    @Pointcut(value = "execution(public * com.hongtao.service.controller.*.*(..))")
    public void apiPoint() {
        // 无需内容
    }

    @Before("apiPoint()")
    public void recordApiLog()
    {
        System.out.println("前置通知");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ApiLog apiLog=new ApiLog();
        apiLog.setId(MyUtils.makeToken());
        apiLog.setApi(request.getRequestURI());
        apiLog.setAddress(request.getRemoteAddr());
        apiLog.setUrl(request.getRequestURL()+"");
        apiLog.setMethod(request.getMethod());
        apiLog.setPort(request.getRemotePort()+"");
        apiLog.setTime(new Date());
        apiLog.setInfo("接口调用");
        apiLogDao.insert(apiLog);
    }
}
