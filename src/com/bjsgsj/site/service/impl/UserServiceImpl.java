package com.bjsgsj.site.service.impl;

import com.bjsgsj.site.pojos.User;
import com.bjsgsj.site.service.UserService;

/**
 * 
 * 用户服务的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 10:52:57
 */
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.UserService#getById(int)
	 */
	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.UserService#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		// 用户名不能为空
		if (null == username || "".equals(username) || username.length() == 0) {
			return null;
		}
		
		return userDao.getUserByUsername(username);
	}

}
