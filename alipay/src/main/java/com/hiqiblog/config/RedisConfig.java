package com.hiqiblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 功能描述：redis工具类
 * 对于redisTpl.opsForValue().set(key, value)进行了一次封装，不然每次都要这样保存值
 * 而封装后只需：new RedisClient().set(key,value);
 * @Author helloc
 * @Date 2019/7/25 14:08
 * @Version 1.0
 */

@Component
public class RedisConfig {
    @Autowired
    private StringRedisTemplate redisTpl;

    // 功能描述：设置key-value到redis中
    public boolean set(String key ,String value){
        try{
            redisTpl.opsForValue().set(key, value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 功能描述：通过key获取缓存里面的值
    public String get(String key){
        return redisTpl.opsForValue().get(key);
    }
}
