package com.yourtion.test;

import com.yourtion.domain.Admin;
import com.yourtion.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * SpringMybatisTest
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 * Created by Yourtion on 26/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring 配置文件
@ContextConfiguration({ "classpath:spring-mybatis.xml" })
public class SpringMybatisTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void testLogin() {
        Admin admin = new Admin();
        admin.setUsername("superadmin");
        admin.setPassword("123456");
        System.out.println(adminService.login(admin).toString());
    }
}
