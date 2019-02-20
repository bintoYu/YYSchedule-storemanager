import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.YYSchedule.common.pojo.Task;
import com.YYSchedule.store.util.QueueConnectionFactory;

/**
 * 
 */

public class TestActivemq
{
	
	public void testSend()
	{
		Task task = new Task();
		task.setTaskId(111L);
		task.setFileName("aaa");
		String queue = "test";
		
		Connection connection = QueueConnectionFactory.createActiveMQConnection("failover:(tcp://192.168.1.184:61616)");
		Session session = QueueConnectionFactory.createSession(connection);
		MessageProducer producer = QueueConnectionFactory.createProducer(session, queue);
//		try
//		{
//			ActiveMQUtils_nospring.sendTask(task, session, producer, queue, 6);
//		} catch (JMSException e)
//		{
//			e.printStackTrace();
//		}finally
//		{
//			try
//			{
//				connection.close();
//			} catch (JMSException e)
//			{
//				e.printStackTrace();
//			}
//		}
	}
	
	public void testReceived()
	{
		Task task;
	}
	
	public static void main(String[] args)
	{
		new TestActivemq().testSend();
	}
}
