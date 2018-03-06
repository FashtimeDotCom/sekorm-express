package com.sekorm.common;

import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;

public interface PushInterface {
	
	public abstract TaskResponse post(TaskRequest tr);
	
}
