package org.javaex.blog.action.type_info;

import java.util.List;

import org.javaex.blog.LinException.LinException;
import org.javaex.blog.service.article_info.ArticleInfoService;
import org.javaex.blog.service.type_info.TypeInfoService;
import org.javaex.blog.view.Result;
import org.javaex.blog.view.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("type_info")
public class TypeInfoAction {
	@Autowired
    TypeInfoService typeInfoService;
	/*
	 * ����������Ĳ�ѯ
	 */
	@RequestMapping("list.action")
	public String list(ModelMap modelMap){
		List<TypeInfo> sortlist= typeInfoService.list();
		modelMap.put("sortlist", sortlist);
		return "admin/type_info/list";	
	}
	/*
	 * ���·���ı���
	 */
	@RequestMapping("save.json")
	@ResponseBody
	public Result save(@RequestParam("idArr") String [] idArr,
		               @RequestParam("nameArr") String [] nameArr,
		               @RequestParam("sortArr") String [] sortArr){
		typeInfoService.save(idArr,nameArr,sortArr);
		
		return Result.success();	
	}
	/*
	 * ���·����ɾ��
	 */
	@RequestMapping("delete.json")
	@ResponseBody
	public Result delete(@RequestParam("idArr") String [] idArr) throws LinException{
			
		typeInfoService.delete(idArr);
		
		return Result.success();	
	}
}
