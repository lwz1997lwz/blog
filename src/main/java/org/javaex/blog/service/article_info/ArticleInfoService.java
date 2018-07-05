package org.javaex.blog.service.article_info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.javaex.blog.dao.article_info.IArticleInfoDAO;
import org.javaex.blog.view.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ArticleInfoService {
	@Autowired
	IArticleInfoDAO iArticleInfoDAO;

	public List<ArticleInfo> list(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.list(param);
	}

	public ArticleInfo selectById(String id) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.selectById(id);
	}

	public void save(ArticleInfo articleInfo) {
		// TODO Auto-generated method stub
		//判断是否改文章是否是第一次编辑
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isEmpty(articleInfo.getId())){
			articleInfo.setStatus(1);
			articleInfo.setUpdateDate(sdf.format(date));
			articleInfo.setViewCount(1);
			iArticleInfoDAO.insert(articleInfo);
		}
		else {
			articleInfo.setUpdateDate(sdf.format(date));
			articleInfo.setStatus(1);
			iArticleInfoDAO.update(articleInfo);
		}
	}
	/*
	 * 删除文章即为将status改为0 移入回收站即可
	 */
	public void delete(String[] idArr) {
		// TODO Auto-generated method stub
		iArticleInfoDAO.delete(idArr);
	}

	public void recover(String[] idArr) {
		// TODO Auto-generated method stub
		iArticleInfoDAO.recover(idArr);
	}

	public void fulldelete(String[] idArr) {
		// TODO Auto-generated method stub
		iArticleInfoDAO.fulldelete(idArr);
	}

	public int countByTypeId(String[] idArr) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.countByTypeId(idArr);
	}

	public List<ArticleInfo> listByType(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.listByType(param);
	}

	public ArticleInfo articleContentById(String articleId) {
		// TODO Auto-generated method stub
		return iArticleInfoDAO.articleContentById(articleId);
	}


	public void updateViewCount(String articleId, int viewCount) {
		// TODO Auto-generated method stub
		iArticleInfoDAO.updateViewCount(articleId,viewCount);
	}
}
