package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.UserBasic;
import com.YYSchedule.common.mybatis.pojo.UserBasicExample;
import com.YYSchedule.store.mapper.UserBasicMapper;
import com.YYSchedule.store.service.UserBasicService;

/**
 * @author yubingtao
 * @version 1.0
 * @date 2018-6-4
 */

@Service
public class UserBasicServiceImpl implements UserBasicService {
    @Autowired
    private UserBasicMapper userBasicMapper;

    @Override
    public UserBasic getUserBasicMapperById(Integer userId) {
        UserBasicExample userBasicExample = new UserBasicExample();
        UserBasicExample.Criteria criteria = userBasicExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        List<UserBasic> userBasicList = userBasicMapper.selectByExample(userBasicExample);
        UserBasic userBasic = null;
        if(userBasicList != null && userBasicList.size() > 0 )
        {
            userBasic = userBasicList.get(0);
        }
        return userBasic;
    }

	@Override
	public int updateUserBasic(UserBasic userBasic)
	{
		return userBasicMapper.updateByPrimaryKeySelective(userBasic);
	}
}
