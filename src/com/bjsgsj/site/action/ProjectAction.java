package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;
import com.bjsgsj.site.param.ProjectQueryParam;
import com.bjsgsj.site.pojos.Project;

/**
 * 菜单管理
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:28:19
 */
public class ProjectAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6267541672794407769L;

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {
		if ("list".equals(cmd)) {
			return list();
		} else if ("edit".equals(cmd)) {
			return edit();
		} else if ("save".equals(cmd)) {
			return save();
		} else {
			return this.unknownCommand(cmd);
		}
	}
	
	/**
	 * 列表
	 * 
	 * @return
	 * @throws Exception
	 */
	private String list() throws Exception {
		ProjectQueryParam param = new ProjectQueryParam();
		if (null == k || "".equals(k) || k.length() == 0) {
			// Ignore
		} else {
			k = k.trim();
			param.k = k;
		}
		projectList = projectService.getProjectList(param, pager);
		return SUCCESS;
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		if (null == project) {
			project = new Project();
		} else {
			project = projectService.getById(project.getProjectId());
		}
		return EDIT_SUCCESS;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		project.setDescribe(project.getDescribe().replaceAll("\n", "<br />"));
		projectService.saveOrUpdate(project);
		return list();
	}

}
