package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.TaskTemp;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-25
 */
public interface TaskTempService {
    TaskTemp getTaskTempById(Long taskId);
    
    int updateTaskTemp(TaskTemp taskTemp);
    
    int insertTaskTemp(TaskTemp taskTemp);
    
    int insertTaskTempList(List<TaskTemp> taskTempList);
}
