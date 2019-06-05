package com.bjsgsj.site.action.base;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.web.context.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action 的 基类 （如果getRequest()，getResponse()，getActionContext()三个方法没有用到的话就可以 去掉了！）
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-09 21:37:16
 */
public abstract class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware, SessionAware {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4784854793452573387L;
	
	/**
	 * HttpServletRequest 对象
	 */
	protected HttpServletRequest request;

	/**
	 * 得到 HttpServletRequest 对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/* (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * HttpServletResponse 对象
	 */
	protected HttpServletResponse response;

	/**
	 * 得到 HttpServletResponse 对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/* (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/**
	 * ServletContext Web应用环境对象
	 */
	protected ServletContext servletContext;

	/* (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * Session 对象
	 */
	protected Map<String, Object> session;

	/* (non-Javadoc)
	 * 
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
