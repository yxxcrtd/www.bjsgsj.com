package com.bjsgsj.site.service;

import com.bjsgsj.site.pojos.User;

/**
 * 
 * 用户服务
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 10:53:50
 */
public interface UserService {
	
	/**
	 * 根据主键标识获得对象
	 * 
	 * @param id
	 * @return
	 */
	public User getById(int id);
	
	/**
	 * 根据用户名得到用户对象
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
	
}
