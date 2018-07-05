package org.javaex.blog.dao.article_info;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.javaex.blog.view.ArticleInfo;
import org.javaex.blog.view.TypeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public interface IArticleInfoDAO {
     
	List<ArticleInfo> list(HashMap<String, Object> param);//��ѯ�����б�

	ArticleInfo selectById(@Param("id") String id);//����id��������

	void insert(ArticleInfo articleInfo);//������������

	void update(ArticleInfo articleInfo);//��������

	void delete(@Param("idArr")  String[] idArr);//����ɾ��

	void recover(@Param("idArr") String[] idArr);//������ԭ

	void fulldelete(@Param("idArr") String[] idArr);//������ȫɾ��

	int countByTypeId(@Param("typeIdArr") String[] idArr);//��ѯ�÷���������Ŀ

	void deleteByTypeId(@Param("typeIdArr") String[] idArr);//ɾ�����·���

	List<ArticleInfo> listByType(HashMap<String, Object> param);//�������Ͳ�ѯ����

	ArticleInfo articleContentById(@Param("articleId") String articleId);//��������������ѯ����

	void updateViewCount(@Param("articleId") String articleId,@Param("viewCount") int viewCount);//�����Ķ���


}