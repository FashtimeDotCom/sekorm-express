package com.sekorm.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sekorm.common.RealTimeInterface;
import com.sekorm.model.Result;
import com.sekorm.service.RealTimeExpress;

public class RealTimeApi implements RealTimeInterface{
	
	private final static Logger logger=LoggerFactory.getLogger(RealTimeApi.class);
	
	@Autowired
	private RealTimeExpress realTimeExpress;
	
	public Result find(String param) {
		Result re=null;
		try{
			re=realTimeExpress.findByRealTime(param);
		}catch(Exception e){
			re.setMessage("实时接口错误");
			re.setStatus("500");
			logger.error("实时接口错误");
		}
		return re;
	}

}
