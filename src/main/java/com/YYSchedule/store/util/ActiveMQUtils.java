/**
 * 
 */
package com.YYSchedule.store.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.YYSchedule.common.pojo.Task;
import com.YYSchedule.common.rpc.domain.container.Context;

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
	 * 发送context到指定queue
	 * 有优先级
	 * 
	 * @param jmsTemplate
	 * @param queue
	 * @param context
	 * @param priority
	 */
	 public static void sendContext(JmsTemplate jmsTemplate,String queue,final Context context, int priority)
	 {
	        LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送Context[" + context.getTaskId() + "]");
	        
	        //设置队列优先级
	        jmsTemplate.setExplicitQosEnabled(true);
	        jmsTemplate.setPriority(priority);
	        
	        jmsTemplate.send(queue,new MessageCreator() {
	            public ObjectMessage createMessage(Session session) throws JMSException {
	                return session.createObjectMessage(context);
	            }
	        });
	    }
	 
		/**
		 * 发送context到指定queue
		 * 无优先级
		 * 
		 * @param jmsTemplate
		 * @param queue
		 * @param context
		 * @param priority
		 */
		 public static void sendContext(JmsTemplate jmsTemplate,String queue,final Context context)
		 {
		        LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送Context[" + context.getTaskId() + "]");
		        
		        jmsTemplate.send(queue,new MessageCreator() {
		            public ObjectMessage createMessage(Session session) throws JMSException {
		                return session.createObjectMessage(context);
		            }
		        });
		    }
	 
	 
	 /**
	  * 从指定queue中获取context
	  * 
	  * @param jmsTemplate
	  * @param queue
	  * @return context
	 * @throws JMSException 
	  */
	 public static Context receiveContext(JmsTemplate jmsTemplate,String queue) throws JMSException
	 {
		 Context context;
		 try {
			 ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive(queue);
			 context = (Context)objectMessage.getObject();
	        } catch (JMSException e) {
	            LOGGER.error("从队列"+queue+"中获取Context失败！");
	            throw new JMSException("从队列"+queue+"中获取Context失败！");
	        }
		 return context;
	 }
	 
	 
	 /**
		 * 发送task到指定queue
		 * 有优先级
		 * 
		 * @param jmsTemplate
		 * @param queue
		 * @param context
		 * @param priority
		 */
		 public static void sendTask(JmsTemplate jmsTemplate,String queue,final Task task, int priority)
		 {
		        LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送task[" + task.getTaskId() + "]");
		        
		        //设置队列优先级
		        jmsTemplate.setExplicitQosEnabled(true);
		        jmsTemplate.setPriority(priority);
		        
		        jmsTemplate.send(queue,new MessageCreator() {
		            public ObjectMessage createMessage(Session session) throws JMSException {
		                return session.createObjectMessage(task);
		            }
		        });
		    }
		 
			/**
			 * 发送task到指定queue
			 * 无优先级
			 * 
			 * @param jmsTemplate
			 * @param queue
			 * @param task
			 */
			 public static void sendTask(JmsTemplate jmsTemplate,String queue,final Task task)
			 {
			        LOGGER.info(Thread.currentThread().getName() + " 向队列" + queue + "发送task[" + task.getTaskId() + "]");
			        
			        jmsTemplate.send(queue,new MessageCreator() {
			            public ObjectMessage createMessage(Session session) throws JMSException {
			                return session.createObjectMessage(task);
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
		 public static Task receiveTask(JmsTemplate jmsTemplate,String queue) throws JMSException
		 {
			 Task task;
			 try {
				 ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive(queue);
				 task = (Task)objectMessage.getObject();
		        } catch (JMSException e) {
		            LOGGER.error("从队列"+queue+"中获取Context失败！");
		            throw new JMSException("从队列"+queue+"中获取Context失败！");
		        }
			 return task;
		 }
		 
		 /**
		  * 
		  * @param queue
		  * @return 队列未消费的信息数queueSize
		  */
		 public static int getQueueSize(JmsTemplate jmsTemplate,String queue)
		 {
			 Integer queueSize = jmsTemplate.browse(queue, new BrowserCallback<Integer>(){

				@Override
				public Integer doInJms(Session session, QueueBrowser browser)
						throws JMSException
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
	 public static List<Long> getQueueTaskIdList(JmsTemplate jmsTemplate,String queue)
	 {
		 List<Long> taskIdList = jmsTemplate.browse(queue, new BrowserCallback<List<Long>>(){

			@Override
			public List<Long> doInJms(Session session, QueueBrowser browser)
					throws JMSException
			{
				List<Long> taskIdList = new ArrayList<Long>();
				
				@SuppressWarnings("unchecked")
				Enumeration<ObjectMessage> enumeration = browser.getEnumeration();
		        while (enumeration.hasMoreElements()) {
		        	ObjectMessage objectMessage = enumeration.nextElement();
		        	Context context = (Context)objectMessage.getObject();
		        	if(context!=null)
		        	{
		        		taskIdList.add(context.getTaskId());
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
	 public static List<Long> getQueueTimeoutList(JmsTemplate jmsTemplate,String queue)
	 {
		 List<Long> timeoutList = jmsTemplate.browse(queue, new BrowserCallback<List<Long>>(){

			@Override
			public List<Long> doInJms(Session session, QueueBrowser browser)
					throws JMSException
			{
				List<Long> timeoutList = new ArrayList<Long>();
				
				@SuppressWarnings("unchecked")
				Enumeration<ObjectMessage> enumeration = browser.getEnumeration();
		        while (enumeration.hasMoreElements()) {
		        	ObjectMessage objectMessage = enumeration.nextElement();
		        	Context context = (Context)objectMessage.getObject();
		        	if(context!=null)
		        	{
		        		timeoutList.add(context.getTimeout());
		        	}
		        }
		        return timeoutList;
			}
			 
		});
		 
		return timeoutList;
	 }
}

