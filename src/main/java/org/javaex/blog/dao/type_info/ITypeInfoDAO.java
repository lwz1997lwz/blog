package org.javaex.blog.dao.type_info;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.TypeInfo;

public interface ITypeInfoDAO {

	/**
	 * 用于查询文章分类
	 */
	List <TypeInfo> list();

	void insert(@Param("name") String name, @Param("sort") String sort);
	//通过Param标签给传入的name参数命名为name，在xml中可以通过#{name}取值

	void update(@Param("id") String id, @Param("name") String name,
			  	@Param("sort") String sort);

	void delete(@Param("idArr") String[] idArr);

	TypeInfo selectByTypeId(@Param("typeId") String typeId);
}