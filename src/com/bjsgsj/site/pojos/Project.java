package com.bjsgsj.site.pojos;

import java.io.Serializable;

/**
 * 项目
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-08-02 15:26:32
 */
public class Project implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7248581550391076589L;

	/**
	 * 项目Id
	 */
	private int projectId;
	
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 项目描述
	 */
	private String describe;
	
	/**
	 * 项目的父名称
	 */
	private String parent;

	/**
	 * Default Constructor
	 */
	public Project() {
		//
	}

	/**
	 * 项目Id的get方法
	 * 
	 * @return
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * 项目Id的set方法
	 * 
	 * @param projectId
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * 项目中文名的get方法
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 项目中文名的set方法
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 项目描述的get方法
	 * 
	 * @return
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 项目描述的set方法
	 * 
	 * @param describe
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 项目的父名称的get方法
	 * 
	 * @return
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * 项目的父名称的set方法
	 * 
	 * @param parent
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}
	
}
