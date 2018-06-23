package com.YYSchedule.store.service;

import com.YYSchedule.common.mybatis.pojo.MissionBasic;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-19
 */
public interface MissionBasicService {
    MissionBasic getMissionBasicMapperById(Integer missionId);
    
    int updateMissionBasic(MissionBasic missionBasic);
    
    int insertMissionBasic(MissionBasic missionBasic);
}
