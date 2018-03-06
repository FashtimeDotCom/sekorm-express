package com.sekorm.dao;

import java.util.HashMap;
import java.util.List;

import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.model.ResultItem;
import com.sekorm.model.TaskRequest;

public interface PushCallBackDao {
	
	public abstract Result findByUUID(String uuid) throws Exception;
	
	public abstract List<HashMap<String,String>> findByError() throws Exception;
	
	public abstract List<HashMap<String,Object>> findPostByNu(String nu) throws Exception;
	
	public abstract List<HashMap<String,String>> findByOne(String uuid) throws Exception;
	
	public abstract int insertLog(HashMap<String,String> hm) throws Exception;
	
	public abstract List<HashMap<String,String>> findDeliveryInfo(HashMap<String,String> hm) throws Exception;
	
	public abstract Integer findInvoiceInfoTotle(HashMap<String,String> hm) throws Exception;
	
	public abstract List<HashMap<String,String>> findInvoiceInfo(HashMap<String,String> hm) throws Exception;
	
	public abstract Integer findDeliveryInfoTotle(HashMap<String,String> hm) throws Exception;
	
	public abstract Result findByNu(String nu) throws Exception;
	
	public abstract Result findByAll() throws Exception;
	
	public abstract List<String> findByState(Result re) throws Exception;
	
	public abstract List<NoticeRequest> findCallBackStatusAll() throws Exception;
	
	public abstract List<HashMap<String,String>> findPushAll() throws Exception;
	
	public abstract int insertPushHistory(HashMap<String,String> hm)throws Exception;
	
	public abstract int insertResult(Result re) throws Exception;
	
	public abstract int updatePush(HashMap<String,String> hm) throws Exception;
	
	public abstract int updateByOne(HashMap<String,String> hm) throws Exception;
	
	public abstract int updateResultStatus(HashMap<String,String> hm) throws Exception;
	
	public abstract int insertResultItem(ResultItem reIt) throws Exception;
	
	public abstract int insertNoticeRequest(NoticeRequest nr) throws Exception;
	
	public abstract int insertTaskRequest(TaskRequest nr) throws Exception;
	
	public abstract int updateResult(Result re) throws Exception;
	
	public abstract int updateNoticeRequest(NoticeRequest nr) throws Exception;
	
	public abstract int deleteResult(String uuid) throws Exception;
	
	public abstract int deleteResultItem(String uuid) throws Exception;
	
	public abstract List<HashMap<String,String>> findLog(HashMap<String,String> hm) throws Exception;
	
	public abstract int findLogCount(HashMap<String,String> hm) throws Exception;
	
}
