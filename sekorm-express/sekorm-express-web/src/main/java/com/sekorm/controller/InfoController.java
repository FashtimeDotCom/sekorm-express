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
import com.sekorm.service.InfoService;
import com.sekorm.service.UserService;

@RestController
public class InfoController {
	private static final Logger logger=LoggerFactory.getLogger(InfoController.class);
	
	@Autowired
	private InfoService infoService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getName",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String getName(){
		Subject subject = SecurityUtils.getSubject();
		String name=(String) subject.getPrincipal();
		return name;
	}
	
	@RequestMapping(value = "/getRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String getRole(){
		Subject subject = SecurityUtils.getSubject();
		String result="success";
		try{
			subject.checkRole("admin");
		}catch(Exception e){
			result="error";
		}
		return result;
	}
	
	@RequestMapping(value = "/updatePassWord",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String updatePassword(String newPassWord){
		Subject subject = SecurityUtils.getSubject();
		String name=(String) subject.getPrincipal();
		String result="success";
		int i=0;
		try {
			i=infoService.updatePassWord(name, newPassWord);
		} catch (Exception e) {
			result="error";
			logger.error("InfoController 更新错误");
		}
		if(i<=0)
			result="error";
		return result;
	}
	
	@RequestMapping(value = "/createUser",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String createUser(String username,String password){
		String result="success";
		User user= new User(username, password);
			 user.setId(String.valueOf(UUID.randomUUID()));
			 user.setLocked("0");
	    try{
		     userService.createUser(user);
	     }catch (Exception e){
	    	 result="error";
	    	 logger.error("InfoController 注册错误");
	     }
		 return result;
	}
	
}
