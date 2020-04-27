package com.hongtao.zuul.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    @Value("${spring.security.key.name}")
    private String key; //键值

    @Value("${spring.security.expiretime}")
    private long expiretime;//过期时间

    //解析token
    public Claims parseToken(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Map<String,Object> error(){
        Map<String,Object> map=new HashMap<>();
        map.put("code",401);
        map.put("msg","认证失败,请检查登录状态。");
        return  map;
    }
}
