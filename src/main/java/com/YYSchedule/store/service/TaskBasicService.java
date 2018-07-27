package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.TaskBasic;
import com.YYSchedule.common.rpc.domain.task.TaskPhase;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-25
 */
public interface TaskBasicService {
    TaskBasic getTaskBasicById(Long taskId);
    
    TaskBasic getTaskBasicByIdAndTaskPhase(Long taskId,TaskPhase taskPhase);
    
    int updateTaskBasic(TaskBasic taskBasic);
    
    int insertTaskBasic(TaskBasic taskBasic);
    
    int insertTaskBasicList(List<TaskBasic> taskBasicList);
}
