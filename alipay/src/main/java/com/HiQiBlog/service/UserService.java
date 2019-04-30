package com.HiQiBlog.service;

import com.HiQiBlog.entity.User;

import java.util.List;

/**
 * Created by
 * User业务逻辑
 */

public interface UserService  {
    User findUserById(User user);
    List<User> getAllList();
}
