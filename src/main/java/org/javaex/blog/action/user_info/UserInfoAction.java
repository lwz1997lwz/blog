package org.javaex.blog.action.user_info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.javaex.blog.LinException.LinException;
import org.javaex.blog.service.user_info.UserInfoService;
import org.javaex.blog.view.Result;
import org.javaex.blog.view.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class UserInfoAction {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 首页跳转登录页面
	 */
	@RequestMapping("index.action")
	public String index() {
		return "admin/index";
	}
	@RequestMapping("login.action")
	public String login() {		
		return "admin/login";
	}
	
	//@ResponseBody 使用json需要添加此注解 表示直接返回json对象
	@RequestMapping("login.json")
	@ResponseBody
	public Result login2(ModelMap map,HttpServletRequest request) throws LinException {
         String username= request.getParameter("username");
         String password= request.getParameter("password");
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
		    throw new LinException("帐号密码为空");
		}
 		UserInfo user = userInfoService.checkUser(username, password);		
		if(user==null) throw new LinException("帐号密码错误");
	        
		
		request.getSession().setAttribute("user", user);
		return Result.success() ;
	}
	@RequestMapping("loginout.action")
	public String loginout(HttpSession session) {
		session.invalidate();//销毁session对象 并返回login.jsp视图
		return "admin/login";
	}
}