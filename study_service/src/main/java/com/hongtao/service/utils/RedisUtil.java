package com.hongtao.service.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class RedisUtil {

    @Resource
    private JedisPool jedisPool;

    public String get(String key,int indexdb) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            value = jedis.get(key);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    public String set(String key, String value,int indexdb) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            return jedis.set(key, value);
        } catch (Exception e) {
            return "0";
        } finally {
            returnResource(jedis);
        }
    }
    // 用于添加 课程的学习人数（course:id   user:id ）
    // | 用户关注的课程/浏览记录（user:id:see course:id）
    public void sadd(String key,String value,int indexdb)
    {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            //jedis.expire(key,3600*24);  设置过期时间
            jedis.sadd(key, value);
        } catch (Exception e) {
            System.out.println("sadd出错了");
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }
    //获取集合中的成员   用于统计排行榜
    public Set<String> getSetMember(String key,int indexdb)
    {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            Set<String> smembers = jedis.smembers(key);
            return  smembers;
        } catch (Exception e) {
            System.out.println("sadd出错了");
            e.printStackTrace();
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    //移除 集合中的某个元素
    public void delSet(String key,String value,int indexdb){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            jedis.srem(key,value);
        } catch (Exception e) {
            System.out.println("sadd出错了");
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }


    //有序集合 排行 倒序
    public Set<String> zRevrange(String key,long start,long end,int indexdb)
    {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            Set<String> zrevrange = jedis.zrevrange(key, start, end);
            return  zrevrange;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        } finally {
            returnResource(jedis);
        }
    }

    public void zadd(String key,int score,String member,int indexdb)
    {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexdb);
            jedis.zadd(key,Double.valueOf(score),member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }

    //判断 member 元素是否是集合 key 的成员value(用户ID)
    public boolean isExisit(String cid,String value,int index){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            return jedis.sismember("love:course:"+cid,value);
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        } finally {
            returnResource(jedis);
        }
    }


    //判断 member 元素是否是集合 key 的成员value(用户ID)
    public boolean isExisitStudy(String cid,String value,int index){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(index);
            return jedis.sismember("study:course:"+cid,value);
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        } finally {
            returnResource(jedis);
        }
    }


    /**
     * 关闭连接
     */
    public static void returnResource(Jedis jedis) {
        try {
            jedis.close();
        } catch (Exception e) {
        }
    }
}
