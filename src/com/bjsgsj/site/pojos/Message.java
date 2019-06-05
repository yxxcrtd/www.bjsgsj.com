package com.bjsgsj.site.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言对象
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-09 20:29:31
 */
public class Message implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4044836196333201247L;

	/**
	 * 留言的标识
	 */
	private int messageId;

	/**
	 * 留言人的姓或姓名
	 */
	private String name;

	/**
	 * 留言人的手机或固定电话或Email
	 */
	private String telephone;

	/**
	 * 留言的内容
	 */
	private String content;

	/**
	 * 留言的回复
	 */
	private String reply;

	/**
	 * 留言的时间
	 */
	private Date time = new Date();
	
	/**
	 * 留言人的IP地址
	 */
	private String ip;

	/**
	 * Default Constructor
	 */
	public Message() {
		//
	}

	/**
	 * 留言的标识的get方法
	 * 
	 * @return
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * 留言的标识的set方法
	 * 
	 * @param messageId
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/**
	 * 留言人的姓或姓名的get方法
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 留言人的姓或姓名的set方法
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 留言人的手机或固定电话的get方法
	 * 
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 留言人的手机或固定电话的set方法
	 * 
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 留言的内容的get方法
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 留言的内容的set方法
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 留言的回复的get方法
	 * 
	 * @return
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * 留言的回复的set方法
	 * 
	 * @param reply
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}

	/**
	 * 留言的时间的get方法
	 * 
	 * @return
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 留言的时间的set方法
	 * 
	 * @param time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 留言人的IP地址的get方法
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 留言人的IP地址的set方法
	 * 
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
