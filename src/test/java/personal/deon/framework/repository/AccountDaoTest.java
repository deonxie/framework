/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package personal.deon.framework.repository;

import cn.gd.thinkjoy.modules.test.spring.SpringTransactionalTestCase;
import personal.deon.framework.core.entity.CoreRole;
import personal.deon.framework.core.entity.CoreUser;
import personal.deon.framework.core.service.CoreRoleServcie;
import personal.deon.framework.core.service.CoreUserService;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = { "/applicationContext.xml" })
public class AccountDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private CoreUserService userService;
    @Autowired
    private CoreRoleServcie roleService;

    @Test
    @Rollback(false)
    public void addRole() {
        CoreRole role = new CoreRole();
        role.setRoleName("管理员");
        role.setPermissions("role:view,role:edit,user:view,user:edit");

        roleService.save(role);

    }

    @Test
    @Rollback(false)
    public void registerUser(){

        List<CoreRole> roleList = roleService.getAll();

        List<String> roleIds = Lists.newArrayList();

        for(CoreRole role : roleList){
            roleIds.add(role.getId());
        }

        CoreUser user = new CoreUser();
//        user.setId(0L);
        user.setAccount("admin");
        user.setUserName("admin");
        user.setPlainPwd("123");
        System.out.println(userService.registerUser(user, roleIds));
    }
}
