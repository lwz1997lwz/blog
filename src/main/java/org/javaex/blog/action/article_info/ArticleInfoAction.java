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
	 * ���ڲ�ѯ�����б�(��������)
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
		//��articlelist����pageInfo �е�list�����У�����html��ͨ��${pageInfo.list}������ȡ
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("typeId", typeId);//������ҳ���ϻ���
	//	modelMap.put("articlelist", articlelist);
		return "admin/article_info/articlelist";	
	}
	
	/*
	 * ���ڲ�ѯ����վ�����б�
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
			param.put("status",0);//����վ�е�����status��Ϊ0
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleInfo> articlelist= articleInfoService.list(param);
		//��articlelist����pageInfo �е�list�����У�����html��ͨ��${pageInfo.list}������ȡ
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>(articlelist);
		modelMap.put("pageInfo", pageInfo);
		List<TypeInfo> typeList =  typeInfoService.list();
		modelMap.put("typeList", typeList);
		modelMap.put("typeId", typeId);//������ҳ���ϻ���
	//	modelMap.put("articlelist", articlelist);
		return "admin/article_info/recyclelist";	
	}

	/*
	 * �༭���£����д����id����Ϊ�� ������Ҫ����required=false
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
	 * ����ɾ����������(�������վstatus=0)
	 */
	@RequestMapping("deleteArticle.json")
	@ResponseBody
	public Result deleteArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.delete(idArr);
		return Result.success();	
	}
	/*
	 * ���ڳ���ɾ����������
	 */
	@RequestMapping("fulldeleteArticle.json")
	@ResponseBody
	public Result fulldeleteArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.fulldelete(idArr);
		return Result.success();	
	}
	/*
	 * ���ڻָ���������(�������վstatus=1)
	 */
	@RequestMapping("recoverArticle.json")
	@ResponseBody
	public Result recoverArticle(@RequestParam("idArr") String [] idArr) {
		articleInfoService.recover(idArr);
		return Result.success();	
	}
	/**
	 * �ϴ��ļ������̣�����·����
	 * 
	 */
	@RequestMapping("upload.json")
	@ResponseBody
	public Result upload(MultipartFile file, HttpServletRequest request) throws IOException {
		
		// �ļ�ԭ����
		String szFileName = file.getOriginalFilename();
		// ����������ļ�����
		String szNewFileName = "";
		// ���������Զ�����3��Ŀ¼
		String szDateFolder = "";
		
		// �ϴ��ļ�
		if (file!=null && szFileName!=null && szFileName.length()>0) {
			Date date = new Date();
			szDateFolder = new SimpleDateFormat("yyyy/MM/dd").format(date);
			// �洢�ļ�������·��
			String szFilePath = "C:\\upload\\" + szDateFolder;
			// �Զ������ļ���
			File f = new File(szFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			
			// �µ��ļ�����
			szNewFileName = UUID.randomUUID() + szFileName.substring(szFileName.lastIndexOf("."));
			// ���ļ�
			File newFile = new File(szFilePath+"\\"+szNewFileName);
			
			// ���ڴ��е�����д�����
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
