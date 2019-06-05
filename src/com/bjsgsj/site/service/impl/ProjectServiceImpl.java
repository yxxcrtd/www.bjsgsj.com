package com.bjsgsj.site.service.impl;

import java.util.List;

import com.bjsgsj.site.param.ProjectQueryParam;
import com.bjsgsj.site.pojos.Project;
import com.bjsgsj.site.service.ProjectService;
import com.bjsgsj.util.Pager;

/**
 * 菜单服务的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:28
 */
public class ProjectServiceImpl extends BaseServiceImpl implements ProjectService {
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getById(int)
	 */
	@Override
	public Project getById(int id) {
		return projectDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getProjectList(com.bjsgsj.site.param.ProjectQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Project> getProjectList(ProjectQueryParam param, Pager pager) {
		return projectDao.getProjectList(param, pager);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getProjectList(com.bjsgsj.site.param.ProjectQueryParam)
	 */
	@Override
	public List<Project> getProjectList(ProjectQueryParam param) {
		return projectDao.getProjectList(param);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#saveOrUpdate(com.bjsgsj.site.pojos.Project)
	 */
	@Override
	public void saveOrUpdate(Project project) {
		projectDao.saveOrUpdate(project);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getMenuList()
	 */
//	@Override
//	public List<Project> getMenuList() {
//		return projectDao.getMenuList();
//	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getProjectNameList()
	 */
	@Override
	public List<Project> getProjectNameList() {
		return projectDao.getProjectNameList();
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.ProjectService#getCategoryList(java.lang.String)
	 */
	@Override
	public List<Project> getCategoryList(String category) {
		return projectDao.getCategoryList(category);
	}

}
