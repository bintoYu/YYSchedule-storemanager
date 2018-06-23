package com.YYSchedule.store.service;

import com.YYSchedule.common.mybatis.pojo.UserBasic;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-4
 */
public interface UserBasicService {
    UserBasic getUserBasicMapperById(Integer userId);
    
    int updateUserBasic(UserBasic userBasic);
}
