package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.TaskFile;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-25
 */
public interface TaskFileService {
    TaskFile getTaskFileById(Long taskId);
    
    int updateTaskFile(TaskFile taskFile);
    
    int insertTaskFile(TaskFile taskFile);
    
    int insertTaskFileList(List<TaskFile> taskFileList);
}
