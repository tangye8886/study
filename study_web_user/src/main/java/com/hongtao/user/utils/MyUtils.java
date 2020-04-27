package com.hongtao.user.utils;

import java.util.Date;
import java.util.UUID;

public class MyUtils {

    public static String getUUID()
    {
        return  UUID.randomUUID().toString().replaceAll("-","").substring(0,10);
    }

    public static String getCourseNo(){
        long t1=System.currentTimeMillis();
        return t1+"";
    }

    public static String makeCommentNo(){
        long t1=System.currentTimeMillis();
        String s=t1+"";
        return s.substring(0,12);
    }

    public static Integer makeRoleID()
    {
        Date data=new Date();
        return Integer.valueOf((data.getTime()+"").substring(7, 13));
    }


    public static String makeToken()
    {
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String token = uuid.toString();
        // 转换为大写
        token = token.toUpperCase();
        // 替换 “-”变成空格
        token = token.replaceAll("-", "").substring(0, 16);
        return token;
    }



}
