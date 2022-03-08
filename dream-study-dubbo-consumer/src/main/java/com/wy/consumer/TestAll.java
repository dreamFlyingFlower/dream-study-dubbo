package com.wy.consumer;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wy.interfaces.ITest;

/**
 * @description
 * @author ParadiseWY
 * @date 2019年4月28日 下午4:57:11
 * @git {@link https://github.com/mygodness100}
 */
public class TestAll {

	private static final Logger logger = Logger.getLogger(TestAll.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] { "spring-dubboConsumer.xml" });
		context.start();
		ITest bean = (ITest) context.getBean("testService");
		logger.info(bean.test("这是测试!!"));
		context.close();
	}
}