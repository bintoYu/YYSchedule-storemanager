package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.TaskTimestamp;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-25
 */
public interface TaskTimestampService {
    TaskTimestamp getTaskTimestampById(Long taskId);
    
    TaskTimestamp getTaskTimestampByIdAndTaskPhase(Long taskId);
    
    int updateTaskTimestamp(TaskTimestamp taskTimestamp);
    
    int insertTaskTimestamp(TaskTimestamp taskTimestamp);
    
    int insertTaskTimestampList(List<TaskTimestamp> taskTimestampList);
}
