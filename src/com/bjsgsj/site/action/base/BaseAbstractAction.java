package com.bjsgsj.site.action.base;

import java.io.PrintWriter;

import com.bjsgsj.util.ParamUtil;

/**
 * 所有 BaseAction 的 抽象基类
 *
 * @author Yang XinXin
 * @version 1.0.0, 2011-03-06 01:02:53
 */
public abstract class BaseAbstractAction extends BaseConstantAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6827178869063695845L;
	
	/**
	 * 响应的输出
	 */
	protected PrintWriter out;
	
	/* (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public final String execute() throws Exception {
		out = response.getWriter();
		
		String result = NONE;

		try {
			// 从session中取信息
			this.session = getActionContext().getSession();
			this.returnUrl = (String) session.get("returnUrl");
			
			this.sessionVerifyCode = (String) session.get("random");
			session.put("random", null);

			// 通过辅助参数得到数据
			this.paramUtil = new ParamUtil(getActionContext().getParameters());
			this.cmd = paramUtil.safeGetStringParam("cmd");
			this.k = paramUtil.safeGetStringParam("k");

			// 根据当前页面参数（page）构造一个缺省Pager对象			
			pager = paramUtil.createPager();
			// 以后其他的方法可以重写
			if (null == p || "".equals(p) || p.length() == 0) {
				pager.setPageSize(20);
			} else {
				pager.setPageSize(Integer.valueOf(p));
			}

			// 执行
			result = beforeExecute();
			if (null != result) {
				return result;
			}
			return execute(cmd);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 在调用派生类 execute() 之前被调用
	 * 
	 * @return 返回 null 表示成功，其它字符串认为是失败并作为 execute() 返回值处理
	 * @throws Exception
	 */
	protected String beforeExecute() throws Exception {
		return null;
	}

	/**
	 * 派生类重载此方法完成实际业务执行
	 * 
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	protected abstract String execute(String cmd) throws Exception;

}
