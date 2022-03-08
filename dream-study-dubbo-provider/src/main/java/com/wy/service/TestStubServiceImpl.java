package com.wy.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.StringUtils;

import com.wy.interfaces.ITestStub;

/**
 * 本地存根.类似于策略模式,主要功能是为了在方法进行调用之前进行额外处理,如参数检测等,实际上调用的存根类
 *
 * @author 飞花梦影
 * @date 2022-03-08 13:58:26
 * @git {@link https://github.com/dreamFlyingFlower }
 */
@DubboService
public class TestStubServiceImpl implements ITestStub {

	private ITestStub testStub;

	public TestStubServiceImpl(ITestStub testStub) {
		this.testStub = testStub;
	}

	@Override
	public String testStub(String name) {
		if (StringUtils.hasText(name)) {
			testStub.testStub(name);
		}
		return null;
	}
}