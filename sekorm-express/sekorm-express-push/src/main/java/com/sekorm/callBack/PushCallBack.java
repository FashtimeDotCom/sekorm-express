package com.sekorm.callBack;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sekorm.model.NoticeRequest;
import com.sekorm.model.NoticeResponse;
import com.sekorm.model.Result;
import com.sekorm.service.PushService;
import com.sekorm.utils.JacksonUtil;

@Controller
@RequestMapping("/callback")
public class PushCallBack extends HttpServlet {
	
	private final static Logger logger = LoggerFactory.getLogger(PushCallBack.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PushService pushService;
	
	@RequestMapping(value="/post",method=RequestMethod.POST)
	public void PostHttpServlet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		NoticeResponse resp = new NoticeResponse();
		resp.setResult(false);
		resp.setReturnCode("500");
		resp.setMessage("保存失败");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String param = request.getParameter("param");
			NoticeRequest nReq = JacksonUtil.fromJSON(param, NoticeRequest.class);
			Result result = nReq.getLastResult();
			// 处理快递结果
			pushService.callBack(result,nReq);
			resp.setMessage("保存成功");
			resp.setResult(true);
			resp.setReturnCode("200");
			response.getWriter().print(JacksonUtil.toJSON(resp)); // 这里必须返回，否则认为失败，过30分钟又会重复推送。
		} catch (Exception e) {
			resp.setMessage("保存失败");
			response.getWriter().print(JacksonUtil.toJSON(resp));// 保存失败，服务端等30分钟会重复推送。
			logger.error("回调异常");
		}
	}
	
}
