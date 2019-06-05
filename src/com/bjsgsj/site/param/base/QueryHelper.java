package com.bjsgsj.site.param.base;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.bjsgsj.util.HqlHelperBase;
import com.bjsgsj.util.Pager;

/**
 * Hibernate + Spring HQL 查询辅助工具
 * 
 * @author Yang XinXin
 * @version , 2008-03-02 10:06:39
 */
@SuppressWarnings("rawtypes")
public class QueryHelper extends HqlHelperBase {
	
	/** 
	 * SELECT 子句
	 */
	public String selectClause = "";

	/** 
	 * FROM 子句
	 */
	public String fromClause = "";

	/** 
	 * WHERE 子句
	 */
	public String whereClause = "";

	/** 
	 * ORDER BY 子句
	 */
	public String orderClause = "";

	/** 
	 * GROUP BY 子句
	 */
	public String groupbyClause = "";

	/** 
	 * HAVING 子句
	 */
	public String havingClause = "";

	/**
	 * 添加一个 whereClause 的与(and)条件
	 * 
	 * @param condition
	 */
	public QueryHelper addAndWhere(String condition) {
		if (this.whereClause.length() == 0)
			this.whereClause = " WHERE (" + condition + ")";
		else
			this.whereClause += " AND (" + condition + ")";
		return this;
	}

	/**
	 * 添加一个 orderClause 的排序方式
	 * 
	 * @param order 排序的子条件，如 'id ASC'
	 */
	public QueryHelper addOrder(String order) {
		if (this.orderClause.length() == 0)
			this.orderClause = " ORDER BY " + order;
		else
			this.orderClause += ", " + order;
		return this;
	}

	/**
	 * 查询记录总数。查询语句为："SELECT COUNT(*) " + fromClause + " " + whereClause
	 * 
	 * @param hiber
	 * @return
	 */
	public int queryTotalCount(HibernateTemplate hiber) {
		final String hql = "SELECT COUNT(*) " + this.fromClause + " " + this.whereClause;
		Object result = hiber.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				initQuery(query);
				return query.uniqueResult();
			}
		});
		return safeGetIntResult(result);
	}

	/**
	 * 查询项目总数并直接将结果放到 pager 中
	 * 
	 * @param hiber
	 * @param pager
	 */
	public void queryTotalCount(HibernateTemplate hiber, Pager pager) {
		int tc = queryTotalCount(hiber);
		pager.setTotalRows(tc);
	}

	/**
	 * 查询项目列表
	 */
	public List queryData(HibernateTemplate hiber) {
		return queryData(hiber, -1, -1);
	}

	/**
	 * 执行当前查询，并返回第一条记录
	 * 
	 * @param hiber
	 * @return
	 */
	public Object querySingleData(HibernateTemplate hiber) {
		List list = queryData(hiber, 0, 1);
		if (null == list || list.size() == 0)
			return null;
		return list.get(0);
	}

	/**
	 * 执行当前查询，并返回一个整数值，如果为 null 则返回 0
	 * 
	 * @param hiber
	 * @return
	 */
	public int queryIntValue(HibernateTemplate hiber) {
		List list = queryData(hiber, 0, 1);
		if (null == list || list.size() == 0)
			return 0;
		Object v = list.get(0);
		if (null == v)
			return 0;
		if (v instanceof Number)
			return ((Number) v).intValue();
		return 0;
	}

	/**
	 * 查询项目列表. 查询语句为：selectClause + fromClause + whereClause + orderClause
	 * 
	 * @param hiber
	 * @param first_result Set the first row to retrieve. -1 means not set, rows will be retrieved beginnning from row 0.
	 * @param max_results Set the maximum number of rows to retrieve. -1 means not set, there is no limit to the number of rows retrieved.
	 */
	public List queryData(HibernateTemplate hiber, final int first_result, final int max_results) {
		final String hql = this.selectClause + " " + this.fromClause + " "
				+ this.whereClause + " "
				+ this.groupbyClause + " " 
				+ this.orderClause + " "
				+ this.havingClause;
		// 对于PostgreSQL数据库，需要将groupbyClause放到orderClause前

		return hiber.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				initQuery(query);
				if (first_result != -1)
					query.setFirstResult(first_result);
				if (max_results != -1)
					query.setMaxResults(max_results);
				return query.list();
			}
		});
	}

	/**
	 * 查询项目列表
	 *
	 * @param hiber
	 * @param page_info
	 * @return
	 */
	public List queryData(HibernateTemplate hiber, final Pager page_info) {
		return queryData(hiber, (page_info.getCurrentPage() - 1) * page_info.getPageSize(), page_info.getPageSize());
	}

	/**
	 * 查询数量及数据
	 * 
	 * @return
	 */
	public List queryDataAndTotalCount(HibernateTemplate hiber, Pager pager) {
		int tc = this.queryTotalCount(hiber);
		pager.setTotalRows(tc);
		return this.queryData(hiber, pager);
	}

	/**
	 * 得到整型结果
	 *
	 * @param v
	 * @return
	 */
	private static final int safeGetIntResult(Object v) {
		if (null == v)
			return 0;
		if (v instanceof Integer)
			return (Integer) v;
		if (v instanceof Number)
			return ((Number) v).intValue();
		return 0;
	}
	
}
