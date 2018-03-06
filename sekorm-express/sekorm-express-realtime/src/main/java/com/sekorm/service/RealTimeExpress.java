package com.sekorm.service;

import com.sekorm.model.Result;

public interface RealTimeExpress {
	
	public abstract Result findByRealTime(String param) throws Exception;
}
