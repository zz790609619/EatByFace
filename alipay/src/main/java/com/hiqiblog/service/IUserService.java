
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
     * 根据username取user
     * @param user
     * @return
     */
    List<User> findUserByUsername(User user);
    /**
     * 插入
     * @param user
     * @return
     */
    int insertUser(User user);
    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllList();

    /**
     * 补充用户个人信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);
    /**
     * 用户登录
     * @param user
     * @return
     */
    User loginUser(User user);

}
