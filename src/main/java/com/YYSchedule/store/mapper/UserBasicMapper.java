package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.UserBasic;
import com.YYSchedule.common.mybatis.pojo.UserBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBasicMapper {
    int countByExample(UserBasicExample example);

    int deleteByExample(UserBasicExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserBasic record);

    int insertSelective(UserBasic record);

    List<UserBasic> selectByExample(UserBasicExample example);

    UserBasic selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserBasic record, @Param("example") UserBasicExample example);

    int updateByExample(@Param("record") UserBasic record, @Param("example") UserBasicExample example);

    int updateByPrimaryKeySelective(UserBasic record);

    int updateByPrimaryKey(UserBasic record);
}