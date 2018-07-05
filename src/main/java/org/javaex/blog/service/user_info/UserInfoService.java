package org.javaex.blog.service.user_info;

import org.javaex.blog.dao.user_info.IUserInfoDAO;
import org.javaex.blog.view.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserInfoService")
public class UserInfoService {
	@Autowired
	private IUserInfoDAO iUserInfoDAO;

	/**
	 * 校验用户登录
	 * @param loginName 登录名
	 * @param passWord 登录密码
	 * @return
	 */
	public UserInfo checkUser(String userName, String passWord) {
		
		return iUserInfoDAO.checkUser(userName, passWord);
	}

}