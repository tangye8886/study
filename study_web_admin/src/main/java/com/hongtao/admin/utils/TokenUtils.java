package com.hongtao.admin.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenUtils {

    @Value("${spring.security.key.name}")
    private String key; //键值

    @Value("${spring.security.expiretime}")
    private long expiretime;//过期时间

    //生成token
    public String makeToken(String id,String username,Integer role)
    {
        JwtBuilder jwtBuilder= Jwts.builder()
                .setId(id)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,key);
        if(expiretime>0)
        {
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis()+expiretime));
        }
        return jwtBuilder.compact();
    }

    //解析token
    public Claims parseToken(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
