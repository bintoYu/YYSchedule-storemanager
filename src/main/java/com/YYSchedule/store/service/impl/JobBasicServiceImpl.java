package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.JobBasic;
import com.YYSchedule.common.mybatis.pojo.JobBasicExample;
import com.YYSchedule.store.mapper.JobBasicMapper;
import com.YYSchedule.store.service.JobBasicService;
/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-25
 */
@Service
public class JobBasicServiceImpl implements JobBasicService
{
    @Autowired
    private JobBasicMapper jobBasicMapper;
    
	@Override
	public JobBasic getJobBasicById(Long jobId)
	{
        JobBasicExample jobBasicExample = new JobBasicExample();
        JobBasicExample.Criteria criteria = jobBasicExample.createCriteria();
        criteria.andJobIdEqualTo(jobId);

        List<JobBasic> jobBasicList = jobBasicMapper.selectByExample(jobBasicExample);
        JobBasic jobBasic = null;
        if(jobBasicList != null && jobBasicList.size() > 0 )
        {
            jobBasic = jobBasicList.get(0);
        }
        return jobBasic;
	}
	
	@Override
	public int updateJobBasic(JobBasic jobBasic)
	{
		return jobBasicMapper.updateByPrimaryKeySelective(jobBasic);
	}
	
	@Override
	public int insertJobBasic(JobBasic jobBasic)
	{
		return jobBasicMapper.insertSelective(jobBasic);
	}
	
}
