package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.TaskResult;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-25
 */
public interface TaskResultService {
    TaskResult getTaskResultById(Long taskId);
    
    int updateTaskResult(TaskResult taskResult);
    
    int insertTaskResult(TaskResult taskResult);
    
    int insertTaskResultList(List<TaskResult> taskResultList);
    
    int updateTaskResultList(List<TaskResult> taskResultList);
}
