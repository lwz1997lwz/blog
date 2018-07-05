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
	 * 用于查询文章列表并显示到前台
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
		//将articlelist放入pageInfo 中的list属性中，可在html中通过${pageInfo.list}遍历获取
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
	//	modelMap.put("typeId", typeId);//用于在页面上回显
	//	modelMap.put("articlelist", articlelist);
		return "portal/index";	
	}
	/*
	 * 根据文章类型查询相应的文章
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
		//将articlelist放入pageInfo 中的list属性中，可在html中通过${pageInfo.list}遍历获取
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		TypeInfo typeInfo = typeInfoService.selectByTypeId(typeId);
		modelMap.put("typeInfo", typeInfo);
		List<TypeInfo> typeList =  typeInfoService.list();//用于显示文章目录
		modelMap.put("typeList", typeList);
		return "portal/articleByType";	
	}
	/*
	 * 根据文章id主键查询文章
	 */
	@RequestMapping("articleContent.action")
	public String articleContent(ModelMap modelMap ,
			@RequestParam(value="articleId" ,required=false) String articleId){
		ArticleInfo article= articleInfoService.selectById(articleId);
		if(article==null){
			return "portal/404";
		}
		int viewCount = article.getViewCount();
		viewCount++;//阅读量自增
		articleInfoService.updateViewCount(articleId,viewCount);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("article", article);
		return "portal/articleContent";	
	}
}
