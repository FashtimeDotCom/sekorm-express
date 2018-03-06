package com.sekorm.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sekorm.dao.PushCallBackDao;
import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.model.ResultItem;
import com.sekorm.model.TaskRequest;
import com.sekorm.model.TaskResponse;
import com.sekorm.service.PushService;
import com.sekorm.utils.HttpRequestUtil;
import com.sekorm.utils.JacksonUtil;
import com.sekorm.utils.UUIDUtil;

@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private PushCallBackDao pushCallBackDao;
	@Value("#{configProperties['kuaidiurl']}")
	private String kuaidiurl;

	@Override
	public TaskResponse postOrder(TaskRequest tr) throws Exception {
		HashMap<String, String> p = new HashMap<String, String>();
			p.put("schema", "json");
			p.put("param", JacksonUtil.toJSON(tr));
		String ret = HttpRequestUtil.postData(kuaidiurl, p, "UTF-8");
		TaskResponse taskResponse = JacksonUtil.fromJSON(ret, TaskResponse.class);
		String uuid = UUIDUtil.getUUID();
		HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("uuid", uuid);
			hm.put("com", tr.getCompany());
			hm.put("nu", tr.getNumber());
			hm.put("code", taskResponse.getReturnCode());
			hm.put("message", taskResponse.getMessage());
			hm.put("result", String.valueOf(taskResponse.getResult()));
		pushCallBackDao.insertPushHistory(hm);
		NoticeRequest nr = new NoticeRequest();
			nr.setUuid(uuid);
			nr.setExceptionStatus("200".equals(taskResponse.getReturnCode()) ? "false" : "true");
			nr.setMessage(taskResponse.getMessage());
			nr.setStatus("200".equals(taskResponse.getReturnCode()) ? "polling" : "abort");
		pushCallBackDao.insertNoticeRequest(nr);
		Result r=new Result();
			r.setUuid(uuid);
			r.setCom(tr.getCompany());
			r.setNu(tr.getNumber());
		pushCallBackDao.insertResult(r);
		return taskResponse;
	}

	@Override
	public void callBack(Result result, NoticeRequest nr) throws Exception {
		if ("1".equals(nr.getAutoCheck())) {
			result.setCom(nr.getComNew());
		}
		List<String> list = pushCallBackDao.findByState(result);
		if(list.size()>0){
			for (String re : list) {
				pushCallBackDao.deleteResultItem(re);
				result.setUuid(re);
				nr.setUuid(re);
				pushCallBackDao.updateResult(result);
				if ("abort".equals(nr.getStatus())
						&& (nr.getMessage().split("3天").length > 1 || nr.getMessage().split("60天").length > 1)
						&& "".equals(nr.getComNew())) {
					nr.setExceptionStatus("true");
				} else {
					nr.setExceptionStatus("false");
					for (ResultItem r : result.getData()) {
						r.setUuid(re);
						pushCallBackDao.insertResultItem(r);
					}
				}
				Result res=pushCallBackDao.findByUUID(re);
				String statu=result.getIscheck();
				if("1".equals(statu)) {
					String date=result.getData().get(0).getFtime();
					HashMap<String, String> hm = new HashMap<String, String>();
						hm.put("status","已签收");
						hm.put("nu",res.getNu());
						hm.put("return_date",date);
					pushCallBackDao.updatePush(hm);
				}
				pushCallBackDao.updateNoticeRequest(nr);
			}
		}
	}
	
	@Override
	public TaskResponse updatePostOrder(TaskRequest tr) throws Exception {
		HashMap<String, String> p = new HashMap<String, String>();
			p.put("schema", "json");
			p.put("param", JacksonUtil.toJSON(tr));
		String ret = HttpRequestUtil.postData(kuaidiurl, p, "UTF-8");
		TaskResponse taskResponse = JacksonUtil.fromJSON(ret, TaskResponse.class);
		HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("status","已订阅");
			hm.put("nu", tr.getNumber());
		pushCallBackDao.updatePush(hm);
		return taskResponse;
	}

}
