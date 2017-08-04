package site.chronos.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.gson.Gson;

import site.chronos.constant.CacheKeys;
import site.chronos.constant.CommonConstants;
import site.chronos.constant.Result;
import site.chronos.entity.User;
import site.chronos.exception.BusinessException;
import site.chronos.mapper.UserMapper;
import site.chronos.service.UserService;
import site.chronos.utils.Utils;


@Service
public class UserServiceImpl implements UserService{

	private Logger LOGGER=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private  PasswordEncoder bcrypt;
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Override
	public Result userRegiest(String phone, String pass) {
		LOGGER.info("用户注册入参,phone:{}",phone);
		if(StringUtils.isEmpty(phone) && phone.trim().length() < 11 && phone.startsWith("1")){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PARAMS);
		}
		if(StringUtils.isEmpty(pass)){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PASS);
		}
		if(pass.trim().length() < 8){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PASS_LENGTH_TEMP);
		}
		Result selectUserByPhone = selectUserByPhone(phone);
		if(!ObjectUtils.isEmpty(selectUserByPhone.getResult())){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PHONE_REGIEST);
		}
//		String password = Hashing.md5().hashString(pass.trim(), StandardCharsets.UTF_8).toString().toUpperCase();//MD5散列加密
		User user = new User();
		user.setId(Utils.getNewId());
		user.setPassword(bcrypt.encode(pass));
		user.setPhone(phone);
		user.setAlisa(phone);
		user.setCreateTime(Utils.getNewTime());
		user.setIsDel(0);
		userMapper.insertSelective(user);
		return new Result();
	}
	@Override
	public Result userLogin(String phone, String pass) {
		if(StringUtils.isEmpty(phone) && phone.trim().length() < 11 && phone.startsWith("1")){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PARAMS); //手机号非法
		}
		if(StringUtils.isEmpty(pass)){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PASS);//密码为空
		}
		if(pass.trim().length() < 8){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PASS_LENGTH_TEMP);//密码不够8位
		}
		Result selectUserByPhone = this.selectUserByPhone(phone);
		if(ObjectUtils.isEmpty(selectUserByPhone.getResult())){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PHONE);//手机号未注册
		}
		User user = (User)selectUserByPhone.getResult();
		boolean matches = bcrypt.matches(pass, user.getPassword());
		if(!matches){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PASS_);//密码错误
		}
		return new Result(user.getId());
	}
	
	@Override
	public Result selectUserByPhone(String phone) {
		if(StringUtils.isEmpty(phone) && phone.trim().length() < 11 && phone.startsWith("1")){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PHONE_NULL);//手机号为空
		}
		String redisCache = redisTemplate.opsForValue().get(CacheKeys.SELECT_USER_PHONE_KEYS + phone);
		if(redisCache!=null){
			Result result = new Gson().fromJson(redisCache, Result.class);
			if(result.getResult() == null){
				throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_USER);//用户不存在
			}
			return new Gson().fromJson(redisCache, Result.class);
		}
		User selectByPhone = userMapper.selectByPhone(phone);
		Result result = new Result();
		result.setResult(selectByPhone);
		//查询不存在，加一个空缓存
		redisTemplate.opsForValue().set(CacheKeys.SELECT_USER_PHONE_KEYS + phone, CommonConstants.GSONIGNORENULL.toJson(new Result()), 1, TimeUnit.HOURS);
		if(selectByPhone ==null){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_USER);//用户不存在
		}
		return result;//TODO  加缓存
	}
	@Override
	public Result selectUserById(String id) {
		if(StringUtils.isEmpty(id)){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_PARAMTER);//ID为空
		}
		String redisCache = redisTemplate.opsForValue().get(CacheKeys.SELECT_USER_PHONE_KEYS+id);
		if(redisCache!=null){
			Result result = new Gson().fromJson(redisCache, Result.class);
			if(result.getResult() == null){
				throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_USER);//用户不存在
			}
			return result;
		}
		User selectByPrimaryKey = userMapper.selectByPrimaryKey(id);
		redisTemplate.opsForValue().set(CacheKeys.SELECT_USER_PHONE_KEYS+id, CommonConstants.GSONIGNORENULL.toJson(new Result(selectByPrimaryKey)), 1, TimeUnit.HOURS);
		if(selectByPrimaryKey == null){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_USER);//用户不存在
		}
		return new Result(selectByPrimaryKey);
	}
	
	/**
	 * 用户状态改变
	 */
	@Override
	public Result userChange(String userId, Integer status) {
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PARAMS); //手机号非法
		}
		if(status == null){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_PARAMS,"用户状态非法"); //手机号非法
		}
		User user = userMapper.selectByPrimaryKey(userId);
		if(user == null){
			throw new BusinessException(CommonConstants.ErrorCode.ERROR_ILLEGAL_USER); //用户不存在
		}
		user.setStatus(status);
		userMapper.updateByPrimaryKeySelective(user);
		return new Result();
	}

}
