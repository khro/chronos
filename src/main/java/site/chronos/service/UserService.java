package site.chronos.service;

import site.chronos.utils.Result;

public interface UserService {
	
	/**
	 * 注册
	 * @param phone
	 * @param pass
	 * @return
	 */
	public Result userRegiest(String phone,String pass);
	
	/**
	 * 手机号查询User
	 * @param phone
	 * @return
	 */
	public Result selectUserByPhone(String phone);
	
	/**
	 * 手机号查询User
	 * @param phone
	 * @return
	 */
	public Result selectUserById(String id);
	
	/**
	 * 登录
	 * @param phone
	 * @param pass
	 * @return
	 */
	public Result userLogin(String phone,String pass);
	

}
