package com.sekorm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.ApiService;
import com.sekorm.utils.JacksonUtil;

@RestController
@RequestMapping(value="/sekorm/expressApi")
public class ApiController {
	
	@Autowired(required=true)
	private ApiService apiService;
	
	@RequestMapping(value="/realTimeInterface",method=RequestMethod.POST)
	public Result realTimeInterface(){
		String param ="{\"com\":\"shunfeng\",\"num\":\"426520821902\"}";
		return apiService.find(param);
	}
	
	@RequestMapping(value="/pushInterface",method=RequestMethod.POST)
	public TaskResponse pushInterface(String param){
		TaskRequest tr=JacksonUtil.fromJSON(param,TaskRequest.class);
		return apiService.post(tr);
	}
}
