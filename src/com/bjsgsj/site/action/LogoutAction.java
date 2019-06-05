package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;

/**
 * 
 * 注销
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-7-10 上午11:45:49
 */
public class LogoutAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2745992655595951477L;

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {

		// 1，清理：Session
		cleanSession();
		
		logger.info("注销成功！");

		// 3，返回
		return SUCCESS;
	}

}
