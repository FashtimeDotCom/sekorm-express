package com.sekorm.common;

import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;

public class PushInterfaceMock implements PushInterface{

	@Override
	public TaskResponse post(TaskRequest tr) {
		TaskResponse task=new TaskResponse();
		task.setMessage("订阅接口繁忙，请稍后再试");
		task.setResult(false);
		task.setReturnCode("500");
		return task;
	}
	
}
