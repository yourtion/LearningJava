package com.yourtion.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {

    @Test
    public void testIniRealm() {

        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        // 1. 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        // 2.主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken token = new UsernamePasswordToken("Yourtion", "123456");

        // 3. 登录
        subject.login(token);
        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        // 检查用户角色
        subject.checkRole("admin");

        // 检查用户权限
        subject.checkPermission("user:delete");

    }
}
