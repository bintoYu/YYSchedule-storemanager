package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.TaskFile;
import com.YYSchedule.common.mybatis.pojo.TaskFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskFileMapper {
    int countByExample(TaskFileExample example);

    int deleteByExample(TaskFileExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskFile record);

    int insertSelective(TaskFile record);

    List<TaskFile> selectByExample(TaskFileExample example);

    TaskFile selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TaskFile record, @Param("example") TaskFileExample example);

    int updateByExample(@Param("record") TaskFile record, @Param("example") TaskFileExample example);

    int updateByPrimaryKeySelective(TaskFile record);

    int updateByPrimaryKey(TaskFile record);
}