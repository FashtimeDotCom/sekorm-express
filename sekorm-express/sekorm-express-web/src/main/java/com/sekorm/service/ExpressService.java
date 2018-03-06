package com.sekorm.service;

import java.util.HashMap;
import java.util.List;

import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;

public interface ExpressService {
	
	public abstract Result findIteam(String uuid) throws Exception;
	
	public abstract List<HashMap<String,String>> findDeliveryInfo(HashMap<String,String> hm) throws Exception;
	
	public abstract Integer findDeliveryInfoTotle(HashMap<String,String> hm) throws Exception;
	
	public abstract List<HashMap<String,String>> findInvoiceInfo(HashMap<String,String> hm) throws Exception;
	
	public abstract Integer findInvoiceInfoTotle(HashMap<String,String> hm) throws Exception;
	
	public abstract void callBack(Result result) throws Exception;
	
	public abstract List<HashMap<String,String>> findByError() throws Exception;
}
