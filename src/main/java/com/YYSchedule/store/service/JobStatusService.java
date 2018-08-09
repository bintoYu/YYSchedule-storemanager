package com.YYSchedule.store.service;

import com.YYSchedule.common.mybatis.pojo.JobStatus;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-25
 */
public interface JobStatusService {
    JobStatus getJobStatusById(Long jobId);
    
    int updateJobStatus(JobStatus jobStatus);
    
    int insertJobStatus(JobStatus jobStatus);
}
