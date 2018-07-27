package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.TaskFile;
import com.YYSchedule.common.mybatis.pojo.TaskFile;
import com.YYSchedule.common.mybatis.pojo.TaskFileExample;
import com.YYSchedule.common.mybatis.pojo.TaskFileExample.Criteria;
import com.YYSchedule.store.mapper.TaskFileMapper;
import com.YYSchedule.store.service.TaskFileService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class TaskFileServiceImpl implements TaskFileService
{
	
    @Autowired
    private TaskFileMapper taskFileMapper;
	
	@Override
	public TaskFile getTaskFileById(Long taskId)
	{
		TaskFileExample taskFileExample = new TaskFileExample();
		Criteria criteria = taskFileExample.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		
		List<TaskFile> taskFileList = taskFileMapper.selectByExample(taskFileExample);
	    TaskFile taskFile = null;
	    if(taskFileList != null && taskFileList.size() > 0 )
	    {
	        taskFile = taskFileList.get(0);
	    }
	    return taskFile;
	}
	
	@Override
	public int updateTaskFile(TaskFile taskFile)
	{
		return taskFileMapper.updateByPrimaryKeySelective(taskFile);
	}
	
	@Override
	public int insertTaskFile(TaskFile taskFile)
	{
		return taskFileMapper.insertSelective(taskFile);
	}
	
	@Override
	public int insertTaskFileList(List<TaskFile> taskFileList)
	{
		int ret = 0;
		for(TaskFile taskFile : taskFileList)
		{
			ret += taskFileMapper.insertSelective(taskFile);
		}
		
		return ret;
	}
	
}
