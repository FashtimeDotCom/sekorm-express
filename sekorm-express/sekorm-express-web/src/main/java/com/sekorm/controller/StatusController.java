package com.sekorm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sekorm.model.Result;
import com.sekorm.service.StatusService;
import com.sekorm.util.JsonUtil;
import com.sekorm.util.PageUtil;

@RestController
@RequestMapping(value = "/find")
public class StatusController {
	private final static Logger logger=LoggerFactory.getLogger(StatusController.class); 
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value = "/one",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String findByUUID(String uuid){
		ObjectMapper mapper = new ObjectMapper(); //转换器  
		String json="error";
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.checkRole("edit");
		}catch(Exception e){
			return json;
		}
		List<HashMap<String, String>> list = null;
		try{
			list=statusService.findByUUID(uuid);
		}catch(Exception e){
			logger.error("StatusController 查询出错");
			e.printStackTrace();
		}
		if(list!=null && !list.isEmpty()){
			try {
				json=mapper.writeValueAsString(list.get(0));
			} catch (JsonProcessingException e) {
				logger.error("StatusController 转换异常");
			}
		}
		return json;
	}
	
	@RequestMapping(value = "/updateByOne",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public String updateByOne(String kddh,String zt,String qsrq,String uuid,
			String old_date,String old_status){
		Subject subject = SecurityUtils.getSubject();
		HashMap<String,String> hm=new HashMap<String, String>();
		int i=0;
		String result="success";
		if(kddh!=null && zt!=null && qsrq!=null && uuid!=null){
			hm.put("no",kddh);
			hm.put("status",zt);
			hm.put("date", qsrq);
			hm.put("uuid", uuid);
			hm.put("old_date", "".equals(old_date)?null:old_date);
			hm.put("old_status", old_status);
			hm.put("name",String.valueOf(subject.getPrincipal()));
		}
		try{
			i=statusService.updateByOne(hm);
			statusService.insertLog(hm);
		}catch(Exception e){
			logger.error("StatusController 更新异常");
			e.printStackTrace();
		}
		if(i<2)
			result="error";
		return result;
	}
	
	@RequestMapping(value = "/Log",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String findLog(HttpServletRequest request,PageUtil page,
			String no,String name,String date) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String,String> param=(HashMap<String, String>) request.getAttribute("param");
		param.put("start",String.valueOf(page.getiDisplayStart()+1));
		param.put("end",String.valueOf(page.getiDisplayStart()+page.getiDisplayLength()));
		param.put("no","".equals(no)?null:no);
		param.put("name","".equals(name)?null:name);
		param.put("date","".equals(date)?null:date);
	    List<HashMap<String,String>> list=statusService.findLog(param);
	    page.setTotle(statusService.findLogCount(param));
		return  JsonUtil.logVoToString(page,list);
	}
}
