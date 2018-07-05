package org.javaex.blog.LinException;
/*
 * 自定义系统异常类   针对预期的异常 需要在程序中抛出此类的异常
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
