package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.MissionBasic;
import com.YYSchedule.common.mybatis.pojo.MissionBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MissionBasicMapper {
    int countByExample(MissionBasicExample example);

    int deleteByExample(MissionBasicExample example);

    int deleteByPrimaryKey(Integer missionId);

    int insert(MissionBasic record);

    int insertSelective(MissionBasic record);

    List<MissionBasic> selectByExample(MissionBasicExample example);

    MissionBasic selectByPrimaryKey(Integer missionId);

    int updateByExampleSelective(@Param("record") MissionBasic record, @Param("example") MissionBasicExample example);

    int updateByExample(@Param("record") MissionBasic record, @Param("example") MissionBasicExample example);

    int updateByPrimaryKeySelective(MissionBasic record);

    int updateByPrimaryKey(MissionBasic record);
}