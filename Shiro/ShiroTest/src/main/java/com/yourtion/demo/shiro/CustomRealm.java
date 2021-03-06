package com.yourtion.demo.shiro;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashedMap(16);

    {
        userMap.put("Yourtion", "588d4112a1e18fa9a39e7007103fcd7f");
        super.setName("customRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }

    /**
     * 模拟从数据库或者缓存加载权限数据
     */
    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    /**
     * 模拟从数据库或者缓存加载角色数据
     */
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "GYX");
        System.out.println(md5Hash);
    }

    /**
     * 模拟数据库查询凭证
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 从主体传入信息获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        // 2. 通过用户名到数据库获取凭证

        String password = getPasswordByUserName(userName);

        if (password == null) {
            return null;
        }

        SimpleAuthenticationInfo simpleAuthorizationInfo = new SimpleAuthenticationInfo(userName, password, "customRealm");
        simpleAuthorizationInfo.setCredentialsSalt(ByteSource.Util.bytes("GYX"));
        return simpleAuthorizationInfo;
    }
}
