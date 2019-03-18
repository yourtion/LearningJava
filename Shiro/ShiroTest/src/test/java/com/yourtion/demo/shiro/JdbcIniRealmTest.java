package com.yourtion.demo.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * -- Create syntax for TABLE 'roles_permissions'
 * CREATE TABLE `roles_permissions` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `role_name` varchar(255) DEFAULT NULL,
 * `permission` varchar(255) NOT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
 * <p>
 * -- Create syntax for TABLE 'user_roles'
 * CREATE TABLE `user_roles` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `username` varchar(255) DEFAULT NULL,
 * `role_name` varchar(255) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
 * <p>
 * -- Create syntax for TABLE 'users'
 * CREATE TABLE `users` (
 * `id` int(11) NOT NULL AUTO_INCREMENT,
 * `username` varchar(255) DEFAULT NULL,
 * `password` varchar(255) DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 * <p>
 * INSERT INTO `roles_permissions` VALUES (1,'admin','user:select'),(2,'admin','user:delete'),(3,'user','user:select');
 * INSERT INTO `user_roles` VALUES (1,'Yourtion','admin'),(2,'Yourtion','user');
 * INSERT INTO `users` VALUES (1,'Yourtion','123456');
 */

public class JdbcIniRealmTest {

    private DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    @Test
    public void testJdbcRealm() {

        JdbcRealm jdbcRealm = new JdbcRealm();

        jdbcRealm.setDataSource(dataSource);

        // 1. 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        // 需要手动开启权限查询
        jdbcRealm.setPermissionsLookupEnabled(true);

        // 2.主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Yourtion", "123456");

        // 3. 登录
        subject.login(token);
        System.out.println("isAuthenticated: " + subject.isAuthenticated());

        // 检查用户角色
        subject.checkRoles("admin", "user");

        // 检查用户权限
        subject.checkPermission("user:delete");

    }
}
