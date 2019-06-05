package com.bjsgsj.site.dao;

import java.util.List;

import com.bjsgsj.site.param.ProjectQueryParam;
import com.bjsgsj.site.pojos.Project;
import com.bjsgsj.util.Pager;

/**
 * 菜单DAO
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:23
 */
public interface ProjectDao {

	/**
	 * 根据主键标识获得对象
	 * 
	 * @param id
	 * @return
	 */
	public Project getById(int id);
	
	/**
	 * 得到带分页的菜单列表
	 * 
	 * @param param
	 * @param pager
	 * @return
	 */
	public List<Project> getProjectList(ProjectQueryParam param, Pager pager);
	
	/**
	 * 获取有条件的项目名称列表
	 * 
	 * @param param
	 * @return
	 */
	public List<Project> getProjectList(ProjectQueryParam param);
	
	/**
	 * 保存或修改菜单
	 * 
	 * @param project
	 */
	public void saveOrUpdate(Project project);
	
	/**
	 * 显示导航菜单列表
	 * 
	 * @return
	 */
//	public List<Project> getMenuList();
	
	/**
	 * 获取所有项目名称列表
	 * 
	 * @return
	 */
	public List<Project> getProjectNameList();
	
	/**
	 * 根据条件获取项目中的所有分类
	 * 
	 * @param category
	 * @return
	 */
	public List<Project> getCategoryList(String category);
	
}
