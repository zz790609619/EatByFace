package com.alipay.demo.service.impl;

import com.alipay.demo.entity.User;
import com.alipay.demo.mapper.UserMapper;
import com.alipay.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUserById(User user){
        try{
            if(user!=null){
                return userMapper.selectByPrimaryKey(user.getId());
            }
        }catch (Exception e){}
        return user;
    }
}
