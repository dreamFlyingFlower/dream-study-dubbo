package com.wy.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import com.wy.interfaces.ITest;

/**
 * dubbo接口的实现类
 * 
 * {@link DubboService}:表明该实现类将暴露给Dubbo服务,作用同spring-dubboProvider.xml中的<dubbo:service>标签
 * 
 * <pre>
 * {@link DubboService#register}:该协议的服务是否注册到注册中心
 * {@link DubboService#registry}:多个注册中心时使用,向指定注册中心注册,值为registry.id,多个注册中心ID用逗号分隔,如果不想注册到任何registry,可将值设为N/A
 * {@link DubboService#stub}:本地存根,类似于一个策略类,主要做调用前的相关检测等,最终调用的是存根类
 * {@link DubboService#version}:调用指定版本.生产者和消费者都要指定
 * {@link DubboService#registry}:注册到指定的注册中心上,可指定多个,值为配置文件中registry的id值
 * {@link DubboService#protocol}:指定调用者的协议,可指定多种,值为配置文件中protocol的name值
 * {@link DubboService#cache}:缓存策略
 * {@link DubboService#group}:服务分组,该名称和配置文件中registry的group属性相同
 * {@link DubboService#mock}:服务降级.降级的类也需要实现被降级类同样的接口,需要填写降级类的全名
 * </pre>
 * 
 * @author 飞花梦影
 * @date 2022-03-08 11:09:58
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@DubboService(version = "0.0.0", mock = "com.wy.service.TestMockService")
public class TestService implements ITest {

	@Override
	public String test(String name) {
		// 隐式传参,在调用方用get即可
		RpcContext.getServerContext().setAttachment("key", "value");
		// RpcContext.getServerContext().getAttachment("key");
		return "传过来的name是:" + name;
	}
}