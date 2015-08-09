/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package personal.deon.framework.service.account;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import personal.deon.framework.core.entity.CoreUser;
import personal.deon.framework.core.repository.CoreUserDao;
import personal.deon.framework.core.service.CoreUserService;
import personal.deon.framework.core.service.ServiceException;
import personal.deon.framework.core.service.ShiroDbRealm;
import personal.deon.framework.data.UserData;
import cn.gd.thinkjoy.modules.test.security.shiro.ShiroTestUtils;

import org.springframework.test.annotation.Rollback;

/**
 * AccountService的测试用例, 测试Service层的业务逻辑.
 * 
 * @author calvin
 */
public class AccountServiceTest {

	@InjectMocks
	private CoreUserService accountService;

	@Mock
	private CoreUserDao mockUserDao;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject(new  ShiroDbRealm.ShiroUser("", "foo", "Foo"));
	}


	@Test
	public void updateUser() {
		// 如果明文密码不为空，加密密码会被更新.
		CoreUser user = UserData.randomNewUser();
		accountService.save(user);
		assertThat(user.getSalt()).isNotNull();

		// 如果明文密码为空，加密密码无变化。
		CoreUser user2 = UserData.randomNewUser();
		user2.setPlainPwd(null);
		accountService.save(user2);
		assertThat(user2.getSalt()).isNull();
	}

	@Test
	public void deleteUser() {
		// 正常删除用户.
		accountService.delete("2");
//		Mockito.verify(mockUserDao).delete("2");

		// 删除超级管理用户抛出异常, userDao没有被执行
		try {
			accountService.delete("1L");
			failBecauseExceptionWasNotThrown(ServiceException.class);
		} catch (ServiceException e) {
			// expected exception
		}
	}

}
