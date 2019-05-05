
package com.hiQiBlog.service;

import com.hiQiBlog.entity.User;

import java.util.List;

/**
 * Created by
 * User业务逻辑
 */

public interface UserService  {
    User findUserById(User user);
    List<User> getAllList();
}
