package com.sekorm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sekorm.service.PermissionService;
import com.sekorm.util.JsonUtil;
import com.sekorm.util.PageUtil;

@RestController
@RequestMapping(value = "/find")
public class PermissionsController {
	private final static Logger logger=LoggerFactory.getLogger(PermissionsController.class); 
	public final ObjectMapper mapper = new ObjectMapper(); 
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value = "/Role",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String getRole(){
		Subject subject = SecurityUtils.getSubject();
		String name=(String) subject.getPrincipal();
		String result=null;
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		try {
			list=permissionService.findInfo(name);
		} catch (Exception e) {
			result="error";
			logger.error("PermissionsController查询出错");
		}
		try {
			if(list.size()>0)
			result=mapper.writeValueAsString(list);
		} catch (IOException e) {
			logger.error("转换异常",e);
		}
		return result;
	}
	
	@RequestMapping(value = "/Info",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String getUsersInfo(HttpServletRequest request,PageUtil page,String username) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String,String> param=(HashMap<String, String>) request.getAttribute("param");
		param.put("start",String.valueOf(page.getiDisplayStart()+1));
		param.put("end",String.valueOf(page.getiDisplayStart()+page.getiDisplayLength()));
		param.put("username",username);
	    List<HashMap<String,String>> list=permissionService.getUsersInfo(param);
	    page.setTotle(permissionService.getUsersInfoCount(param));
		return  JsonUtil.usersVoToString(page,list);
	}
	@RequiresRoles("admin")
	@RequestMapping(value = "/updateRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String updateRole(String id){
		String result="success";
		try {
			permissionService.updateRole(id);
		} catch (Exception e) {
			result="error";
			logger.error("注册权限异常",e);
		}
		return result;
	}
	@RequiresRoles("admin")
	@RequestMapping(value = "/deleteRole",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String deleteRole(String id){
		String result="success";
		try {
			permissionService.deleteRole(id);
		} catch (Exception e) {
			result="error";
			logger.error("删除权限异常",e);
		}
		return result;
	}
}
