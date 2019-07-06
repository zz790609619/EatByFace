package com.hiqiblog.mapper;

import com.hiqiblog.entity.UpdateInfo;

import java.util.List;

public interface UpdateInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UpdateInfo record);

    int insertSelective(UpdateInfo record);

    UpdateInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UpdateInfo record);

    int updateByPrimaryKey(UpdateInfo record);

    List<UpdateInfo> getAllList();
}