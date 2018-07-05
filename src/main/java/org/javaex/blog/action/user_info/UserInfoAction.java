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
	 * ��ҳ��ת��¼ҳ��
	 */
	@RequestMapping("index.action")
	public String index() {
		return "admin/index";
	}
	@RequestMapping("login.action")
	public String login() {		
		return "admin/login";
	}
	
	//@ResponseBody ʹ��json��Ҫ��Ӵ�ע�� ��ʾֱ�ӷ���json����
	@RequestMapping("login.json")
	@ResponseBody
	public Result login2(ModelMap map,HttpServletRequest request) throws LinException {
         String username= request.getParameter("username");
         String password= request.getParameter("password");
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
		    throw new LinException("�ʺ�����Ϊ��");
		}
 		UserInfo user = userInfoService.checkUser(username, password);		
		if(user==null) throw new LinException("�ʺ��������");
	        
		
		request.getSession().setAttribute("user", user);
		return Result.success() ;
	}
	@RequestMapping("loginout.action")
	public String loginout(HttpSession session) {
		session.invalidate();//����session���� ������login.jsp��ͼ
		return "admin/login";
	}
}