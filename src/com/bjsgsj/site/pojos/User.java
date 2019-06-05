package com.bjsgsj.site.pojos;

import java.io.Serializable;

/**
 * 用户对象
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 08:08:20
 */
public class User implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4432706693862891846L;

	/**
	 * 用户标识
	 */
	private int userId;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 验证码
	 */
	private String verifyCode;

	/**
	 * Default Constructor
	 */
	public User() {
		//
	}

	/**
	 * 用户标识的get方法
	 * 
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * 用户标识的set方法
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 用户名的get方法
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 用户名的set方法
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 密码的get方法
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码的set方法
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 验证码的get方法
	 * 
	 * @return
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * 验证码的set方法
	 * 
	 * @param verifyCode
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
