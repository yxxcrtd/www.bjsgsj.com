package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;

/**
 * 用户登录
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-07 06:17:12
 */
public class LoginAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 739389517157772704L;

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {
		returnUrl = (String) session.get("returnUrl");
		logger.info("从 session 得到的 returnUrl： " + returnUrl);
		
		// 用户在页面中输入的密码
		String password = user.getPassword();
		
		// 1，检验验证码
		if (!isValidVerifyCode(sessionVerifyCode, user.getVerifyCode())) {
			return INPUT;
		}
		
		// 1，根据登录名/邮件地址获得用户对象
		if (null == this.getUserByUsername(user.getUsername())) {
			return INPUT;
		}
		
		// 2，验证用户密码
		if (!equalPassword(user.getPassword(), password)) {
			return INPUT;
		}
		
		// 3，在服务器端写 Session
		writeSessionInServer();

		// 4，返回
		return SUCCESS;
	}
	
}
