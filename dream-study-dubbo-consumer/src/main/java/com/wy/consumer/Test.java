package com.wy.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;

import com.wy.interfaces.ITest;

/**
 * {@link DubboReference}:表明该组件由Dubbo提供
 * 
 * <pre>
 * {@link DubboReference#version}:当有多个实现时,调用指定版本
 * {@link DubboReference#cache}:缓存策略
 * {@link DubboReference#timeout}:调用超时时间,单位毫秒
 * {@link DubboReference#async}:是否异步调用
 * {@link DubboReference#group}:服务分组,和配置文件以及需要调用的方法的分组相同
 * {@link DubboReference#cluster}:集群容错方式,failover:自动切换
 * </pre>
 * 
 * {@link Method}:对指定方法进行特殊处理
 * 
 * <pre>
 * {@link Method#name:方法名}:对指定方法进行特殊处理
 * {@link Method#onreturn:方法名}:返回时调用的方法
 * {@link Method#onthrow:方法名}:抛异常时调用的方法
 * </pre>
 * 
 * @author 飞花梦影
 * @date 2022-03-08 14:00:06
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public class Test {

	@DubboReference(version = "0.0.1",
			methods = { @Method(name = "test", onreturn = "testHandler.onReturn", onthrow = "testHandler.onThrow") })
	private ITest itest;

	public void test(String name) {
		// 隐式传参,从服务端存储才会有
		System.out.println(RpcContext.getServerContext().getAttachment("key"));
		System.out.println(itest);
		itest.test(name);
	}
}