package com.sekorm.service;

import java.util.HashMap;
import java.util.List;

import com.sekorm.model.Result;

public interface StatusService {
	
	public abstract List<HashMap<String,String>> findByUUID(String uuid) throws Exception;
	
	public abstract List<HashMap<String,String>> findLog(HashMap<String,String> hm) throws Exception;
	
	public abstract int findLogCount(HashMap<String,String> hm) throws Exception;
	
	public abstract int updateByOne(HashMap<String,String> hm) throws Exception;
	
	public abstract void insertLog(HashMap<String,String> hm) throws Exception;
}
