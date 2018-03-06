package com.sekorm.controller;

import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sekorm.entity.User;
import com.sekorm.ldap.LdapAuthentication;
import com.sekorm.service.UserService;

@RestController
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private LdapAuthentication ldapAuthentication;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String login(User user) throws Exception {
		String result = "success";
		Subject subject = SecurityUtils.getSubject();
		if (!ADLogin(user.getUsername(), user.getPassword())) {
			result = "error";
		}
		return result;
	}

	@RequestMapping(value = "/out", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String out() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "success";
	}

	@SuppressWarnings("unused")
	private boolean ADLogin(String email, String password) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		boolean result = ldapAuthentication.isLogin(email, password);
		if (result) {
			User u=userService.findByUsername(email);
			if(u==null){
				User user = new User(email, password);
				user.setId(String.valueOf(UUID.randomUUID()));
				user.setLocked("0");
				try {
					userService.createUser(user);
				} catch (Exception e) {
					logger.error("ADLogin 出错");
				}
			}else{
				UsernamePasswordToken token = new UsernamePasswordToken(email,
						password);
				try{
					subject.login(token);
				}catch(Exception e){
					userService.changePassword(u.getId(), password);
					subject.login(token);
				}
				
			}
		}
		return result;
	}
}
