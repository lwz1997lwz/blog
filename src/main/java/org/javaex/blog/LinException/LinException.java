package org.javaex.blog.LinException;
/*
 * �Զ���ϵͳ�쳣��   ���Ԥ�ڵ��쳣 ��Ҫ�ڳ������׳�������쳣
 */
@SuppressWarnings("serial")
public class LinException extends Exception {

	private String message;
    
	public LinException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
