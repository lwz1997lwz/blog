package org.javaex.blog.dao.article_info;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.ArticleInfo;
import org.javaex.blog.view.TypeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IArticleInfoDAO {
     
	List<ArticleInfo> list(HashMap<String, Object> param);//查询文章列表

	ArticleInfo selectById(@Param("id") String id);//根据id查找文章

	void insert(ArticleInfo articleInfo);//新增插入文章

	void update(ArticleInfo articleInfo);//更新文章

	void delete(@Param("idArr")  String[] idArr);//批量删除

	void recover(@Param("idArr") String[] idArr);//批量还原

	void fulldelete(@Param("idArr") String[] idArr);//批量完全删除

	int countByTypeId(@Param("typeIdArr") String[] idArr);//查询该分类文章数目

	void deleteByTypeId(@Param("typeIdArr") String[] idArr);//删除文章分类

	List<ArticleInfo> listByType(HashMap<String, Object> param);//根据类型查询文章

	ArticleInfo articleContentById(@Param("articleId") String articleId);//根据文章主键查询文章

	void updateViewCount(@Param("articleId") String articleId,@Param("viewCount") int viewCount);//更新阅读量


}