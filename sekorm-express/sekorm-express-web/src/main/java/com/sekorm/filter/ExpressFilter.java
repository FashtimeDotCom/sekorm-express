package com.sekorm.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class ExpressFilter extends OncePerRequestFilter {

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
			String khm=(String) request.getParameter("khm");
			String kddh=(String) request.getParameter("kddh");
			String kdgs=(String) request.getParameter("kdgs");
			String jhh=(String) request.getParameter("jhh");
			String date=(String) request.getParameter("date");
			String wlzt=(String) request.getParameter("wlzt");
			HashMap<String,String> h=new HashMap<>();
				h.put("date",(!"".equals(date) && date!=null)?date:null);
				h.put("khm",(!"".equals(khm) && khm!=null)?(new String(khm.getBytes("ISO-8859-1"), "UTF-8")):null);
				h.put("kddh",(!"".equals(kddh) && kddh!=null)?kddh:null);
				h.put("kdgs",(!"".equals(kdgs) && kdgs!=null)?("0".equals(kdgs)?null:kdgs):null);
				h.put("jhh",(!"".equals(jhh) && jhh!=null)?jhh:null);
				h.put("wlzt",(!"".equals(wlzt) && wlzt!=null)?("111".equals(wlzt)?null:wlzt):null);
			request.setAttribute("param",h); 
			filterChain.doFilter(request,response);
	}

}
