package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;
import com.bjsgsj.site.param.MessageQueryParam;
import com.bjsgsj.site.param.PictureQueryParam;
import com.bjsgsj.site.param.ProjectQueryParam;
import com.bjsgsj.site.pojos.Project;

/**
 * 站点管理
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:28:19
 */
public class SiteAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2621974224452076698L;

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {
		// 显示导航菜单列表
		// menuList = projectService.getMenuList();
		// map.put("menuList", menuList);
		map.put("cmd", cmd);
		
		if ("index".equals(cmd)) {
			return index();
		} else if ("company".equals(cmd)) {
			return company();
		} else if ("villa".equals(cmd) || "equipment".equals(cmd) || "home".equals(cmd) || "garden".equals(cmd) || "decoration".equals(cmd)) {
			return project();
		} else if ("message".equals(cmd)) {
			return message();
		} else {
			return this.unknownCommand(cmd);
		}
	}
	
	/**
	 * 生成网站首页
	 * 
	 * @return
	 * @throws Exception
	 */
	private String index() throws Exception {
		// 首页精品展示
		// showList = pictureService.getShowList();
		// map.put("showList", showList);
		
		// 首页12个新闻动态(最新效果图)
		PictureQueryParam pictureParam = new PictureQueryParam();
		
		// 只显示推荐到案例展示的图片
		pictureParam.cases = 1;
		pictureList = pictureService.getPictureList(pictureParam);
		map.put("pictureList", pictureList);
		
		// 首页案例分类展示
		ProjectQueryParam param = new ProjectQueryParam();
		projectList = projectService.getProjectList(param);
		map.put("projectList", projectList);
		
		// 将五大模块的数据(条件：图片是否首页案例展示)分别提供给页面
		putListToMap("villa");
		putListToMap("equipment");
		putListToMap("home");
		putListToMap("garden");
		putListToMap("decoration");
		
		siteService.generateHTML("WEB-INF/ftl/site/", "Index.ftl", "index.html", map);
		logger.info("首页生成成功！");
		
		return INDEX_SUCCESS;
	}
	
	/**
	 * 生成公司介绍
	 * 
	 * @return
	 * @throws Exception
	 */
	private String company() throws Exception {
		siteService.generateHTML("WEB-INF/ftl/site/", "Company.ftl", "company.html", map);
		logger.info("公司介绍生成成功！");
		return COMPANY_SUCCESS;
	}
	
	/**
	 * 系统五大项目（villa：中式别墅，equipment：中式公装，home：中式家装，garden：古建园林，decoration：中式配饰）
	 * 
	 * @return
	 * @throws Exception
	 */
	private String project() throws Exception {		
		// 左边的项目菜单列表
		projectList = projectService.getCategoryList(cmd);
		map.put("projectList", projectList);
		
		// 获取所有属于别墅的ID
		String s = "";
		for (int i = 0; i < projectList.size(); i++) {
			Project p = projectList.get(i);
			s += "'" + p.getProjectId() + "',";
		}
		
		// 右边的默认展示图片（与首页上的'精选案例展示'相同）
		pictureList = pictureService.getPictureListByProjectId(s.substring(0, s.length() - 1));
		map.put("pictureList", pictureList);
		
		// 所有图片
		allPictureList = pictureService.getAllPictureListByProjectId(s.substring(0, s.length() - 1));
		map.put("allPictureList", allPictureList);
		
		siteService.generateHTML("WEB-INF/ftl/site/", "Project.ftl", cmd + ".html", map);
		logger.info(cmd + " 生成成功！");
		return PROJECT_SUCCESS;
	}
	
	/**
	 * 生成在线留言
	 * 
	 * @return
	 * @throws Exception
	 */
	private String message() throws Exception {
		// 留言列表
		MessageQueryParam param = new MessageQueryParam();
		messageList = messageService.getMessageList(param);
		map.put("messageList", messageList);
		
		siteService.generateHTML("WEB-INF/ftl/site/", "Message.ftl", "message.html", map);
		logger.info("在线留言生成成功！");
		return MESSAGE_SUCCESS;
	}

}
