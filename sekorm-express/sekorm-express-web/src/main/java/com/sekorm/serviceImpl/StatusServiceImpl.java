package com.sekorm.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.PushCallBackDao;
import com.sekorm.model.Result;
import com.sekorm.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService{
	
	@Autowired
	private PushCallBackDao pushCallBackDao;
	
	@Override
	public List<HashMap<String,String>> findByUUID(String uuid) throws Exception{
		return pushCallBackDao.findByOne(uuid);
	}

	@Override
	public int updateByOne(HashMap<String, String> hm) throws Exception {
		pushCallBackDao.updateResultStatus(hm);
		pushCallBackDao.updateByOne(hm);
		return 2;
	}

	@Override
	public void insertLog(HashMap<String, String> hm) throws Exception {
		pushCallBackDao.insertLog(hm);
	}

	@Override
	public List<HashMap<String, String>> findLog(HashMap<String, String> hm)
			throws Exception {
		return pushCallBackDao.findLog(hm);
	}

	@Override
	public int findLogCount(HashMap<String, String> hm) throws Exception {
		return pushCallBackDao.findLogCount(hm);
	}

}
