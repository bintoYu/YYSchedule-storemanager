package com.YYSchedule.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.MissionJob;
import com.YYSchedule.store.mapper.MissionJobMapper;
import com.YYSchedule.store.service.MissionJobService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-28
 */
@Service
public class MissionJobServiceImpl implements MissionJobService
{
	@Autowired
	private MissionJobMapper missionJobMapper;
	
	@Override
	public int insertMissionJob(MissionJob missionJob)
	{
		return missionJobMapper.insertSelective(missionJob);
	}
	
}
