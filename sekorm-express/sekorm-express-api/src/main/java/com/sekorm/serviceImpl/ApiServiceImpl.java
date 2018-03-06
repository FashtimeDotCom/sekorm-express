package com.sekorm.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.common.PushInterface;
import com.sekorm.common.RealTimeInterface;
import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService{
	
	private final static Logger logger=LoggerFactory.getLogger(ApiServiceImpl.class);
	
	@Autowired
	private RealTimeInterface realTimeInterface;
	@Autowired
	private PushInterface pushInterface;
	
	@Override
	public Result find(String param) {
		Result re=null;
		re=realTimeInterface.find(param);
		return re;
	}

	@Override
	public TaskResponse post(TaskRequest tr) {
		TaskResponse st=null;
		st=pushInterface.post(tr);
		return st;
	}

}
