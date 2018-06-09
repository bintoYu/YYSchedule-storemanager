package test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.binto.YYSchedule.common.mybatis.pojo.UserBasic;
import com.binto.YYSchedule.storemanager.service.UserBasicService;

public class StoremanagerApplicationTests
{
	private ApplicationContext applicationContext;

	@Before
	public void init() {
		//创建一个spring容器
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}
	
    @Test
    public void testGetUserBasicMapperById()
    {
        UserBasicService userBasicService = applicationContext.getBean(UserBasicService.class);
    	
        int userId = 1;

        UserBasic userBasic= userBasicService.getUserBasicMapperById(userId);

        System.out.println(userBasic.getUsername());
    }

}
