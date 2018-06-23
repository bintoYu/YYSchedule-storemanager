package com.YYSchedule.store.mapper;

import com.YYSchedule.common.mybatis.pojo.FileResource;
import com.YYSchedule.common.mybatis.pojo.FileResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileResourceMapper {
    int countByExample(FileResourceExample example);

    int deleteByExample(FileResourceExample example);

    int deleteByPrimaryKey(String fileChecksum);

    int insert(FileResource record);

    int insertSelective(FileResource record);

    List<FileResource> selectByExample(FileResourceExample example);

    FileResource selectByPrimaryKey(String fileChecksum);

    int updateByExampleSelective(@Param("record") FileResource record, @Param("example") FileResourceExample example);

    int updateByExample(@Param("record") FileResource record, @Param("example") FileResourceExample example);

    int updateByPrimaryKeySelective(FileResource record);

    int updateByPrimaryKey(FileResource record);
}