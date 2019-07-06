package com.hiqiblog.service.impl;

import com.hiqiblog.entity.UpdateInfo;
import com.hiqiblog.mapper.UpdateInfoMapper;
import com.hiqiblog.service.IUpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author helloc
 * @Date 2019/7/6 11:24
 * @Version 1.0
 */
@Service
public class UpdateInfoServiceImpl implements IUpdateInfoService {
    @Autowired
    UpdateInfoMapper updateInfoMapper;
    @Override
    public List<UpdateInfo> getList() {
        return updateInfoMapper.getAllList();
    }
}
