package com.sekorm.service;

import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;

public interface ApiService {
	
	public abstract Result find(String param);
	
	public abstract TaskResponse post(TaskRequest tr);
}
