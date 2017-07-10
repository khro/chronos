package site.chronos.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;
import site.chronos.service.UserService;

@Controller
@RequestMapping("/u")
@ResponseBody
@Api(value = "用户模块", tags = "UserController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {})
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "login", notes = "登录")
	@PutMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("phone") String phone, @RequestParam("pass") String pass,
			HttpSession session) throws Exception {
		Result userLogin = userService.userLogin(phone, pass);
		if(CommonConstants.CODE.equals(userLogin.getCode())){
			session.setAttribute(CommonConstants.SESSION_KEY, phone);
		}
		return ResponseEntity.ok(userLogin);
	}
	
	@ApiOperation(value = "loginOut", notes = "登出")
	@PutMapping("/loginOut")
	public ResponseEntity<Object> loginOut(HttpSession session) throws Exception {
		session.removeAttribute(CommonConstants.SESSION_KEY);
		return ResponseEntity.ok(new Result());
	}

	@ApiOperation(value = "regiest", notes = "注册")
	@PutMapping("/regiest")
	public ResponseEntity<Object> regiest(@RequestParam("phone") String phone, @RequestParam("pass") String pass,
			HttpSession session) throws Exception {
		Result userRegiest = userService.userRegiest(phone, pass);
		return ResponseEntity.ok(userRegiest);
	}

}
