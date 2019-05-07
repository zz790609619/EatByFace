
package com.hiqiblog.service;

import com.hiqiblog.entity.User;

import java.util.List;

/**
 * @author ${ww}=
 * @Description: TODO
 */
public interface IUserService {
    /**
     * 根据id获取user
     * @param user
     * @return
     */
    User findUserById(User user);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllList();
}
