package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.TaskTimestamp;
import com.YYSchedule.common.mybatis.pojo.TaskTimestampExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTimestampMapper {
    int countByExample(TaskTimestampExample example);

    int deleteByExample(TaskTimestampExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskTimestamp record);

    int insertSelective(TaskTimestamp record);

    List<TaskTimestamp> selectByExample(TaskTimestampExample example);

    TaskTimestamp selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TaskTimestamp record, @Param("example") TaskTimestampExample example);

    int updateByExample(@Param("record") TaskTimestamp record, @Param("example") TaskTimestampExample example);

    int updateByPrimaryKeySelective(TaskTimestamp record);

    int updateByPrimaryKey(TaskTimestamp record);
}