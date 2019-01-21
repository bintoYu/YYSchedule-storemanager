/**
 * 
 */
package com.YYSchedule.store.util;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.YYSchedule.common.pojo.Result;
import com.YYSchedule.common.pojo.Task;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ActiveMQUtils_nospring
{
	private static final Logger LOGGER = Logger.getLogger(ActiveMQUtils_nospring.class);
	
	public final static void sendTask(final Task task, Session session,MessageProducer producer, String queue, int priority) throws JMSException
	{
		try
		{
			LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送task[" + task.getTaskId() + "]");
			TextMessage message = session.createTextMessage(JSON.toJSONString(task));
			
			Destination destination = session.createQueue(queue);
			producer.send(destination, message, DeliveryMode.PERSISTENT, priority, 0);
		} catch (JMSException e)
		{
			LOGGER.error("无法发送Task[" + task.getTaskId() + "]到队列" + queue + "中！");
			throw new JMSException("无法发送Task[" + task.getTaskId() + "]到队列" + queue + "中！");
		}
	}
	
	public static Task receiveTask(MessageConsumer consumer,String queue) throws JMSException
	{
		Task task = null;

		try {
			TextMessage textMessage = (TextMessage)consumer.receive();
			if(textMessage != null)
			{
				String taskString = textMessage.getText();
				task = JSONObject.parseObject(taskString,Task.class);
				LOGGER.info(Thread.currentThread().getName() + " 从队列" + queue + "中收到task[" + task.getTaskId() + "]");
			}
		} catch (JMSException e) {
			LOGGER.error("从队列" + queue + "中获取Task失败！");
			throw new JMSException("从队列" + queue + "中获取Task失败！");
		}
		return task;
	}
	
	public final static void sendMessage(final String message, Session session,MessageProducer producer, String queue, int priority) throws JMSException
	{
		
		LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送消息" + message + "--------");
		try
		{
			TextMessage textMessage = session.createTextMessage(message);
			
			Destination destination = session.createQueue(queue);
			producer.send(destination, textMessage, DeliveryMode.PERSISTENT, priority, 0);
		} catch (JMSException e)
		{
			LOGGER.error("无法发送Message[" + message + "]到队列" + queue + "中！");
			throw new JMSException("无法发送Message[" + message + "]到队列" + queue + "中！");
		}
	}
	
	public static String receiveMessage(MessageConsumer consumer,String queue) throws JMSException
	{
		String message = null;

		try {
			TextMessage textMessage = (TextMessage)consumer.receive();
			if(textMessage != null)
			{
				message = textMessage.getText();
				LOGGER.info(Thread.currentThread().getName() + " 从队列" + queue + "中收到消息" + message);
			}
		} catch (JMSException e) {
			LOGGER.error("从队列" + queue + "中获取Message失败！");
			throw new JMSException("从队列" + queue + "中获取Message失败！");
		}

		return message;
	}
	
	/**
	 * 发送task到指定queue 无优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param result
	 */
	public static void sendResult(final Result result, Session session,MessageProducer producer, String queue) throws JMSException
	{
		LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送result[" + result.getTaskId() + "]");
		
		try
		{
			TextMessage message = session.createTextMessage(JSON.toJSONString(result));
			
			Destination destination = session.createQueue(queue);
			producer.send(destination, message, DeliveryMode.PERSISTENT, 0, 0);
		} catch (JMSException e)
		{
			LOGGER.error("无法发送Result[" + result.getTaskId() + "]到队列" + queue + "中！");
			throw new JMSException("无法发送Result[" + result.getTaskId() + "]到队列" + queue + "中！");
		}
	}
	
	/**
	 * 从指定queue中获取task
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @return result
	 * @throws JMSException
	 */
	public static Result receiveResult(MessageConsumer consumer,String queue) throws JMSException
	{
		Result result = null;
		try {
			TextMessage textMessage = (TextMessage)consumer.receive();
			if(textMessage != null)
			{
				String resultString = textMessage.getText();
				result = JSONObject.parseObject(resultString,Result.class);
				LOGGER.info(Thread.currentThread().getName() + " 从队列" + queue + "中收到Result[" + result.getTaskId() + "]");
			}
		} catch (JMSException e) {
			LOGGER.error("从队列" + queue + "中获取Result失败！");
			throw new JMSException("从队列" + queue + "中获取Result失败！");
		}

		return result;
	}
}
