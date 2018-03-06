package com.sekorm.interceptor;  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  
/**
 * 权限拦截器
 * 非授权令牌的请求无法访问接口
 * @author noah_yang
 * @version 1.0
 */
public class BaseInterceptor implements HandlerInterceptor{  
      
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {  
        System.out.println("************BaseInterceptor preHandle executed**********");  
        return true;  
    }  
    
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)  
            throws Exception {  
        System.out.println("************BaseInterceptor postHandle executed**********");  
    }  
      
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)  
            throws Exception {  
        System.out.println("************BaseInterceptor afterCompletion executed**********");  
    }  
  
}  