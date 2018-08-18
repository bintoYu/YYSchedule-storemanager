package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.TaskTimestamp;
import com.YYSchedule.common.mybatis.pojo.TaskTimestampExample;
import com.YYSchedule.common.mybatis.pojo.TaskTimestampExample.Criteria;
import com.YYSchedule.store.mapper.TaskTimestampMapper;
import com.YYSchedule.store.service.TaskTimestampService;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class TaskTimestampServiceImpl implements TaskTimestampService
{
	
    @Autowired
    private TaskTimestampMapper taskTimestampMapper;
	
	@Override
	public TaskTimestamp getTaskTimestampById(Long taskId)
	{
		TaskTimestampExample taskTimestampExample = new TaskTimestampExample();
		Criteria criteria = taskTimestampExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskTimestamp> taskTimestampList = taskTimestampMapper.selectByExample(taskTimestampExample);
	    TaskTimestamp taskTimestamp = null;
	    if(taskTimestampList != null && taskTimestampList.size() > 0 )
	    {
	        taskTimestamp = taskTimestampList.get(0);
	    }
	    return taskTimestamp;
	}

	@Override
	public TaskTimestamp getTaskTimestampByIdAndTaskPhase(Long taskId)
	{
		TaskTimestampExample taskTimestampExample = new TaskTimestampExample();
		Criteria criteria = taskTimestampExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskTimestamp> taskTimestampList = taskTimestampMapper.selectByExample(taskTimestampExample);
	    TaskTimestamp taskTimestamp = null;
	    if(taskTimestampList != null && taskTimestampList.size() > 0 )
	    {
	        taskTimestamp = taskTimestampList.get(0);
	    }
	    return taskTimestamp;
	}

	@Override
	public int updateTaskTimestamp(TaskTimestamp taskTimestamp)
	{
		return taskTimestampMapper.updateByPrimaryKeySelective(taskTimestamp);
	}
	
	@Override
	public int insertTaskTimestamp(TaskTimestamp taskTimestamp)
	{
		return taskTimestampMapper.insertSelective(taskTimestamp);
	}
	
	@Override
	public int insertTaskTimestampList(List<TaskTimestamp> taskTimestampList)
	{
		int ret = 0;
		for(TaskTimestamp taskTimestamp : taskTimestampList)
		{
			ret += taskTimestampMapper.insertSelective(taskTimestamp);
		}
		
		return ret;
	}

	
}
