package com.bjsgsj.site.dao;

import java.util.List;

import com.bjsgsj.site.param.MessageQueryParam;
import com.bjsgsj.site.pojos.Message;
import com.bjsgsj.util.Pager;

/**
 * 在线留言DAO
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:23
 */
public interface MessageDao {

	/**
	 * 根据主键标识获得对象
	 * 
	 * @param id
	 * @return
	 */
	public Message getById(int id);
	
	/**
	 * 得到带分页的在线留言列表
	 * 
	 * @param param
	 * @param pager
	 * @return
	 */
	public List<Message> getMessageList(MessageQueryParam param, Pager pager);
	
	/**
	 * 获取所有的在线留言
	 * 
	 * @param param
	 * @return
	 */
	public List<Message> getMessageList(MessageQueryParam param);
	
	/**
	 * 保存或修改
	 * 
	 * @param message
	 */
	public void saveOrUpdate(Message message);
	
	/**
	 * 删除
	 * 
	 * @param message
	 */
	public void delete(Message message);

}
