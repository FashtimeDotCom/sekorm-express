package com.sekorm.task;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sekorm.common.RealTimeInterface;
import com.sekorm.model.Result;
import com.sekorm.service.ExpressService;

@Component
public class TaskRealTime {
	private final static Logger logger=LoggerFactory.getLogger(TaskRealTime.class);
	
	@Autowired
	private ExpressService expressService;
	
	@Autowired
	private RealTimeInterface realTimeInterface;
	
	@Scheduled(cron = "0 */10 * * * ?")
	public void task(){
		List<HashMap<String,String>> list = null;
		try{
			list=expressService.findByError();
		}catch(Exception e){
			logger.error("查询异常");
		}
		if(list!=null && list.size()>0){
			for(HashMap<String,String>hm:list){
				String param="{\"com\":\""+hm.get("KD_COMPANY")+"\",\"num\":\""+hm.get("KD_NO")+"\"}";
				Result r=realTimeInterface.find(param);
				// 处理快递结果
				try {
					expressService.callBack(r);
				} catch (Exception e) {
					logger.error("定时查询结果保存异常");
				}
			}
		}
	}
}
