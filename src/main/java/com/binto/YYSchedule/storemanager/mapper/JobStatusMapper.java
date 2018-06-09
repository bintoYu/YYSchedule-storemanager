package com.binto.YYSchedule.storemanager.mapper;

import com.binto.YYSchedule.common.mybatis.pojo.JobStatus;
import com.binto.YYSchedule.common.mybatis.pojo.JobStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobStatusMapper {
    int countByExample(JobStatusExample example);

    int deleteByExample(JobStatusExample example);

    int deleteByPrimaryKey(Integer jobId);

    int insert(JobStatus record);

    int insertSelective(JobStatus record);

    List<JobStatus> selectByExample(JobStatusExample example);

    JobStatus selectByPrimaryKey(Integer jobId);

    int updateByExampleSelective(@Param("record") JobStatus record, @Param("example") JobStatusExample example);

    int updateByExample(@Param("record") JobStatus record, @Param("example") JobStatusExample example);

    int updateByPrimaryKeySelective(JobStatus record);

    int updateByPrimaryKey(JobStatus record);
}