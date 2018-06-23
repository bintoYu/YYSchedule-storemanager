package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.JobBasic;
import com.YYSchedule.common.mybatis.pojo.JobBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobBasicMapper {
    int countByExample(JobBasicExample example);

    int deleteByExample(JobBasicExample example);

    int deleteByPrimaryKey(Integer jobId);

    int insert(JobBasic record);

    int insertSelective(JobBasic record);

    List<JobBasic> selectByExample(JobBasicExample example);

    JobBasic selectByPrimaryKey(Integer jobId);

    int updateByExampleSelective(@Param("record") JobBasic record, @Param("example") JobBasicExample example);

    int updateByExample(@Param("record") JobBasic record, @Param("example") JobBasicExample example);

    int updateByPrimaryKeySelective(JobBasic record);

    int updateByPrimaryKey(JobBasic record);
}