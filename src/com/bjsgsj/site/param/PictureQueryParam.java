package com.bjsgsj.site.param;

import com.bjsgsj.site.param.base.BaseQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.param.base.QueryParam;

/**
 * 菜单的查询参数
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-08-02 21:57:10
 */
public class PictureQueryParam extends BaseQueryParam implements QueryParam {
	
	public QueryHelper createQuery(boolean bol) {		
		QueryHelper query = new QueryHelper();
		query.fromClause = "FROM Picture p";
		query.orderClause = "ORDER BY p.pictureId DESC";
		
		// 查询关键字
		if (null != k && k.length() > 0) {
			query.addAndWhere("(describe LIKE :likeKey)");
			query.setString("likeKey", "%" + k + "%");
		}
		
		// 显示首页案例展示
		if (1 == cases) {
			query.addAndWhere("(cases IS TRUE)");
			// 按照最新的数据排列
			query.orderClause = "ORDER BY p.pictureId DESC";
		}
		
		// 返回
		return query;
	}
	
}
