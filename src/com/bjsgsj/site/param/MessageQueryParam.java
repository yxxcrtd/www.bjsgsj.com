package com.bjsgsj.site.param;

import com.bjsgsj.site.param.base.BaseQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.param.base.QueryParam;

/**
 * 留言的查询参数
 * 
 * @author Yang XinXin
 * @version 3.0.0, 2008-03-02 13:30:12
 */
public class MessageQueryParam extends BaseQueryParam implements QueryParam {
	
	public QueryHelper createQuery(boolean bol) {		
		QueryHelper query = new QueryHelper();
		query.fromClause = "FROM Message";
		query.orderClause = "ORDER BY messageId DESC";
		
		// 查询关键字
		if (null != k && k.length() > 0) {
			query.addAndWhere("(name LIKE :likeKey) OR (telephone LIKE :likeKey) OR (content LIKE :likeKey) OR (reply LIKE :likeKey)");
			query.setString("likeKey", "%" + k + "%");
		}
		
		// 返回
		return query;
	}
	
}
