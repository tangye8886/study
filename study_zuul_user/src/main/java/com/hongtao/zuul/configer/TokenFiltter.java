package com.hongtao.zuul.configer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongtao.zuul.util.TokenUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author tanghongtao
 * @version 1.0
 * @date 2020/4/13 20:33
 */
@Component
public class TokenFiltter extends ZuulFilter {

    @Value("${zuul.routes.api-study.serviceId}")
    String name;


    private static ObjectMapper objectMapper=new ObjectMapper();

    @Resource
    TokenUtils tokenUtils;

    // 指定过滤器类型
    @Override
    public String filterType() {
        return "pre";
    }

    // 过滤器顺序，数字越小越先执行
    @Override
    public int filterOrder() {
        return 5;
    }

    // 是否使用该过滤器。
    @Override
    public boolean shouldFilter() {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if(("/"+name+"/login").equalsIgnoreCase(request.getRequestURI()))
        {
            return false;
        }
        return true;
    }

    // 过滤器具体执行的操作
    @Override
    public Object run() throws ZuulException {
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        context.getResponse().setContentType("application/json;charset=UTF-8");
        boolean isLogin=false;
        try{
            if(request.getHeader("Authorization")!=null)
            {
                String header=request.getHeader("Authorization");
                boolean startsWith=header.startsWith("Bearer ");
                if(startsWith)
                {
                    String token=header.substring(7);
                    Claims claims = tokenUtils.parseToken(token);
                    if(claims.getSubject()!=null) {
                        isLogin=true;
                    }
                }
            }
        }catch (Exception e)
        {
            isLogin=false;
            e.printStackTrace();
        }

        if(!isLogin)
        {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            try {
                context.getResponse().getWriter().write(objectMapper.writeValueAsString(tokenUtils.error()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
