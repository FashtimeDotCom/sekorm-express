package com.sekorm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sekorm.common.RealTimeInterface;
import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.service.ExpressService;
import com.sekorm.util.JsonUtil;
import com.sekorm.util.PageUtil;
import com.sekorm.utils.JacksonUtil;

@RestController
@RequestMapping(value = "/find")
public class ExpressController {
	private final static Logger logger=LoggerFactory.getLogger(ExpressController.class); 
	
	@Autowired
	private ExpressService expressService;
	
	@Autowired
	private RealTimeInterface realTimeInterface;
	
	@RequestMapping(value = "/all",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String findAll(HttpServletRequest request,PageUtil page) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String,String> param=(HashMap<String, String>) request.getAttribute("param");
		param.put("start",String.valueOf(page.getiDisplayStart()+1));
		param.put("end",String.valueOf(page.getiDisplayStart()+page.getiDisplayLength()));
	    List<HashMap<String,String>> list=expressService.findDeliveryInfo(param);
	    page.setTotle(expressService.findDeliveryInfoTotle(param));
		return  JsonUtil.voToString(page,list);
	}
	
	@RequestMapping(value = "/allInvoice",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public String findInvoice(HttpServletRequest request,PageUtil page) throws Exception{
		@SuppressWarnings("unchecked")
		HashMap<String,String> param=(HashMap<String, String>) request.getAttribute("param");
		param.put("start",String.valueOf(page.getiDisplayStart()+1));
		param.put("end",String.valueOf(page.getiDisplayStart()+page.getiDisplayLength()));
	    List<HashMap<String,String>> list=expressService.findInvoiceInfo(param);
	    page.setTotle(expressService.findInvoiceInfoTotle(param));
		return  JsonUtil.voToString(page,list);
	}
	
	@RequestMapping(value = "/item",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public Result findItem(String uuid) throws Exception{
		Result r=expressService.findIteam(uuid);
		return r;
	}
	
	@RequestMapping(value = "/realTime",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public Result findRealTime(String kddh,String com) throws Exception {
		String param="{\"com\":\""+com+"\",\"num\":\""+kddh+"\"}";
		Result r=realTimeInterface.find(param);
		// 处理快递结果
		try {
			expressService.callBack(r);
		} catch (Exception e) {
			logger.error("实时查询结果保存异常");
			throw new Exception();
		}
		return r;
	}
	
}
