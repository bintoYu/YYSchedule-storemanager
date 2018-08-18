/**
 * 
 */
package com.YYSchedule.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YYSchedule.common.mybatis.pojo.Node;
import com.YYSchedule.common.mybatis.pojo.NodeExample;
import com.YYSchedule.common.mybatis.pojo.NodeExample.Criteria;
import com.YYSchedule.store.mapper.NodeMapper;
import com.YYSchedule.store.service.NodeService;

/**
 * @author Administrator
 *
 * @date 2018年8月17日  
 * @version 1.0  
 */
@Service
public class NodeServiceImpl implements NodeService
{
	@Autowired
	private NodeMapper nodeMapper;
	
	@Override
	public List<Node> getNodeListByNodeId(String nodeId)
	{
		NodeExample nodeExample = new NodeExample();
		Criteria criteria = nodeExample.createCriteria();
		criteria.andNodeIdEqualTo(nodeId);
		nodeExample.setOrderByClause("update_time asc");
		
		return nodeMapper.selectByExample(nodeExample);
	}
	
	/**
	 * 获得一定时间范围内的所有node信息
	 */
	@Override
	public List<Node> getNodeListBetweenTime(Long startTime,Long endTime)
	{
		NodeExample nodeExample = new NodeExample();
		Criteria criteria = nodeExample.createCriteria();
		criteria.andUpdateTimeBetween(startTime, endTime);
		nodeExample.setOrderByClause("nodeId asc");
		
		return nodeMapper.selectByExample(nodeExample);
	}

	@Override
	public int insertNode(Node node)
	{
		return nodeMapper.updateByPrimaryKeySelective(node);
	}
	
	
}
