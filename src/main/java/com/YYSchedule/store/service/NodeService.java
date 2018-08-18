package com.YYSchedule.store.service;

import java.util.List;

import com.YYSchedule.common.mybatis.pojo.Node;

/**
 * @author ybt
 * @version 1.0
 * @date 2018-8-17
 */
public interface NodeService {
    List<Node> getNodeListByNodeId(String nodeId);
    
    List<Node> getNodeListBetweenTime(Long startTime,Long endTime);
    
    int insertNode(Node node);
    
}
