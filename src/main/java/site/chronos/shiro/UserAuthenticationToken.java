package site.chronos.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class UserAuthenticationToken implements AuthenticationToken, RememberMeAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean rememberMe;

	public UserAuthenticationToken(String username, String password, boolean rememberMe) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
	}

	/**
	 * 是否记住密码
	 */
	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}

	/**
	 * 获取用户密码
	 */
	@Override
	public Object getCredentials() {
		return this.password.toCharArray();
	}

	/**
	 * 获取用户登录名
	 */
	@Override
	public String getPrincipal() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
}