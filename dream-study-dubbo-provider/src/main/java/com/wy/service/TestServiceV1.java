package com.wy.service;

import org.apache.dubbo.config.annotation.DubboService;

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
 * </pre>
 * 
 * @author 飞花梦影
 * @date 2022-03-08 11:09:58
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@DubboService(version = "0.0.1")
public class TestServiceV1 implements ITest {

	@Override
	public String test(String name) {
		return "传过来的name是:" + name;
	}
}