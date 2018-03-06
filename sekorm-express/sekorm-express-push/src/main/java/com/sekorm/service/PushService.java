package com.sekorm.service;

import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;

public interface PushService {

	public abstract TaskResponse postOrder(TaskRequest tr) throws Exception;
	
	public abstract TaskResponse updatePostOrder(TaskRequest tr) throws Exception;

	public abstract void callBack(Result result, NoticeRequest nr) throws Exception;

}
