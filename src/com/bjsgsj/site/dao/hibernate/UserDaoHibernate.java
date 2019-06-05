package com.bjsgsj.site.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsgsj.site.dao.UserDao;
import com.bjsgsj.site.pojos.User;

/**
 * 用户DAO的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 10:51:32
 */
@SuppressWarnings("unchecked")
public class UserDaoHibernate extends HibernateDaoSupport implements UserDao {
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.UserDao#getById(int)
	 */
	@Override
	public User getById(int id) {
		return (User) this.getHibernateTemplate().get(User.class, id);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.UserDao#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		List<User> list = this.getHibernateTemplate().find("FROM User WHERE username = ?", username);
		return (null != list && list.size() > 0) ? (User) list.get(0) : null;
	}

}
