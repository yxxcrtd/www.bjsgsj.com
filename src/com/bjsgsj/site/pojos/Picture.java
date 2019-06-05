package com.bjsgsj.site.pojos;

import java.io.Serializable;

/**
 * 图片
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-08-02 17:01:02
 */
public class Picture implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9054508708033679369L;

	/**
	 * 图片ID
	 */
	private int pictureId;

	/**
	 * 父菜单ID（仅供查询使用）
	 */
	private int projectId;
	
	/**
	 * 父菜单对象
	 */
	private Project project;

	/**
	 * 图片描述
	 */
	private String describe;

	/**
	 * 图片原图连接
	 */
	private String link;

	/**
	 * 图片是否首页案例展示
	 */
	private boolean cases;

	/**
	 * Default Constructor
	 */
	public Picture() {
		//
	}

	/**
	 * 图片ID的get方法
	 * 
	 * @return
	 */
	public int getPictureId() {
		return pictureId;
	}

	/**
	 * 图片ID的set方法
	 * 
	 * @param pictureId
	 */
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}

	/**
	 * 父菜单ID的get方法
	 * 
	 * @return
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * 父菜单ID的set方法
	 * 
	 * @param projectId
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	/**
	 * 项目对象的get方法
	 * 
	 * @return
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * 项目对象的set方法
	 * 
	 * @param project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * 图片描述的get方法
	 * 
	 * @return
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 图片描述的set方法
	 * 
	 * @param describe
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	/**
	 * 图片原图连接的get方法
	 * 
	 * @return
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 图片原图连接的set方法
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * 图片是否首页案例展示的get方法
	 * 
	 * @return
	 */
	public boolean isCases() {
		return cases;
	}

	/**
	 * 图片是否首页案例展示的set方法
	 * 
	 * @param cases
	 */
	public void setCases(boolean cases) {
		this.cases = cases;
	}

}
