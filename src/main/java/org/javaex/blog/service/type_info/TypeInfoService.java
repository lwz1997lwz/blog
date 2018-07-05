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
	 * ִ�б�����������ü�¼��id��ʹ��update ����id��ʹ��insert
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
	 * ����ɾ����ѡ��¼,ֱ�Ӵ�������(ɾ��ʱ��Ҫ�жϸ��б������������Ƿ����0������ɾ���������������)
	 */
	public void delete(String[] idArr) throws LinException {
		// TODO Auto-generated method stub
		if(iArticleInfoDAO.countByTypeId(idArr)>0){
			throw new LinException("�÷�����������������޷�ɾ��");
		}
			//ɾ������վ�и��б����������
		iArticleInfoDAO.deleteByTypeId(idArr);
		iTypeInfoDAO.delete(idArr);
	}
	/*
	 * ����typeId����ѯ��Ӧ������
	 */
	public TypeInfo selectByTypeId(String typeId) {
		
		return iTypeInfoDAO.selectByTypeId(typeId);
	}
}
