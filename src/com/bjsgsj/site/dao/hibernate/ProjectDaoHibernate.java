package com.bjsgsj.site.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsgsj.site.dao.ProjectDao;
import com.bjsgsj.site.param.ProjectQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.pojos.Project;
import com.bjsgsj.util.Pager;

/**
 * 菜单DAO的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:17
 */
@SuppressWarnings("unchecked")
public class ProjectDaoHibernate extends HibernateDaoSupport implements ProjectDao {
	
	/**
	 * 显示菜单列表
	 */
	// private static final String LOAD_MENU_LIST = "FROM Project WHERE (projectId < 9) ORDER BY projectId";
	
	/**
	 * 获取所有项目名称列表
	 */
	private static final String LOAD_PROJECTNAME_LIST = "FROM Project ORDER BY projectId DESC";
	
	/**
	 * 根据条件获取项目中的所有分类
	 */
	private static final String LOAD_CATEGORY_LIST = "FROM Project WHERE (parent = ?) ORDER BY projectId";
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getById(int)
	 */
	@Override
	public Project getById(int id) {
		return (Project) this.getHibernateTemplate().get(Project.class, id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getProjectList(com.bjsgsj.site.param.ProjectQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Project> getProjectList(ProjectQueryParam param, Pager pager) {
		QueryHelper queryHelper = param.createQuery(false);
		if (null == pager) {
			return queryHelper.queryData(this.getHibernateTemplate(), -1, param.count);
		} else {
			return queryHelper.queryDataAndTotalCount(this.getHibernateTemplate(), pager);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getProjectList(com.bjsgsj.site.param.ProjectQueryParam)
	 */
	@Override
	public List<Project> getProjectList(ProjectQueryParam param) {
		QueryHelper query = param.createQuery(true);
		return query.queryData(this.getHibernateTemplate(), 0, 8);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#saveOrUpdate(com.bjsgsj.site.pojos.Project)
	 */
	@Override
	public void saveOrUpdate(Project project) {
		this.getHibernateTemplate().saveOrUpdate(project);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getMenuList()
	 */
//	@Override
//	public List<Project> getMenuList() {
//		return this.getHibernateTemplate().find(LOAD_MENU_LIST);		
//	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getProjectNameList()
	 */
	@Override
	public List<Project> getProjectNameList() {
		return this.getHibernateTemplate().find(LOAD_PROJECTNAME_LIST);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.ProjectDao#getCategoryList(java.lang.String)
	 */
	@Override
	public List<Project> getCategoryList(String category) {
		return this.getHibernateTemplate().find(LOAD_CATEGORY_LIST, category);
	}

}
