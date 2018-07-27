package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.JobStatus;
import com.YYSchedule.common.mybatis.pojo.JobStatusExample;
import com.YYSchedule.store.mapper.JobStatusMapper;
import com.YYSchedule.store.service.JobStatusService;
/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-25
 */
@Service
public class JobStatusServiceImpl implements JobStatusService
{
    @Autowired
    private JobStatusMapper jobStatusMapper;
    
	@Override
	public JobStatus getJobStatusById(Long jobId)
	{
        JobStatusExample jobStatusExample = new JobStatusExample();
        JobStatusExample.Criteria criteria = jobStatusExample.createCriteria();
        criteria.andJobIdEqualTo(jobId);

        List<JobStatus> jobStatusList = jobStatusMapper.selectByExample(jobStatusExample);
        JobStatus jobStatus = null;
        if(jobStatusList != null && jobStatusList.size() > 0 )
        {
            jobStatus = jobStatusList.get(0);
        }
        return jobStatus;
	}
	
	@Override
	public int updateJobStatus(JobStatus jobStatus)
	{
		return jobStatusMapper.updateByPrimaryKeySelective(jobStatus);
	}
	
	@Override
	public int insertJobStatus(JobStatus jobStatus)
	{
		return jobStatusMapper.insertSelective(jobStatus);
	}
	
}
