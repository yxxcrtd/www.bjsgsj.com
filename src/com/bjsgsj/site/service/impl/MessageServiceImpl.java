package com.bjsgsj.site.service.impl;

import java.util.List;

import com.bjsgsj.site.param.MessageQueryParam;
import com.bjsgsj.site.pojos.Message;
import com.bjsgsj.site.service.MessageService;
import com.bjsgsj.util.Pager;

/**
 * 在线留言服务的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:28
 */
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.MessageService#getById(int)
	 */
	@Override
	public Message getById(int id) {
		return messageDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.MessageService#getMessageList(com.bjsgsj.site.param.MessageQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Message> getMessageList(MessageQueryParam param, Pager pager) {
		return messageDao.getMessageList(param, pager);
	}
	
	/**
	 * 获取所有的在线留言
	 * 
	 * @param param
	 * @return
	 */
	public List<Message> getMessageList(MessageQueryParam param) {
		return messageDao.getMessageList(param);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.MessageService#saveOrUpdate(com.bjsgsj.site.pojos.Message)
	 */
	@Override
	public String saveOrUpdate(Message message) {
		
		// 数据校验 TODO
		
		// 保存
		messageDao.saveOrUpdate(message);
		
		// 生成静态文件
		
		// 导航菜单列表
		// map.put("menuList", projectDao.getMenuList());
		
		// 留言信息列表
		map.put("messageList", getMessageList(param));
		generateMessageHTML(map);
		
		// 返回
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.MessageService#delete(int)
	 */
	@Override
	public void delete(int messageId) {
		Message message = messageDao.getById(messageId);
		if (null != message) {
			messageDao.delete(message);
			
			// 生成静态文件
			map.put("messageList", getMessageList(param));
			generateMessageHTML(map);
		}
	}

}
