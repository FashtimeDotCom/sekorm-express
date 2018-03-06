/*package com.sekorm.test;


import java.util.UUID;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.sekorm.dao.UserDao;
import com.sekorm.entity.Permission;
import com.sekorm.entity.Role;
import com.sekorm.entity.User;
import com.sekorm.ldap.LdapAuthentication;
import com.sekorm.service.PermissionService;
import com.sekorm.service.RoleService;
import com.sekorm.service.UserService;
import com.sekorm.shiro.realm.UserRealm;
import com.sekorm.util.PasswordHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:spring-shiro-web.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ShiroTest {
	
	@Autowired
	private LdapAuthentication ldapAuthentication;
	
    @Autowired
    protected PermissionService permissionService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRealm userRealm;
    @Autowired
	private PasswordHelper passwordHelper;
    protected String password = "123";

    protected Permission p1;
    protected Role r1;
    protected User u1;
    @Test
    public void t(){
    	System.out.println(ldapAuthentication.isLogin("NOAH_yang@sekorm.com","A2017#0925"));
    }
    
    
    //@Test
    public void tt() throws Exception{
    	User i=userDao.findByUsername("zhang");
    	System.out.println(i.getUsername());
    }
    
    
   @Test
    public void setUp() throws Exception {
        //1、新增权限
       p1 = new Permission("user:create", "用户模块新增","0");
        p1.setId(String.valueOf(UUID.randomUUID()));
        permissionService.createPermission(p1);
        //2、新增角色
        r1 = new Role("admin", "管理员", "0");
        r1.setId(String.valueOf(UUID.randomUUID()));
        roleService.createRole(r1);
        roleService.correlationPermissions(r1.getId(), p1.getId());
        //4、新增用户
        u1 = new User("admin", password);
        u1.setId(String.valueOf(UUID.randomUUID()));
        u1.setLocked("0");
        userService.createUser(u1);
        //5、关联用户-角色
        userService.correlationRoles("3e7a07b8-ea34-4d10-8646-b3551aa8249f","80887c01-01f1-4fc4-971b-c27bd26bf2ec");

    }

    
    
    //@Test
    public void test() throws Exception {
    	String resource = "spring-shiro-web.xml";  
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);  
        org.apache.shiro.mgt.SecurityManager securityManager =   
            (org.apache.shiro.mgt.SecurityManager)appCtx.getBean("securityManager");  
        SecurityUtils.setSecurityManager(securityManager);  
        Subject subject = SecurityUtils.getSubject();
        u1 = new User("zhang", password);
        //passwordHelper.encryptPassword(u1);
        u1.setId("2e57d467-bed4-46bb-94cf-580d1cf3c770");
        UsernamePasswordToken token = new UsernamePasswordToken(u1.getUsername(), u1.getPassword());
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
//        subject.checkRole("admin");
//        subject.checkPermission("user:create");

        userService.changePassword(u1.getId(), password + "1");
        userRealm.clearCache(subject.getPrincipals());
        
        User u2 = new User("zhang", password);
        //passwordHelper.encryptPassword(u2);
        UsernamePasswordToken token = new UsernamePasswordToken(u2.getUsername(),u2.getPassword());
        subject.login(token);
        
        subject.checkRole("admin");
        subject.checkPermission("user:create");




    }

}
*/