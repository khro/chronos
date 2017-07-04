package site.chronos.utils;

import java.io.Serializable;

import site.chronos.constant.CommonConstants;


public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code ;
	
	private Object result;
	
	private  String message;
	
	public Result(){
		this.result = "success";
		this.code = CommonConstants.CODE;
	}
	public Result(Object obj){
		this.result = obj;
		this.code = CommonConstants.CODE;
		this.message =  "success";
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(CommonConstants.ErrorCode message) {
		this.code = String.valueOf(message.getCode());
		this.message = String.valueOf(message.getDescription());
		return this;
	}
	
	public Result setMessage(CommonConstants.ErrorCode message,String temMessage) {
		this.code = String.valueOf(message.getCode());
		this.message = String.valueOf(temMessage);
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public Result setResult(Object result) {
		this.result = result;
		return this;
	}
	
	
}
