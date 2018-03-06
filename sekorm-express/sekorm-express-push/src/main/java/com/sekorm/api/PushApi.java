package com.sekorm.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sekorm.common.PushInterface;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.PushService;

@Component
public class PushApi implements PushInterface {
	
	private final static Logger logger=LoggerFactory.getLogger(PushApi.class);
	
	@Autowired
	private PushService pushService;
	@Value("#{configProperties['url']}")
	private String url;
	@Value("#{configProperties['key']}")
	private String key;
	
	public TaskResponse post(TaskRequest tr) {
		TaskResponse task=null;
		try{
			tr.getParameters().put("callbackurl",url);
			tr.setKey(key);
			task=pushService.postOrder(tr);
		}catch(Exception e){
			task.setMessage("订阅接口错误");
			task.setResult(false);
			task.setReturnCode("500");
			logger.error("订阅接口出错",e);
		}
		return task;
	}

}
