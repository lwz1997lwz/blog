package org.javaex.blog.dao.user_info;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.UserInfo;

public interface IUserInfoDAO {

	/**
	 * У���û�
	 * @param loginName ��¼��
	 * @param passWord ��¼����
	 * @return
	 */
	UserInfo checkUser(@Param("userName") String userName, @Param("passWord") String passWord);

}