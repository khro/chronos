package site.chronos.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;

//@ControllerAdvice
public class GlobalExceptionHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
    public Result messageExceptionHandler(BusinessException ex) {
		LOGGER.info("统一异常处理：{}",CommonConstants.GSONIGNORENULL.toJson(ex));
        Result result = new Result();
//        result.setCode(String.valueOf(ex.getErrorCode().getCode()));
        if(!StringUtils.isEmpty(ex.getDetailMsg())){
        	result.setMessage(ex.getErrorCode(),ex.getDetailMsg());
        }else{
        	result.setMessage(ex.getErrorCode());
        }
        result.setResult("FAILED");
        LOGGER.info("统一异常处理返回：{}",CommonConstants.GSONIGNORENULL.toJson(result));
        return result;
    }
}
