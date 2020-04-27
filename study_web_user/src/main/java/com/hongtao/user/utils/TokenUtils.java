package com.hongtao.user.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtils {


    //生成token
    public static String makeToken(String id,String username)
    {
        JwtBuilder jwtBuilder= Jwts.builder()
                .setId(id)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"user_study");

        return jwtBuilder.compact();
    }


    //解析token
    public static Claims parseToken(String token)
    {
        Claims claims = Jwts.parser().setSigningKey("user_study")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }


    public boolean isOkToken(HttpServletRequest request)
    {
        String header=request.getHeader(request.getHeader("Authorization"));
        if(header!=null && !header.equals("") && header.startsWith("Bearer "))
        {
            String token =header.substring(7);
            try{
                parseToken(token);
                return true;
            }catch (Exception e)
            {
                return false;
            }
        }
        return false;
    }
}
