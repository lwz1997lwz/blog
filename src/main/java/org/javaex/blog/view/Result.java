package org.javaex.blog.view;

import java.util.HashMap;

public class Result {
	//code 表示状态码  成功：000000 失败：999999
    private String code;
    //message 表示错误消息 
    private String message;
    //返回数据的链式
    private HashMap<String, Object> data = new HashMap<String, Object>();
    public static  Result success() {
		// TODO Auto-generated method stub
	    Result result = new Result();
	    result.setCode("000000");
		return result;
	}
    public static Result error(String message) {
		// TODO Auto-generated method stub
    	 Result result = new Result();
 	    result.setCode("999999");
 	    result.setMessage(message);
		return result;
	}
    public Result add(String key,Object value){
		this.getData().put(key,value );
    	return this;
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HashMap<String, Object> getData() {
		return data;
	}
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
    
}
