package com.sekorm.serviceImpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sekorm.model.Result;
import com.sekorm.service.RealTimeExpress;
import com.sekorm.utils.HttpRequestUtil;
import com.sekorm.utils.JacksonUtil;
import com.sekorm.utils.MD5Util;

@Service
public class RealTimeExpressImpl implements RealTimeExpress{
	
	@Value("#{configProperties['url']}")
	private String url;
	@Value("#{configProperties['customer']}")
	private String customer;
	@Value("#{configProperties['key']}")
	private String key;
	
	public Result findByRealTime(String param) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		String sign = MD5Util.encode(param+key+customer);
		params.put("param",param);
		params.put("sign",sign);
		params.put("customer",customer);
		String resp = new HttpRequestUtil().postData(url, params, "utf-8").toString();
		Result re=JacksonUtil.fromJSON(resp, Result.class);
		return re;
	}

}
