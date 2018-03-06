package com.sekorm.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sekorm.api.PushApi;
import com.sekorm.dao.PushCallBackDao;
import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.PushService;

@Component
public class CallBackExceptionTask {

	private final static Logger logger = LoggerFactory.getLogger(CallBackExceptionTask.class);

	@Autowired
	private PushCallBackDao pushCallBackDao;
	@Autowired
	private PushService pushService;
	@Value("#{configProperties['url']}")
	private String url;
	@Value("#{configProperties['key']}")
	private String key;

	@Scheduled(cron = "0 15 10 ? * MON")
	public void exceptionTask() throws Exception {
		List<NoticeRequest> list = pushCallBackDao.findCallBackStatusAll();
		if (list.size() > 0) {
			for (NoticeRequest nr : list) {
				Result rr = pushCallBackDao.findByUUID(nr.getUuid());
				TaskRequest tr = new TaskRequest();
				    tr.setCompany(rr.getCom());
					tr.setNumber(rr.getNu());
					tr.getParameters().put("callbackurl", url);
					tr.setKey(key);
				try {
					pushService.updatePostOrder(tr);
				} catch (Exception e) {
					logger.error("定时订阅接口出错", e);
					System.out.println(e);
				}
			}
		}
	}

}
