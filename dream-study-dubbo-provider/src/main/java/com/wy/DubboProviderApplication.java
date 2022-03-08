package com.wy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用dubbo-admin,需要修改解压后的war包里的dubbo.properties的zk地址
 * 
 * @author 飞花梦影
 * @date 2022-03-08 11:08:34
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@SpringBootApplication
public class DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProviderApplication.class, args);
	}
}