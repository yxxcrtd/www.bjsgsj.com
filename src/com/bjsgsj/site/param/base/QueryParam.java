package com.bjsgsj.site.param.base;

/**
 * 查询参数 接口
 * 
 * @author Yang XinXin
 * @version 3.0.0, 2009-10-20 19:18:19
 */
public interface QueryParam {

	/**
	 * 通过此条件创建出对应查询
	 * 
	 * @param bol
	 * @return
	 */
	public QueryHelper createQuery(boolean bol);

}
