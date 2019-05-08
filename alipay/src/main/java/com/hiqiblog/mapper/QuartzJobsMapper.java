package com.hiqiblog.mapper;


import com.hiqiblog.entity.QuartzJobs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author ww
 */

public interface QuartzJobsMapper {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(QuartzJobs record);
    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(QuartzJobs record);
    /**
     * 根据id查找
     * @param id
     * @return
     */
    QuartzJobs selectByPrimaryKey(Integer id);
    /**
     * 根据实体更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(QuartzJobs record);
    /**
     * 根据实体中的主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(QuartzJobs record);
    /**
     * 根据jobName获取所有job
     * @param jobName
     * @return
     */
    List<QuartzJobs> listJob(String jobName);

    /**
     * 根据jobName和jobGroup查询实体
     * @param jobName
     * @param jobGroup
     * @return
     */
    QuartzJobs getJob(String jobName,String jobGroup);


}