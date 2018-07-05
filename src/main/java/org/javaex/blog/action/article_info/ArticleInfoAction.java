package org.javaex.blog.action.article_info;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.javaex.blog.service.article_info.ArticleInfoService;
import org.javaex.blog.service.type_info.TypeInfoService;
import org.javaex.blog.view.ArticleInfo;
import org.javaex.blog.view.Result;
import org.javaex.blog.view.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Controller
@RequestMapping("article_info")
public class ArticleInfoAction {
	@Autowired
	ArticleInfoService articleInfoService;
	@Autowired
	TypeInfoService typeInfoService;
	
	/*
	 * 用于查询文章列表(所有文章)
	 */
	@RequestMapping("articlelist.action")
	public String list(ModelMap modelMap ,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="4") int pageSize,
			@RequestParam(value="typeId" ,required=false) String typeId,
			@RequestParam(value="keyWord",required=false) String keyWord,
			@RequestParam(value="startDate",required=false) String startDate,
			@RequestParam(value="endDate",required=false) String  endDate){
		HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("keyWord", keyWord);
			param.put("typeId", typeId);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("status", 1);
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> articlelist= articleInfoService.list(param);
		//将articlelist放入pageInfo 中的list属性中，可在html中通过${pageInfo.list}遍历获取
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("typeId", typeId);//用于在页面上回显
	//	modelMap.put("articlelist", articlelist);
		return "admin/article_info/articlelist";	
	}
	
	/*
	 * 用于查询回收站文章列表
	 */

	@RequestMapping("recyclelist.action")
	public String recyclelist(ModelMap modelMap ,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum,
			@RequestParam(value="pageSize", defaultValue="4") int pageSize,
			@RequestParam(value="typeId" ,required=false) String typeId,
			@RequestParam(value="keyWord",required=false) String keyWord,
			@RequestParam(value="startDate",required=false) String startDate,
			@RequestParam(value="endDate",required=false) String  endDate){
		HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("keyWord", keyWord);
			param.put("typeId", typeId);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("status",0);//回收站中的文章status都为0
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> articlelist= articleInfoService.list(param);
		//将articlelist放入pageInfo 中的list属性中，可在html中通过${pageInfo.list}遍历获取
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("typeId", typeId);//用于在页面上回显
	//	modelMap.put("articlelist", articlelist);
		return "admin/article_info/recyclelist";	
	}

	/*
	 * 编辑文章，其中传入的id可以为空 所以需要设置required=false
	 */
	@RequestMapping("edit.action")
	public String edit(ModelMap modelMap,@RequestParam(required=false ,value="id") String id){
		if(!StringUtils.isEmpty(id)){
			ArticleInfo articleInfo = articleInfoService.selectById(id);
			modelMap.put("articleInfo", articleInfo);
		}	
			List<TypeInfo> typeList =  typeInfoService.list();
			modelMap.put("typeList", typeList);
		return "admin/article_info/edit";	
	}
	/*
	 * 用于删除批量文章(移入回收站status=0)
	 */
	@RequestMapping("deleteArticle.json")
	@ResponseBody
	public Result deleteArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.delete(idArr);
		return Result.success();	
	}
	/*
	 * 用于彻底删除批量文章
	 */
	@RequestMapping("fulldeleteArticle.json")
	@ResponseBody
	public Result fulldeleteArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.fulldelete(idArr);
		return Result.success();	
	}
	/*
	 * 用于恢复批量文章(移入回收站status=1)
	 */
	@RequestMapping("recoverArticle.json")
	@ResponseBody
	public Result recoverArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.recover(idArr);
		return Result.success();	
	}
	/**
	 * 上传文件到磁盘（物理路径）
	 * 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// 文件原名称
		String szFileName = file.getOriginalFilename();
		// 重命名后的文件名称
		String szNewFileName = "";
		// 根据日期自动创建3级目录
		String szDateFolder = "";
		
		// 上传文件
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// 存储文件的物理路径
			String szFilePath = "C:\\upload\\" + szDateFolder;
			// 自动创建文件夹
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// 新的文件名称
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// 新文件
			File newFile = new File(szFilePath+"\\"+szNewFileName);
			
			// 将内存中的数据写入磁盘
			file.transferTo(newFile);
		}
		
		return Result.success().add("imgUrl", szDateFolder+"/"+szNewFileName);
	} 
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(ArticleInfo articleInfo){
		articleInfoService.save(articleInfo);
		return Result.success();
	}
}
