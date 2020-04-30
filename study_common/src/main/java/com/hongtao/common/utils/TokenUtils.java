package com.hongtao.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static final String KEY="study"; //键值

    private static final long expiretime=36000000;//过期时间


    //生成token
    public static String makeToken(String id,String username)
    {
        JwtBuilder jwtBuilder= Jwts.builder()
                .setId(id)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,KEY);
        if(expiretime>0)
        {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis()+expiretime));
        }
        return jwtBuilder.compact();
    }

    //解析token
    public static Claims parseToken(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    //错误结果返回
    public static Map<String,Object> error(){
        Map<String,Object> map=new HashMap<>();
        map.put("code",401);
        map.put("msg","认证失败,请检查登录状态。");
        return  map;
    }

}
