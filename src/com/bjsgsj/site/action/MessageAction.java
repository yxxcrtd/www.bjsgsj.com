package com.bjsgsj.site.action;

import com.bjsgsj.site.action.base.BasePublicAction;
import com.bjsgsj.site.param.MessageQueryParam;

/**
 * 在线留言管理
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:28:19
 */
public class MessageAction extends BasePublicAction {

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
		MessageQueryParam param = new MessageQueryParam();
		if (null == k || "".equals(k) || k.length() == 0) {
			// Ignore
		} else {
			k = k.trim();
			param.k = k;
		}
		pager.setPageSize(10);
		messageList = messageService.getMessageList(param, pager);
		return SUCCESS;
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		message = messageService.getById(message.getMessageId());
		return EDIT_SUCCESS;
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (0 == message.getMessageId()) {
			// 设置留言人的IP地址
			message.setIp(request.getRemoteAddr());
			message.setContent(message.getContent().replaceAll("\n", "<br />")); // Linux下可能是：\\n
			out.print(messageService.saveOrUpdate(message));
			out.flush();
			out.close();
			return NONE;
		} else {
			// 填写回复
			message.setReply(message.getReply().replaceAll("\n", "<br />"));
			messageService.saveOrUpdate(message);
			return list();
		}
	}
	
	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	private String del() throws Exception {
		messageService.delete(message.getMessageId());
		return list();
	}

}
