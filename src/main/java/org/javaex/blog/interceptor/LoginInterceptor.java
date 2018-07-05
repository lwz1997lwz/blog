package org.javaex.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.javaex.blog.view.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//获取请求地址，若为login则直接放行
		String url = request.getRequestURI();
		if(url.indexOf("login")>0||url.indexOf("portal")>0)
			return true;
		//若登录验证通过则方向
	   UserInfo user= (UserInfo) request.getSession().getAttribute("user");
	   if(user!=null)
		   return true;
	   request.getRequestDispatcher("/WEB-INF/page/admin/login.jsp").forward(request, response);  
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


	

}
