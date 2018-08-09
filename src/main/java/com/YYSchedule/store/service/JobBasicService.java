package com.YYSchedule.store.service;

import com.YYSchedule.common.mybatis.pojo.JobBasic;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-25
 */
public interface JobBasicService {
    JobBasic getJobBasicById(Long jobId);
    
    int updateJobBasic(JobBasic jobBasic);
    
    int insertJobBasic(JobBasic jobBasic);
}
