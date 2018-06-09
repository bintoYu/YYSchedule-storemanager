package com.binto.YYSchedule.storemanager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.binto.YYSchedule.common.mybatis.pojo.UserBasic;
import com.binto.YYSchedule.common.mybatis.pojo.UserBasicExample;
import com.binto.YYSchedule.storemanager.mapper.UserBasicMapper;
import com.binto.YYSchedule.storemanager.service.UserBasicService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-4
 */

@Service
public class UserBasicServiceImpl implements UserBasicService {
    @Resource
    UserBasicMapper userBasicMapper;

    @Override
    public UserBasic getUserBasicMapperById(Integer userId) {
        UserBasicExample userBasicExample = new UserBasicExample();
        UserBasicExample.Criteria criteria = userBasicExample.createCriteria();
        criteria.andUserIdEqualTo(1);

        List<UserBasic> userBasicList = userBasicMapper.selectByExample(userBasicExample);
        UserBasic userBasic = null;
        if(userBasicList != null && userBasicList.size() > 0 )
        {
            userBasic = userBasicList.get(0);
        }
        return userBasic;
    }
}
