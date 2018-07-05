package org.javaex.blog.action.portal;

import java.util.HashMap;
import java.util.List;

import org.javaex.blog.service.article_info.ArticleInfoService;
import org.javaex.blog.service.type_info.TypeInfoService;
import org.javaex.blog.view.ArticleInfo;
import org.javaex.blog.view.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("portal")
public class PortalAction {

	@Autowired
	ArticleInfoService articleInfoService;
	@Autowired
	TypeInfoService typeInfoService;
	
	/*
	 * ���ڲ�ѯ�����б���ʾ��ǰ̨
	 */
	@RequestMapping("index.action")
	public String list(ModelMap modelMap ,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="4") int pageSize,
			@RequestParam(value="typeId" ,required=false) String typeId){
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("status", 1);
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> articlelist= articleInfoService.list(param);
		//��articlelist����pageInfo �е�list�����У�����html��ͨ��${pageInfo.list}������ȡ
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
	//	modelMap.put("typeId", typeId);//������ҳ���ϻ���
	//	modelMap.put("articlelist", articlelist);
		return "portal/index";	
	}
	/*
	 * �����������Ͳ�ѯ��Ӧ������
	 */
	@RequestMapping("articleByTypelist.action")
	public String articleByTypelist(ModelMap modelMap ,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="4") int pageSize,
			@RequestParam(value="typeId" ,required=false) String typeId){
		HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("typeId", typeId);
			param.put("status", 1);
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> articlelist= articleInfoService.list(param);
		if(articlelist.size()==0){
			return "portal/404";
		}
		//��articlelist����pageInfo �е�list�����У�����html��ͨ��${pageInfo.list}������ȡ
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		TypeInfo typeInfo = typeInfoService.selectByTypeId(typeId);
		modelMap.put("typeInfo", typeInfo);
		List<TypeInfo> typeList =  typeInfoService.list();//������ʾ����Ŀ¼
		modelMap.put("typeList", typeList);
		return "portal/articleByType";	
	}
	/*
	 * ��������id������ѯ����
	 */
	@RequestMapping("articleContent.action")
	public String articleContent(ModelMap modelMap ,
			@RequestParam(value="articleId" ,required=false) String articleId){
		ArticleInfo article= articleInfoService.selectById(articleId);
		if(article==null){
			return "portal/404";
		}
		int viewCount = article.getViewCount();
		viewCount++;//�Ķ�������
		articleInfoService.updateViewCount(articleId,viewCount);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("article", article);
		return "portal/articleContent";	
	}
}
