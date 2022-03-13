package com.wy.service;

import org.apache.dubbo.common.threadpool.ThreadPool;
import org.apache.dubbo.common.threadpool.support.cached.CachedThreadPool;
import org.apache.dubbo.common.threadpool.support.eager.EagerThreadPool;
import org.apache.dubbo.common.threadpool.support.fixed.FixedThreadPool;
import org.apache.dubbo.common.threadpool.support.limited.LimitedThreadPool;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.support.AvailableCluster;
import org.apache.dubbo.rpc.cluster.support.BroadcastCluster;
import org.apache.dubbo.rpc.cluster.support.FailbackCluster;
import org.apache.dubbo.rpc.cluster.support.FailfastCluster;
import org.apache.dubbo.rpc.cluster.support.FailoverCluster;
import org.apache.dubbo.rpc.cluster.support.FailsafeCluster;
import org.apache.dubbo.rpc.cluster.support.ForkingCluster;
import org.apache.dubbo.rpc.cluster.support.MergeableCluster;
import org.apache.dubbo.remoting.Dispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.all.AllDispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.connection.ConnectionOrderedDispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.direct.DirectDispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.execution.ExecutionDispatcher;
import org.apache.dubbo.remoting.transport.dispatcher.message.MessageOnlyDispatcher;

import com.wy.interfaces.ITest;

/**
 * dubbo接口的实现类
 * 
 * {@link DubboService}:表明该实现类将暴露给Dubbo服务,作用同spring-dubboProvider.xml中的<dubbo:service>标签
 * 
 * <pre>
 * {@link DubboService#register()}:是否注册到注册中心.即是否只订阅服务,而不注册到注册中心,一般用于直连测试
 * {@link DubboService#registry}:多个注册中心时使用,向指定注册中心注册,值为registry.id,多个注册中心ID用逗号分隔,如果不想注册到任何registry,可将值设为N/A
 * {@link DubboService#stub}:本地存根,类似于一个策略类,主要做调用前的相关检测等,最终调用的是存根类
 * {@link DubboService#version}:调用指定版本.生产者和消费者都要指定
 * {@link DubboService#protocol}:指定调用者的协议,可指定多种,值为配置文件中protocol的name值
 * {@link DubboService#cache}:缓存策略
 * {@link DubboService#group}:服务分组,该名称和配置文件中registry的group属性相同
 * {@link DubboService#mock}:服务降级.降级的类也需要实现被降级类同样的接口,需要填写降级类的全名
 * {@link DubboService#loadbalance}:服务提供者负载均衡策略
 * {@link DubboService#cluster}:服务提供者集群策略
 * </pre>
 * 
 * {@link Dispatcher}:事件处理类型,默认all:
 * 
 * <pre>
 * {@link AllDispatcher}:所有消息都派发到线程池,包括请求,响应,连接事件,断开事件,心跳等
 * {@link DirectDispatcher}:所有消息都不派发到线程池,全部在IO线程上直接执行
 * {@link MessageOnlyDispatcher}:只有请求响应消息派发到线程池,其他连接断开事件,心跳等消息,直接在IO线程上执行
 * {@link ExecutionDispatcher}:只有请求消息派发到线程池,不含响应,响应和其他连接断开事件,心跳等消息,直接在IO线程上执行
 * {@link ConnectionOrderedDispatcher}:在IO线程上,将连接断开事件放入队列,有序逐个执行,其他消息派发到线程池
 * </pre>
 * 
 * {@link ThreadPool}:线程池类型,默认fixed:
 * 
 * <pre>
 * {@link FixedThreadPool}:固定大小线程池,启动时建立线程,不关闭,一直持有
 * {@link CachedThreadPool}:缓存线程池,空闲一分钟自动删除,需要时重建
 * {@link LimitedThreadPool}:可伸缩线程池,但池中的线程数只会增长不会减少(为避免收缩时来了大量请求导致程序崩溃)
 * {@link EagerThreadPool}:类似于{@link LimitedThreadPool},有新请求时会创建新的线程,而不会放到队列,宕机就消息
 * </pre>
 * 
 * 服务提供方集群策略(cluster),默认为failover:
 * 
 * <pre>
 * {@link FailoverCluster}:失败自动切换,重试其它服务器.通常用于读操作,可通过retries=2来设置重试次数,(不含第一次)
 * {@link FailfastCluster}:快速失败,只发起一次调用,失败立即报错.通常用于非幂等性的写操作,比如新增记录
 * {@link FailsafeCluster}:失败安全,出现异常时,直接忽略.通常用于写入审计日志等操作
 * {@link FailbackCluster}:失败自动恢复,后台记录失败请求,定时重发.通常用于消息通知操作
 * {@link ForkingCluster}:并行调用多个服务器,只要一个成功即返回.通常用于实时性要求较高的读操作,可通过forks设置最大并行数
 * {@link BroadcastCluster}:广播调用所有提供者,逐个调用,任意一台报错则报错.通常用于更新提供方本地状态
 * {@link AvailableCluster}:
 * {@link MergeableCluster}:
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