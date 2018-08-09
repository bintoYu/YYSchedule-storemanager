package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.UserBasic;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-6-4
 */
public interface UserBasicService {
	List<UserBasic> getUserBasicList();
	
    UserBasic getUserBasicById(Integer userId);
    
    int updateUserBasic(UserBasic userBasic);
}
