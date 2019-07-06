package com.hiqiblog.mapper;

import com.hiqiblog.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> getAllList();

    /**
     * 根据名称判断是否存在
     * @param record
     * @return
     */
    List<User> findUserByUsername(User record);

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