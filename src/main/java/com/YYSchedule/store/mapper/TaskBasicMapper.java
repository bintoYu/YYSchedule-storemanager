package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.TaskBasic;
import com.YYSchedule.common.mybatis.pojo.TaskBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskBasicMapper {
    int countByExample(TaskBasicExample example);

    int deleteByExample(TaskBasicExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskBasic record);

    int insertSelective(TaskBasic record);

    List<TaskBasic> selectByExample(TaskBasicExample example);

    TaskBasic selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TaskBasic record, @Param("example") TaskBasicExample example);

    int updateByExample(@Param("record") TaskBasic record, @Param("example") TaskBasicExample example);

    int updateByPrimaryKeySelective(TaskBasic record);

    int updateByPrimaryKey(TaskBasic record);
}