package site.chronos.constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonConstants {
	
	public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

    public static final Gson GSONIGNORENULL = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    
    public static final String CODE= "000000";
    public static final String SESSION_KEY= "USER";
    
    public static enum ErrorCode{

        //系统参数
        /**
         * 默认值	描述
         * 0x00000	您的 ip 被限制访问
         * 0x00001	缺少参数或参数不合法
         * 0x00002	你的账号存在异常已被锁定
         * 0x00003	重复申请
         * 0x00004	银行卡类型不支持
         * 0x00006	服务器内部错误
         * 0x00007	尚未进行银行卡绑定操作
         * 0x00009	没有权限
         * 0x00018	服务器正在维护
         */

        ERROR_PARAMS(0x00001, "缺少参数或参数不合法"),
        ERROR_ACCOUNT_BLOCKED(0x00002, "账号被锁定，请30分钟后尝试"),
        ERROR_REPEAT_REQUEST(0x00003, "重复申请"),
        ERROR_REQUEST_TIMEOUT(0x00005, "请求过期"),
        ERROR_IP_BLOCKED(0xFF000, "您的 ip 被限制访问"),
        ERROR_ILLEGAL_PARAMTER(0xFF001, "ID不合法"),
        ERROR_ILLEGAL_PHONE(0xFF002, "电话号码不能为空"),
        ERROR_ILLEGAL_CONTANTS(0xFF003, "操作内容不能为空"),
        ERROR_ILLEGAL_USER(0xFF004, "用户不存在"),
        ERROR_TEMP(0xFF005, "临时变量，为了保留原先code值"),
        ERROR_PASS(0xFF006, "密码不能为空"),
        ERROR_PASS_(0xFF006, "密码错误"),
        ERROR_PASS_LENGTH_TEMP(0xFF006, "密码太短了"),
        ERROR_PHONE(0xFF007, "该手机号未注册"),
        ERROR_PHONE_NULL(0xFF007, "手机号为空"),
        ERROR_PHONE_REGIEST(0xFF007, "该手机号已被注册"),
        ERROR_TITLE(0xFF008, "标题为空"),
        ERROR_QUESTION(0xFF009, "内容为空"),
        ERROR_ADD_REVIEW(0xFF010, "还有未审核问题。"),
        ERROR_REVIEW_NULL(0xFF011, "审核不存在。"),
        ;
        private Integer code;
        private String description;
        private String tempDesc;

        public String getTempDesc() {
            return tempDesc;
        }

        public void setTempDesc(String tempDesc) {
            this.tempDesc = tempDesc;
        }

        public Integer getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        ErrorCode(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        public ErrorCode customDescription(String customError) {
            ERROR_TEMP.code = code;
            ERROR_TEMP.description = description;
            if (customError != null) {
                this.description = customError;
            }

            return this;
        }

        public String toString() {
//			JSONObject json = new JSONObject();
//			JSONObject error = new JSONObject();
//			error.put("code", code);
//			error.put("description", description);
//			json.put("error", error);
//
//			if (ERROR_TEMP.code.equals(code)) {
//				this.description = ERROR_TEMP.description;
//			}
//			return json.toString();
            return toString(null);
        }

        /**
         * 用于返回注解描述信息
         *
         * @param customerDescription
         * @return
         */
        public String toString(String customerDescription) {
//            JSONObject json = new JSONObject();
//            JSONObject error = new JSONObject();
//            error.put("code", code);
//            String userDefinedDescrption = description;
//            if (customerDescription != null && customerDescription.length() > 0) {
//                userDefinedDescrption = userDefinedDescrption + ";" + customerDescription;
//            }
//            error.put("description", userDefinedDescrption);
//            json.put("error", error);
//
//            if (ERROR_TEMP.code.equals(code)) {
//                this.description = ERROR_TEMP.description;
//            }
//            return json.toString();
        	return null;
        }
    
   }

}
