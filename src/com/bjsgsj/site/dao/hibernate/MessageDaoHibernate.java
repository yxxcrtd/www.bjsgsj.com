package com.bjsgsj.site.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsgsj.site.dao.MessageDao;
import com.bjsgsj.site.param.MessageQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.pojos.Message;
import com.bjsgsj.util.Pager;

/**
 * 在线留言DAO的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:17
 */
@SuppressWarnings("unchecked")
public class MessageDaoHibernate extends HibernateDaoSupport implements MessageDao {
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.MessageDao#getById(int)
	 */
	@Override
	public Message getById(int id) {
		return (Message) this.getHibernateTemplate().get(Message.class, id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.MessageDao#getMessageList(com.bjsgsj.site.param.MessageQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Message> getMessageList(MessageQueryParam param, Pager pager) {
		QueryHelper queryHelper = param.createQuery(false);
		if (null == pager) {
			return queryHelper.queryData(this.getHibernateTemplate(), -1, param.count);
		} else {
			return queryHelper.queryDataAndTotalCount(this.getHibernateTemplate(), pager);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.MessageDao#getMessageList(com.bjsgsj.site.param.MessageQueryParam)
	 */
	@Override
	public List<Message> getMessageList(MessageQueryParam param) {
		QueryHelper query = param.createQuery(true);
		return query.queryData(this.getHibernateTemplate());
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.MessageDao#saveOrUpdate(com.bjsgsj.site.pojos.Message)
	 */
	@Override
	public void saveOrUpdate(Message message) {
		this.getHibernateTemplate().saveOrUpdate(message);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.MessageDao#delete(com.bjsgsj.site.pojos.Message)
	 */
	@Override
	public void delete(Message message) {
		this.getHibernateTemplate().delete(message);
	}

}
