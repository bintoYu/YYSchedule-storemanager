/**
 * 
 */
package com.YYSchedule.store.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.apache.log4j.Logger;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.YYSchedule.common.pojo.Result;
import com.YYSchedule.common.pojo.Task;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ybt
 * 
 * @date 2018年7月12日
 * @version 1.0
 */
public class ActiveMQUtils
{
	private static final Logger LOGGER = Logger.getLogger(ActiveMQUtils.class);
	
	/**
	 * 发送task到指定queue 有优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param task
	 * @param priority
	 */
	public static void sendTask(JmsTemplate jmsTemplate, String queue, final Task task, int priority)
	{
		LOGGER.info(Thread.currentThread().getName() + " 向队列1" + queue + "发送task[" + task.getTaskId() + "]");
		Destination destination = new ActiveMQQueue(queue);
		// 设置队列优先级
		jmsTemplate.setExplicitQosEnabled(true);
		jmsTemplate.setPriority(priority);
		
		jmsTemplate.send(destination, new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(JSONObject.toJSONString(task));
			}
		});
	}
	
	/**
	 * 发送task到指定queue 无优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param task
	 */
	public static void sendTask(JmsTemplate jmsTemplate, String queue, final Task task)
	{
		LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送task[" + task.getTaskId() + "]");
		
		jmsTemplate.send(queue, new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(JSONObject.toJSONString(task));
			}
		});
	}
	
	/**
	 * 从指定queue中获取task
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @return task
	 * @throws JMSException
	 */
	public static Task receiveTask(JmsTemplate jmsTemplate, String queue) throws JMSException
	{
		Task task = null;
		
		try {
			jmsTemplate.setReceiveTimeout(1000*60);
			TextMessage textMessage = (TextMessage) jmsTemplate.receive(queue);
			if(textMessage != null)
			{
				String taskString = textMessage.getText();
				task = JSONObject.parseObject(taskString,Task.class);
			}
		} catch (JMSException e) {
			LOGGER.error("从队列" + queue + "中获取Task失败！");
			throw new JMSException("从队列" + queue + "中获取Task失败！");
		}
		return task;
	}
	
	/**
	 * 发送task到指定queue 无优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param result
	 */
	public static void sendResult(JmsTemplate jmsTemplate, String queue, final Result result)
	{
		LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送result[" + result.getTaskId() + "]");
		
		jmsTemplate.send(queue, new MessageCreator()
		{
			public ObjectMessage createMessage(Session session) throws JMSException
			{
				return session.createObjectMessage(result);
			}
		});
	}
	
	/**
	 * 从指定queue中获取task
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @return result
	 * @throws JMSException
	 */
	public static Result receiveResult(JmsTemplate jmsTemplate, String queue) throws JMSException
	{
		Result result = null;
		try {
			jmsTemplate.setReceiveTimeout(1000*60);
			ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive(queue);
			if(objectMessage != null)
				result = (Result) objectMessage.getObject();
		} catch (JMSException e) {
			LOGGER.error("从队列" + queue + "中获Result失败！");
			throw new JMSException("从队列" + queue + "中获取Result失败！");
		}
		return result;
	}
	
	/**
	 * 发送message到指定queue 无优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param message
	 */
	public static void sendMessage(JmsTemplate jmsTemplate, String queue, final String message)
	{
		LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送消息--------");
		
		jmsTemplate.send(queue, new MessageCreator()
		{
			public Message createMessage(Session session) throws JMSException
			{
				return session.createTextMessage(message);
			}
		});
	}
	
	/**
	 * 从指定queue中获取message
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @return message
	 * @throws JMSException
	 */
	public static String receiveMessage(JmsTemplate jmsTemplate, String queue) throws JMSException
	{
		TextMessage tm = (TextMessage) jmsTemplate.receive(queue);

		return tm.getText();
	}
	
	/**
	 * 
	 * @param queue
	 * @return 队列未消费的信息数queueSize
	 */
	public static int getQueueSize(JmsTemplate jmsTemplate, String queue)
	{
		Integer queueSize = jmsTemplate.browse(queue, new BrowserCallback<Integer>()
		{
			
			@Override
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException
			{
				Enumeration enumeration = browser.getEnumeration();
				int num = 0;
				while (enumeration.hasMoreElements()) {
					enumeration.nextElement();
					num++;
				}
				return num;
			}
			
		});
		
		return queueSize;
	}
	
	/**
	 * 
	 * @param queue
	 * @return 队列中所有消息的taskId属性组成的list
	 */
	public static List<Long> getQueueTaskIdList(JmsTemplate jmsTemplate, String queue)
	{
		List<Long> taskIdList = jmsTemplate.browse(queue, new BrowserCallback<List<Long>>()
		{
			
			@Override
			public List<Long> doInJms(Session session, QueueBrowser browser) throws JMSException
			{
				List<Long> taskIdList = new ArrayList<Long>();
				
				@SuppressWarnings("unchecked")
				Enumeration<ObjectMessage> enumeration = browser.getEnumeration();
				while (enumeration.hasMoreElements()) {
					ObjectMessage objectMessage = enumeration.nextElement();
					Task task = (Task) objectMessage.getObject();
					if (task != null) {
						taskIdList.add(task.getTaskId());
					}
				}
				return taskIdList;
			}
			
		});
		
		return taskIdList;
	}
	
	/**
	 * 
	 * @param queue
	 * @return 队列中所有消息的timeout属性组成的list
	 */
	public static List<Long> getQueueTimeoutList(JmsTemplate jmsTemplate, String queue)
	{
		List<Long> timeoutList = jmsTemplate.browse(queue, new BrowserCallback<List<Long>>()
		{
			
			@Override
			public List<Long> doInJms(Session session, QueueBrowser browser) throws JMSException
			{
				List<Long> timeoutList = new ArrayList<Long>();
				
				@SuppressWarnings("unchecked")
				Enumeration<ObjectMessage> enumeration = browser.getEnumeration();
				while (enumeration.hasMoreElements()) {
					ObjectMessage objectMessage = enumeration.nextElement();
					Task task = (Task) objectMessage.getObject();
					if (task != null) {
						timeoutList.add(task.getTimeout());
					}
				}
				return timeoutList;
			}
			
		});
		
		return timeoutList;
	}
}
