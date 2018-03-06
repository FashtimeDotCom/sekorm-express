package com.sekorm.task;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sekorm.dao.PushCallBackDao;
import com.sekorm.model.Result;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.PushService;

@Component
public class PostForDataBaseTask {
	private final static Logger logger = LoggerFactory.getLogger(PostForDataBaseTask.class);

	@Autowired
	private PushCallBackDao pushCallBackDao;
	@Autowired
	private PushService pushService;
	@Value("#{configProperties['url']}")
	private String url;
	@Value("#{configProperties['key']}")
	private String key;

	@Scheduled(cron = "0 */5 * * * ?")
	public void task() throws Exception {
		List<HashMap<String, String>> list = pushCallBackDao.findPushAll();
		if (list.size()>0) {
			for (HashMap<String, String> hm : list) {
				TaskRequest tr = new TaskRequest();
						tr.setCompany(hm.get("KD_COMPANY"));
						tr.setNumber(hm.get("KD_NO"));
				try {
					tr.getParameters().put("callbackurl", url);
					tr.setKey(key);
					TaskResponse taskResponse = pushService.postOrder(tr);
					HashMap<String, String> h =new HashMap<String, String>();
							h.put("status","已订阅");
							h.put("nu", tr.getNumber());
					pushCallBackDao.updatePush(h);
				} catch (Exception e) {
					logger.error("数据库订阅接口出错", e);
				}
			}
		}
	}

}
