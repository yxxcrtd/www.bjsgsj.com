package com.bjsgsj.site.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;

import com.bjsgsj.site.dao.MessageDao;
import com.bjsgsj.site.dao.PictureDao;
import com.bjsgsj.site.dao.ProjectDao;
import com.bjsgsj.site.dao.UserDao;
import com.bjsgsj.site.param.MessageQueryParam;
import com.bjsgsj.util.CommonUtil;

import freemarker.template.TemplateException;

/**
 * 
 * Service接口实现的基类
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 10:45:09
 */
public class BaseServiceImpl implements ServletContextAware {

	/**
	 * 日志
	 */
	protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	/**
	 * Map
	 */
	protected Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 留言的查询参数
	 */
	protected MessageQueryParam param = new MessageQueryParam();

	/**
	 * success
	 */
	protected static final String SUCCESS = "success";
	
	/**
	 * servletContext
	 */
	protected ServletContext servletContext;

	/** 
	 * 用户DAO
	 */
	protected UserDao userDao;
	
	/**
	 * 在线留言DAO
	 */
	protected MessageDao messageDao;
	
	/**
	 * 菜单DAO
	 */
	protected ProjectDao projectDao;
	
	/**
	 * 图片DAO
	 */
	protected PictureDao pictureDao;
	
	/**
	 * 生成留言的HTML
	 * 
	 * @param map
	 */
	protected void generateMessageHTML(Map<String, Object> map) {
		try {
			CommonUtil.generateHTML("WEB-INF/ftl/site", "Message.ftl", "message.html", map, servletContext);
			logger.info("在线留言静态页面生成成功！");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户DAO的set方法
	 * 
	 * @param userDao
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 在线留言DAO的set方法
	 * 
	 * @param messageDao
	 */
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	/**
	 * 菜单DAO的set方法
	 * 
	 * @param projectDao
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * 图片DAO的set方法
	 * 
	 * @param pictureDao
	 */
	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}
	
	/* (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
