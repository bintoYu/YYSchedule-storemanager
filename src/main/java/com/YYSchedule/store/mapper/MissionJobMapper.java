package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.MissionJob;
import com.YYSchedule.common.mybatis.pojo.MissionJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MissionJobMapper {
    int countByExample(MissionJobExample example);

    int deleteByExample(MissionJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MissionJob record);

    int insertSelective(MissionJob record);

    List<MissionJob> selectByExample(MissionJobExample example);

    MissionJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MissionJob record, @Param("example") MissionJobExample example);

    int updateByExample(@Param("record") MissionJob record, @Param("example") MissionJobExample example);

    int updateByPrimaryKeySelective(MissionJob record);

    int updateByPrimaryKey(MissionJob record);
}