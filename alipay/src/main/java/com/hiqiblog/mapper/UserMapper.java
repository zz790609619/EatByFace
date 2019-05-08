
package com.hiqiblog.mapper;

import com.hiqiblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ww
 */
public interface UserMapper {
    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(User record);
    /**
     * 插入数据
     * @param record
     * @return
     */
    int insertSelective(User record);
    /**
     * 根据id查找实体
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);
    /**
     * 根据id更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);
    /**
     * 根据id更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    /**
     * 获取全部数据
     * @return
     */
    List<User>  getAllList();
}