package com.bjsgsj.util;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;

/**
 * QueryHelper 等辅助类的基类。其提供命名参数的记忆功能
 * 
 * @author Yang Xinxin
 * @version 1.0.0 Apr 10, 2008 1:45:19 PM
 */
public abstract class HqlHelperBase {
	
	/** 
	 * 查询条件记忆容器
	 */
	private ArrayList<QueryParam> query_param = new ArrayList<QueryParam>();
	
	/**
	 * 记忆一个字符串参数
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public void setString(String paramName, String paramValue) {
		QueryParam p = new QueryParam(paramName, "setString", paramValue);
		query_param.add(p);
	}
	
	/**
	 * 记忆一个布尔型参数
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public void setBoolean(String paramName, boolean paramValue) {
		QueryParam p = new QueryParam(paramName, "setBoolean", paramValue);
		query_param.add(p);
	}
	
	/**
	 * 记忆一个整数参数
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public void setInteger(String paramName, int paramValue) {
		QueryParam p = new QueryParam(paramName, "setInteger", paramValue);
		query_param.add(p);
	}
	
	/**
	 * 记忆一个日期型参数
	 *
	 * @param paramName
	 * @param paramValue
	 */
	public void setDate(String paramName, Date paramValue) {
		QueryParam p = new QueryParam(paramName, "setDate", paramValue);
		query_param.add(p);
	}
	
	/**
	 * 记忆一个日期型参数2, 采用 setTimestamp 方法
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public void setTimestamp(String paramName, Date paramValue) {
		QueryParam p = new QueryParam(paramName, "setTimestamp", paramValue);
		query_param.add(p);
	}

	/**
	 * 根据记忆中的查询条件，初始化 query
	 *
	 * @param query
	 */
	protected void initQuery(Query query) {
		for (int i = 0; i < query_param.size(); ++i)
			query_param.get(i).setParam(query);
	}
	
	/**
	 * 查询参数记录
	 */
	private static class QueryParam {
		/**
		 * 参数名字
		 */
		public String paramName; 
		
		/**
		 * 设置器名字，如 setString, setInt, setBoolean
		 */
		public String setterName; 
		
		/**
		 * 参数值
		 */
		public Object paramValue; 
		/**
		 * Default Constructor
		 */
		@SuppressWarnings("unused")
		public QueryParam() {
			// 
		}
		
		public QueryParam(String paramName, String setterName, Object paramValue) { 
			this.paramName = paramName;
			this.setterName = setterName;
			this.paramValue = paramValue;
		}
		
		public void setParam(Query query) {
			if ("setString".equals(setterName))
				query.setString(paramName, (String)paramValue);
			else if ("setInteger".equals(setterName))
				query.setInteger(paramName, (Integer)paramValue);
			else if ("setDate".equals(setterName))
				query.setDate(paramName, (Date)paramValue);
			else if ("setBoolean".equals(setterName))
				query.setBoolean(paramName, (Boolean)paramValue);
			else if ("setTimestamp".equals(setterName))
				query.setTimestamp(paramName, (Date)paramValue);
			else
				query.setParameter(paramName, paramValue);
		}
	}

}
