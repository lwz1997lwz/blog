package org.javaex.blog.dao.type_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.TypeInfo;

public interface ITypeInfoDAO {

	/**
	 * ���ڲ�ѯ���·���
	 */
	List <TypeInfo> list();

	void insert(@Param("name") String name, @Param("sort") String sort);
	//ͨ��Param��ǩ�������name��������Ϊname����xml�п���ͨ��#{name}ȡֵ

	void update(@Param("id") String id, @Param("name") String name,
			  	@Param("sort") String sort);

	void delete(@Param("idArr") String[] idArr);

	TypeInfo selectByTypeId(@Param("typeId") String typeId);
}