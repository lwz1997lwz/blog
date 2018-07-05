package org.javaex.blog.dao.user_info;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.UserInfo;

public interface IUserInfoDAO {

	/**
	 * 校验用户
	 * @param loginName 登录名
	 * @param passWord 登录密码
	 * @return
	 */
	UserInfo checkUser(@Param("userName") String userName, @Param("passWord") String passWord);

}