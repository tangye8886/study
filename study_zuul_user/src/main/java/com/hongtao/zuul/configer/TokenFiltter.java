package com.hongtao.zuul.configer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hongtao.common.utils.TokenUtils;
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
import java.util.ArrayList;
import java.util.List;

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

        String header="/"+name+"/";
        List<String> filterUrl=new ArrayList<String>();
        filterUrl.add(header+"api/user/order/query");
        filterUrl.add(header+"api/user/course/redis/querySeeRecord");
        filterUrl.add(header+"api/user/course/redis/queryUserStudy");
        filterUrl.add(header+"api/user/course/redis/queryLoveCourse");
        filterUrl.add(header+"api/user/userInfo/modify");
        filterUrl.add(header+"api/user/course/redis/delLoveCourse");
        filterUrl.add(header+"api/user/course/redis/addSeeRecord");
        //filterUrl.add(header+"api/user/course/comment/query");
        filterUrl.add(header+"api/user/course/comment/delete");

        for(String url:filterUrl)
        {
            if(url.equalsIgnoreCase(request.getRequestURI()))
            {
                return  true;
            }
        }
        return false;
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
                    Claims claims = TokenUtils.parseToken(token);
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
                context.getResponse().getWriter().write(objectMapper.writeValueAsString(TokenUtils.error()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
