package com.alipay.demo.service;

import com.alipay.demo.entity.User;

import java.util.List;

/**
 * Created by
 * User业务逻辑
 */

public interface UserService  {
    User findUserById(User user);
    List<User> getAllList();
}
