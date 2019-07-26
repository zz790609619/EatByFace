
package com.hiqiblog.service.impl;

import com.hiqiblog.config.RedisConfig;
import com.hiqiblog.entity.User;
import com.hiqiblog.mapper.UserMapper;
import com.hiqiblog.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ${ww}
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
     UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    private RedisConfig redisConfig;
    @Override
    public User findUserById(User user){
        try{
            if(user!=null){
                return userMapper.selectByPrimaryKey(user.getId());
            }
        }catch (Exception e){}
        return user;
    }

    @Override
    public List<User> findUserByUsername(User user) {
        return  userMapper.findUserByUsername(user);
    }

    @Override
    public int insertUser(User user) {

        int result=userMapper.insert(user);
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.delete("getList");
        redisTemplate.opsForValue().set("getList",userMapper.getAllList());
        return  result;
    }

    @Override
    public List<User> getAllList() {
        //重新定义字符串系列化
        RedisSerializer redisSerializer=new StringRedisSerializer();
        //将字符串序列化加到模板key中
        redisTemplate.setKeySerializer(redisSerializer);
        List<User> userList=(List<User>)redisTemplate.opsForValue().get("getList");
        if(userList!=null){
            return userList;
        }else{
          redisTemplate.opsForValue().set("getList",userMapper.getAllList());
            return userMapper.getAllList();
        }

    }

    @Override
    public int updateUserInfo(User user) {
        int result=userMapper.updateUserInfo(user);
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.delete("getList");
        redisTemplate.opsForValue().set("getList",userMapper.getAllList());
        return result;
    }

    @Override
    public User loginUser(User user) {

        return userMapper.loginUser(user);
    }

}
