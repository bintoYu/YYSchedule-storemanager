package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.TaskResult;
import com.YYSchedule.common.mybatis.pojo.TaskResultExample;
import com.YYSchedule.common.mybatis.pojo.TaskResultExample.Criteria;
import com.YYSchedule.store.mapper.TaskResultMapper;
import com.YYSchedule.store.service.TaskResultService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class TaskResultServiceImpl implements TaskResultService
{
	
    @Autowired
    private TaskResultMapper taskResultMapper;
	
	@Override
	public TaskResult getTaskResultById(Long taskId)
	{
		TaskResultExample taskResultExample = new TaskResultExample();
		Criteria criteria = taskResultExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskResult> taskResultList = taskResultMapper.selectByExample(taskResultExample);
	    TaskResult taskResult = null;
	    if(taskResultList != null && taskResultList.size() > 0 )
	    {
	        taskResult = taskResultList.get(0);
	    }
	    return taskResult;
	}
	
	@Override
	public int updateTaskResult(TaskResult taskResult)
	{
		int ret = taskResultMapper.updateByPrimaryKeySelective(taskResult);
		if(ret==0)
		{
			ret = taskResultMapper.insertSelective(taskResult);
		}
		return ret;
	}
	
	@Override
	public int insertTaskResult(TaskResult taskResult)
	{
		return taskResultMapper.insertSelective(taskResult);
	}
	
	@Override
	public int insertTaskResultList(List<TaskResult> taskResultList)
	{
		int ret = 0;
		for(TaskResult taskResult : taskResultList)
		{
			ret += insertTaskResult(taskResult);
		}
		
		return ret;
	}

	@Override
	public int updateTaskResultList(List<TaskResult> taskResultList)
	{
		int ret = 0;
		for(TaskResult taskResult : taskResultList)
		{
			ret += updateTaskResult(taskResult);
		}
		return ret;
	}
	
}
