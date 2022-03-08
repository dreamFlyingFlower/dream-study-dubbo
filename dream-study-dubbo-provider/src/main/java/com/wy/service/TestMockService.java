package com.wy.service;

import com.wy.interfaces.ITest;

/**
 * Dubbo服务降级
 * 
 * @author 飞花梦影
 * @date 2022-03-08 11:09:58
 * @git {@link https://github.com/dreamFlyingFlower }
 */
public class TestMockService implements ITest {

	@Override
	public String test(String name) {
		return "传过来的name是:" + name;
	}
}