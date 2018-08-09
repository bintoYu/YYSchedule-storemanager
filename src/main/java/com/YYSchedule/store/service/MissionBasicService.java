package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.MissionBasic;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-19
 */
public interface MissionBasicService {
	List<MissionBasic> getMissionBasicList();
	
    MissionBasic getMissionBasicById(Integer missionId);
    
    int updateMissionBasic(MissionBasic missionBasic);
    
    int insertMissionBasic(MissionBasic missionBasic);
}
