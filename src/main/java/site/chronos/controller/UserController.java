package site.chronos.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import scala.reflect.internal.annotations.uncheckedBounds;
import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;
import site.chronos.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;

@Controller
@RequestMapping("/u")
@ResponseBody
@Api(value = "用户模块", tags = "UserController", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, authorizations = {})
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RedissonClient redissonClient;

	@ApiOperation(value = "login", notes = "登录")
	@PutMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("phone") String phone, @RequestParam("pass") String pass,
			HttpSession session) throws Exception {
		Result userLogin = userService.userLogin(phone, pass);
		if(CommonConstants.CODE.equals(userLogin.getCode())){
			session.setAttribute(CommonConstants.SESSION_KEY, (String)userLogin.getResult()); //userID加入到session
		}
		return ResponseEntity.ok(new Result());
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
	
	@GetMapping("/test")
	public ResponseEntity<Object> test() throws Exception {
		RLock lock = redissonClient.getLock("1");
		try {
			lock.lock(10, TimeUnit.SECONDS);
			Thread.sleep(5000);
			redisTemplate.opsForValue().set("a", "a1", 20, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(lock!=null){
				lock.unlock();
			}
		}
		String string = redisTemplate.opsForValue().get("a");
		
		return ResponseEntity.ok(string);
	}

}
