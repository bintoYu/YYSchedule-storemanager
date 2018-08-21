package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.TaskTemp;
import com.YYSchedule.common.mybatis.pojo.TaskTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskTempMapper {
    int countByExample(TaskTempExample example);

    int deleteByExample(TaskTempExample example);

    int deleteByPrimaryKey(Long taskId);

    int insert(TaskTemp record);

    int insertSelective(TaskTemp record);

    List<TaskTemp> selectByExample(TaskTempExample example);

    TaskTemp selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") TaskTemp record, @Param("example") TaskTempExample example);

    int updateByExample(@Param("record") TaskTemp record, @Param("example") TaskTempExample example);

    int updateByPrimaryKeySelective(TaskTemp record);

    int updateByPrimaryKey(TaskTemp record);
}