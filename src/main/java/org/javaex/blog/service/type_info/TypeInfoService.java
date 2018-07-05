package org.javaex.blog.service.type_info;

import java.util.List;

import org.javaex.blog.LinException.LinException;
import org.javaex.blog.dao.article_info.IArticleInfoDAO;
import org.javaex.blog.dao.type_info.ITypeInfoDAO;
import org.javaex.blog.view.TypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class TypeInfoService {
    @Autowired
	ITypeInfoDAO iTypeInfoDAO;
    @Autowired
 	IArticleInfoDAO iArticleInfoDAO;
	public List<TypeInfo> list() {
			
		return iTypeInfoDAO.list();
	}
	/*
	 * 执行保存操作，若该记录有id则使用update 若无id则使用insert
	 */
	public void save(String[] idArr, String[] nameArr, String[] sortArr) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i<idArr.length;i++){
			if(StringUtils.isEmpty(idArr[i])){
				iTypeInfoDAO.insert(nameArr[i],sortArr[i]);
			}
			else {
				iTypeInfoDAO.update(idArr[i],nameArr[i],sortArr[i]);
			}
		}
	}
	/*
	 * 批量删除所选记录,直接传入数组(删除时需要判断该列表下文章数量是否大于0，否则删除会产生垃圾数据)
	 */
	public void delete(String[] idArr) throws LinException {
		// TODO Auto-generated method stub
		if(iArticleInfoDAO.countByTypeId(idArr)>0){
			throw new LinException("该分类下尚有文章因此无法删除");
		}
			//删除回收站中该列表的所有文章
		iArticleInfoDAO.deleteByTypeId(idArr);
		iTypeInfoDAO.delete(idArr);
	}
	/*
	 * 根据typeId来查询相应的类型
	 */
	public TypeInfo selectByTypeId(String typeId) {
		
		return iTypeInfoDAO.selectByTypeId(typeId);
	}
}
