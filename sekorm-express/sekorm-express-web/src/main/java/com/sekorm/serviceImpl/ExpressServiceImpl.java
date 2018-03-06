package com.sekorm.serviceImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sekorm.dao.PushCallBackDao;
import com.sekorm.model.NoticeRequest;
import com.sekorm.model.Result;
import com.sekorm.model.ResultItem;
import com.sekorm.service.ExpressService;

@Service
public class ExpressServiceImpl implements ExpressService{
	
	@Autowired
	private PushCallBackDao pushCallBackDao;
	
	@Override
	public Result findIteam(String uuid) throws Exception {
		return pushCallBackDao.findByUUID(uuid);
	}

	@Override
	public List<HashMap<String, String>> findDeliveryInfo(HashMap<String, String> hm) throws Exception {
		return pushCallBackDao.findDeliveryInfo(hm);
	}

	@Override
	public Integer findDeliveryInfoTotle(HashMap<String, String> hm) throws Exception {
		return pushCallBackDao.findDeliveryInfoTotle(hm);
	}
	
	@Override
	public void callBack(Result result) throws Exception {
		List<String> list = pushCallBackDao.findByState(result);
		if(list.size()>0){
			for (String re : list) {
				pushCallBackDao.deleteResultItem(re);
					result.setUuid(re);
				pushCallBackDao.updateResult(result);
				for (ResultItem r : result.getData()) {
					r.setUuid(re);
					pushCallBackDao.insertResultItem(r);
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
			}
		}
	}

	@Override
	public List<HashMap<String, String>> findByError() throws Exception {
		return pushCallBackDao.findByError();
	}

	@Override
	public List<HashMap<String, String>> findInvoiceInfo(
			HashMap<String, String> hm) throws Exception {
		return pushCallBackDao.findInvoiceInfo(hm);
	}

	@Override
	public Integer findInvoiceInfoTotle(HashMap<String, String> hm)
			throws Exception {
		return pushCallBackDao.findInvoiceInfoTotle(hm);
	}
	
	
}
