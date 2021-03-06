package com.wy.zookeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

public class ServiceConsumer {

	private List<String> serverList = new ArrayList<String>();

	private String serviceName = "service-A";

	// 初始化服务地址信息
	public void init() {
		String zkServerList = "192.168.0.250:2181";
		String SERVICE_PATH = "/configcenter/" + serviceName;// 服务节点路径
		ZkClient zkClient = new ZkClient(zkServerList);

		boolean serviceExists = zkClient.exists(SERVICE_PATH);
		if (serviceExists) {
			serverList = zkClient.getChildren(SERVICE_PATH);
		} else {
			throw new RuntimeException("service not exist!");
		}

		// 注册事件监听
		zkClient.subscribeChildChanges(SERVICE_PATH, new IZkChildListener() {

			// @Override
			public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
				System.out.println(parentPath + " 变化了");
				serverList = currentChilds;
			}
		});
	}

	// 消费服务
	public void consume() {
		// 通过负责均衡算法,得到一台服务器进行调用
		if (serverList.size() > 0) {
			int index = new Random().nextInt(serverList.size());
			System.out.println("调用" + serverList.get(index) + "提供的服务：" + serviceName);
		} else {
			System.out.println("没有可用服务");
		}
	}

	public static void main(String[] args) throws Exception {
		ServiceConsumer consumer = new ServiceConsumer();

		consumer.init();
		while (true) {
			consumer.consume();
			Thread.sleep(1000);
		}
	}
}