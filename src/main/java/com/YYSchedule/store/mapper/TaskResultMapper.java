package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.TaskResult;
import com.YYSchedule.common.mybatis.pojo.TaskResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskResultMapper {
    int countByExample(TaskResultExample example);

    int deleteByExample(TaskResultExample example);

    int deleteByPrimaryKey(Long taskId);

    int insert(TaskResult record);

    int insertSelective(TaskResult record);

    List<TaskResult> selectByExample(TaskResultExample example);

    TaskResult selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") TaskResult record, @Param("example") TaskResultExample example);

    int updateByExample(@Param("record") TaskResult record, @Param("example") TaskResultExample example);

    int updateByPrimaryKeySelective(TaskResult record);

    int updateByPrimaryKey(TaskResult record);
}