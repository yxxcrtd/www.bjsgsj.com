package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;
import com.bjsgsj.site.param.PictureQueryParam;
import com.bjsgsj.site.pojos.Picture;

/**
 * 图片管理
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:28:19
 */
public class PictureAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3918802259306664366L;

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
		} else if ("del".equals(cmd)) {
			return del();
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
		PictureQueryParam param = new PictureQueryParam();
		if (null == k || "".equals(k) || k.length() == 0) {
			// Ignore
		} else {
			k = k.trim();
			param.k = k;
		}
		pictureList = pictureService.getPictureList(param, pager);
		return SUCCESS;
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		if (null == picture) {
			picture = new Picture();
		} else {
			picture = pictureService.getById(picture.getPictureId());
		}
		projectList = projectService.getProjectNameList();
		return EDIT_SUCCESS;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (0 == picture.getPictureId()) {
			upload();
		} else {
			pictureService.update(picture);
		}
		return list();
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	private String del() throws Exception {
		pictureService.delete(picture.getPictureId());
		return list();
	}

}
