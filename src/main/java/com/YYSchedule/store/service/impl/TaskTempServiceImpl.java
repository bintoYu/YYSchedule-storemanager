package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.TaskTemp;
import com.YYSchedule.common.mybatis.pojo.TaskTemp;
import com.YYSchedule.common.mybatis.pojo.TaskTempExample;
import com.YYSchedule.common.mybatis.pojo.TaskTempExample.Criteria;
import com.YYSchedule.store.mapper.TaskTempMapper;
import com.YYSchedule.store.service.TaskTempService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class TaskTempServiceImpl implements TaskTempService
{
	
    @Autowired
    private TaskTempMapper taskTempMapper;
	
	@Override
	public TaskTemp getTaskTempById(Long taskId)
	{
		TaskTempExample taskTempExample = new TaskTempExample();
		Criteria criteria = taskTempExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskTemp> taskTempList = taskTempMapper.selectByExample(taskTempExample);
	    TaskTemp taskTemp = null;
	    if(taskTempList != null && taskTempList.size() > 0 )
	    {
	        taskTemp = taskTempList.get(0);
	    }
	    return taskTemp;
	}
	
	@Override
	public int updateTaskTemp(TaskTemp taskTemp)
	{
		return taskTempMapper.updateByPrimaryKeySelective(taskTemp);
	}
	
	@Override
	public int insertTaskTemp(TaskTemp taskTemp)
	{
		return taskTempMapper.insertSelective(taskTemp);
	}
	
	@Override
	public int insertTaskTempList(List<TaskTemp> taskTempList)
	{
		int ret = 0;
		for(TaskTemp taskTemp : taskTempList)
		{
			ret += taskTempMapper.insertSelective(taskTemp);
		}
		
		return ret;
	}
	
}
