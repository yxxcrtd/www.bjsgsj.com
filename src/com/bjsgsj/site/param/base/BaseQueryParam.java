package com.bjsgsj.site.param.base;

/**
 * 查询参数的基类
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:57:07
 */
public abstract class BaseQueryParam {
	
	/** 
	 * 默认的记录数
	 */
	public int count;

	/** 
	 * 查询关键字 keyword
	 */
	public String k = null;
	
	/**
	 * 是否属于案例展示的图片（首页12个最新效果图的动态点击后进入主案例的首页）
	 */
	public int cases;
	
}
