package org.javaex.blog.LinException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javaex.blog.view.Result;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;



/**
 * ȫ���쳣������
 */
public class LinExceptionHandler implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
	
		// �����̨��ӡ������Ϣ
		e.printStackTrace();
		
		// ���������Ϣ
		String message = "ϵͳ�쳣�����Ժ�����";
		// �ж��ǲ����Լ��׳��Ĵ���
		if (e instanceof LinException) {
			message = ((LinException)e).getMessage();
		}
		
		// �ж���json������ҳ��ת������
		HandlerMethod handMethod = (HandlerMethod)handler;
		ResponseBody responseBody = handMethod.getMethod().getAnnotation(ResponseBody.class);
		if (responseBody!=null) {
			// json���󣨷���json���ݣ�
			//Map<String, Object> responseMap = new HashMap<String, Object>();
			//responseMap.put("code", "999999");
			//responseMap.put("message", message);
			Result result = new Result();
			result.setCode("999999");
            result.setMessage(message);
			String json = new Gson().toJson(result);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				response.getWriter().write(json);
				response.getWriter().flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			// ����һ���յ�ModelAndView��ʾ�Ѿ��ֶ�������Ӧ
			return new ModelAndView();
		}
		
		// ҳ��ת������ת������ҳ�棩
		ModelAndView modelAndView = new ModelAndView();
		//��������Ϣ����ҳ��
		modelAndView.addObject("message", message);
		//ָ�����ҳ��
		modelAndView.setViewName("error");
		
		return modelAndView;
	}
}