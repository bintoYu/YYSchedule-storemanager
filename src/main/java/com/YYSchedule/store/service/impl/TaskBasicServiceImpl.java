package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.TaskBasic;
import com.YYSchedule.common.mybatis.pojo.TaskBasic;
import com.YYSchedule.common.mybatis.pojo.TaskBasicExample;
import com.YYSchedule.common.mybatis.pojo.TaskBasicExample.Criteria;
import com.YYSchedule.common.rpc.domain.task.TaskPhase;
import com.YYSchedule.store.mapper.TaskBasicMapper;
import com.YYSchedule.store.service.TaskBasicService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class TaskBasicServiceImpl implements TaskBasicService
{
	
    @Autowired
    private TaskBasicMapper taskBasicMapper;
	
	@Override
	public TaskBasic getTaskBasicById(Long taskId)
	{
		TaskBasicExample taskBasicExample = new TaskBasicExample();
		Criteria criteria = taskBasicExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskBasic> taskBasicList = taskBasicMapper.selectByExample(taskBasicExample);
	    TaskBasic taskBasic = null;
	    if(taskBasicList != null && taskBasicList.size() > 0 )
	    {
	        taskBasic = taskBasicList.get(0);
	    }
	    return taskBasic;
	}

	@Override
	public TaskBasic getTaskBasicByIdAndTaskPhase(Long taskId,
			TaskPhase taskPhase)
	{
		TaskBasicExample taskBasicExample = new TaskBasicExample();
		Criteria criteria = taskBasicExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		criteria.andTaskPhaseEqualTo(taskPhase.toString());
		
		List<TaskBasic> taskBasicList = taskBasicMapper.selectByExample(taskBasicExample);
	    TaskBasic taskBasic = null;
	    if(taskBasicList != null && taskBasicList.size() > 0 )
	    {
	        taskBasic = taskBasicList.get(0);
	    }
	    return taskBasic;
	}

	@Override
	public int updateTaskBasic(TaskBasic taskBasic)
	{
		return taskBasicMapper.updateByPrimaryKeySelective(taskBasic);
	}
	
	@Override
	public int insertTaskBasic(TaskBasic taskBasic)
	{
		return taskBasicMapper.insertSelective(taskBasic);
	}
	
	@Override
	public int insertTaskBasicList(List<TaskBasic> taskBasicList)
	{
		int ret = 0;
		for(TaskBasic taskBasic : taskBasicList)
		{
			ret += taskBasicMapper.insertSelective(taskBasic);
		}
		
		return ret;
	}

	
}
