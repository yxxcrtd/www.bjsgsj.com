package com.bjsgsj.site.dao;

import com.bjsgsj.site.pojos.User;

/**
 * 
 * 用户DAO
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 10:52:27
 */
public interface UserDao {

	/**
	 * 根据主键标识获得对象
	 * 
	 * @param id
	 * @return
	 */
	public User getById(int id);

	/**
	 * 根据用户名或邮件地址得到用户对象
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

}
