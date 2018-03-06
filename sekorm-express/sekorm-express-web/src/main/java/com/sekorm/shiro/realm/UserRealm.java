package com.sekorm.shiro.realm;


import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sekorm.entity.User;
import com.sekorm.ldap.LdapAuthentication;
import com.sekorm.service.UserService;

/**
 * 账号验证域
 */
@Component
public class UserRealm extends AuthorizingRealm {
	private final static Logger logger=LoggerFactory.getLogger(UserRealm.class); 
	
	@Autowired
    private UserService userService;
	
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
			authorizationInfo.setRoles(userService.findRoles(username));
			authorizationInfo.setStringPermissions(userService.findPermissions(username));
        } catch (Exception e) {
			e.printStackTrace();
		}
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	String username = (String)token.getPrincipal();
        User user=null;
		try {
			user = userService.findByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
         if(user == null) {
             throw new UnknownAccountException();//没找到帐号
         }

         if(Boolean.TRUE.equals(user.getLocked())) {
             throw new LockedAccountException(); //帐号锁定
         }
         //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
         SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                 user.getUsername(), //用户名
                 user.getPassword(), //密码
                 ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                 getName()  //realm name
         );
         return authenticationInfo;
    }
    
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
