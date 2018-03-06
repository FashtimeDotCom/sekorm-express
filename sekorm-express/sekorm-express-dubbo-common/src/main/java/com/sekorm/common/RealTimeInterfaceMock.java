package com.sekorm.common;

import com.sekorm.model.Result;

public class RealTimeInterfaceMock implements RealTimeInterface{

	@Override
	public Result find(String param) {
		Result re=new Result();
		re.setMessage("接口繁忙，请稍后再试！");
		re.setStatus("500");
		return re;
	}

}
