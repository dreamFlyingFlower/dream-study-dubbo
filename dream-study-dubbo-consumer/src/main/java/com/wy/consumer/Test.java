package com.wy.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.LeastActiveLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RandomLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.RoundRobinLoadBalance;
import org.apache.dubbo.rpc.cluster.loadbalance.ShortestResponseLoadBalance;

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
 * {@link DubboReference#check}:关闭所修饰服务的启动检查,防止启动时报错
 * {@link DubboReference#init}:默认懒加载
 * {@link DubboReference#loadbalance}:消费者负载均衡策略
 * {@link DubboReference#url}:消费者直连生产者,不经过注册中心,多个地址用逗号隔开,一般用于测试
 * </pre>
 * 
 * 消费者负载均衡策略:
 * 
 * <pre>
 * {@link RandomLoadBalance}:默认,随机,按权重设置随机概率
 * {@link RoundRobinLoadBalance}:轮询,按权重设置轮询比例.会存在慢的提供者累计请求的问题
 * {@link LeastActiveLoadBalance}:最少活跃调用数,相同活跃数的随机,活跃数指调用前后计数差
 * {@link ConsistentHashLoadBalance}:一致性Hash,相同参数的请求总是发到同一个提供者
 * {@link ShortestResponseLoadBalance}:最快响应,根据响应最快且成功的地址增加权重
 * </pre>
 * 
 * {@link Method}:对指定方法进行特殊处理
 * 
 * <pre>
 * {@link Method#name}:对指定方法进行特殊处理
 * {@link Method#onreturn}:返回时调用的方法
 * {@link Method#onthrow}:抛异常时调用的方法
 * {@link Method#loadbalance}:方法的负载均衡策略,值同{@link DubboReference#loadbalance}
 * </pre>
 * 
 * @author 飞花梦影
 * @date 2022-03-08 14:00:06
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public class Test {

	@DubboReference(version = "0.0.1",
			methods = { @Method(name = "test", onreturn = "testHandler.onReturn", onthrow = "testHandler.onThrow") },
			url = "dubbo://localhost:33333")
	private ITest itest;

	public void test(String name) {
		// 隐式传参,从服务端存储才会有
		System.out.println(RpcContext.getServerContext().getAttachment("key"));
		System.out.println(itest);
		itest.test(name);
	}
}