package com.bjsgsj.site.param;

import com.bjsgsj.site.param.base.BaseQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.param.base.QueryParam;

/**
 * 项目的查询参数
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-08-02 21:57:10
 */
public class ProjectQueryParam extends BaseQueryParam implements QueryParam {
	
	public QueryHelper createQuery(boolean bol) {		
		QueryHelper query = new QueryHelper();
		query.fromClause = "FROM Project";
		query.orderClause = "ORDER BY projectId DESC";
//		query.addAndWhere("projectId > 8"); // parent <> " + "'/'（不等于还可以用：!=）
		
		// 查询关键字
		if (null != k && k.length() > 0) {
			query.addAndWhere("(name LIKE :likeKey) OR (describe LIKE :likeKey)");
			query.setString("likeKey", "%" + k + "%");
		}
		
		// 返回
		return query;
	}
	
}
