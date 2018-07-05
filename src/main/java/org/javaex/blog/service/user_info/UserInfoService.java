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
	 * У���û���¼
	 * @param loginName ��¼��
	 * @param passWord ��¼����
	 * @return
	 */
	public UserInfo checkUser(String userName, String passWord) {
		
		return iUserInfoDAO.checkUser(userName, passWord);
	}

}