package com.YYSchedule.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.MissionBasic;
import com.YYSchedule.common.mybatis.pojo.MissionBasicExample;
import com.YYSchedule.common.mybatis.pojo.UserBasic;
import com.YYSchedule.store.mapper.MissionBasicMapper;
import com.YYSchedule.store.service.MissionBasicService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-19
 */

@Service
public class MissionBasicServiceImpl implements MissionBasicService
{
	@Resource
	private MissionBasicMapper missionBasicMapper;
	
	@Override
	public MissionBasic getMissionBasicMapperById(Integer missionId)
	{
		MissionBasicExample missionBasicExample = new MissionBasicExample();
		MissionBasicExample.Criteria criteria = missionBasicExample.createCriteria();
		criteria.andMissionIdEqualTo(missionId);
		
		List<MissionBasic> missionBasicList = missionBasicMapper.selectByExample(missionBasicExample);
		MissionBasic missionBasic = null;
        if(missionBasicList != null && missionBasicList.size() > 0 )
        {
        	missionBasic = missionBasicList.get(0);
        }
		
		return missionBasic;
	}
	
	@Override
	public int updateMissionBasic(MissionBasic missionBasic)
	{
		return missionBasicMapper.updateByPrimaryKeySelective(missionBasic);
	}

	@Override
	public int insertMissionBasic(MissionBasic missionBasic)
	{
		return missionBasicMapper.insert(missionBasic);
	}
	
}