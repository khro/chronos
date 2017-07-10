package site.chronos.controller.core;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import site.chronos.constant.CommonConstants;
import site.chronos.service.UserService;

public class BaseController {
	
	
	@Autowired
	private UserService userService;
	
	
	public String getId(HttpSession session){
		String userid = (String)session.getAttribute(CommonConstants.SESSION_KEY);
		userService.selectUserById(userid);
		return userid;
		
	}

}
