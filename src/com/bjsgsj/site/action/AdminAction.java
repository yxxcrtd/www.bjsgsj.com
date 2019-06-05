package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;

/**
 * 后台管理
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-09 21:15:03
 */
public class AdminAction extends BasePublicAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8871999091443936480L;
	
	private double total;
	private double max;
	private double free;
	private double valid;

	public double getTotal() {
		return total;
	}

	public double getMax() {
		return max;
	}

	public double getFree() {
		return free;
	}

	public double getValid() {
		return valid;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.action.base.BaseAbstractAction#execute(java.lang.String)
	 */
	@Override
	protected String execute(String cmd) throws Exception {
		return SUCCESS;
	}

	/**
	 * 菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String menu() throws Exception {
		return "menu";
	}

	/**
	 * 中间切换线
	 * 
	 * @return
	 * @throws Exception
	 */
	public String line() throws Exception {
		return "line";
	}
	
	/**
	 * Main
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
		max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
		free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
		valid = max - total + free;
		return "main";
	}

}
