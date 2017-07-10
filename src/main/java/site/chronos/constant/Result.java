package site.chronos.constant;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Result", description = "结果")
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("CODE")
	private String code ;
	
	@ApiModelProperty("结果")
	private Object result;
	
	@ApiModelProperty("描述信息")
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
