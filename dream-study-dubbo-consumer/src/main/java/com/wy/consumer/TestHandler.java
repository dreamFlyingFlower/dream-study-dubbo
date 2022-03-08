package com.wy.consumer;

import org.springframework.stereotype.Service;

/**
 * Dubbo方法调用时的回调,必须注入到Spring上下文或Dubbo上下文中
 *
 * @author 飞花梦影
 * @date 2022-03-08 15:01:16
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@Service
public class TestHandler {

	public void onReturn() {
	}

	public void onThrow() {
	}
}