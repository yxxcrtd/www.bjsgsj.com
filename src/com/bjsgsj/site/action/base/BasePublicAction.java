package com.bjsgsj.site.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjsgsj.site.interceptor.LoginInterceptor;
import com.bjsgsj.site.pojos.Message;
import com.bjsgsj.site.pojos.Picture;
import com.bjsgsj.site.pojos.Project;
import com.bjsgsj.site.pojos.User;
import com.bjsgsj.site.service.MessageService;
import com.bjsgsj.site.service.PictureService;
import com.bjsgsj.site.service.ProjectService;
import com.bjsgsj.site.service.SiteService;
import com.bjsgsj.site.service.UserService;
import com.bjsgsj.util.CommonUtil;
import com.bjsgsj.util.MD5;

/**
 * Action的第一个基类
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 11:00:01
 */
public abstract class BasePublicAction extends BaseAbstractAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5335746248415322312L;

	/**
	 * 用户对象
	 */
	protected User user;

	/**
	 * 在线留言对象
	 */
	protected Message message;

	/**
	 * 菜单对象
	 */
	protected Project project;

	/**
	 * 图片对象
	 */
	protected Picture picture;

	/**
	 * 在线留言列表
	 */
	protected List<Message> messageList = new ArrayList<Message>();

	/**
	 * 菜单列表
	 */
	protected List<Project> projectList = new ArrayList<Project>();

	/**
	 * 图片列表
	 */
	protected List<Picture> pictureList = new ArrayList<Picture>();

	/**
	 * 所有图片列表
	 */
	protected List<Picture> allPictureList = new ArrayList<Picture>();

	/**
	 * 导航菜单列表
	 */
	protected List<Project> menuList = new ArrayList<Project>();

	/**
	 * 首页精品展示列表
	 */
	protected List<Picture> showList = new ArrayList<Picture>();
	
	/**
	 * 8个精选案例展示列表
	 */
	protected List<Picture> caseList = new ArrayList<Picture>();

	/**
	 * 用户服务
	 */
	protected UserService userService;

	/**
	 * 在线留言服务
	 */
	protected MessageService messageService;

	/**
	 * 菜单服务
	 */
	protected ProjectService projectService;

	/**
	 * 图片服务
	 */
	protected PictureService pictureService;
	
	/**
	 * 站点服务
	 */
	protected SiteService siteService;

	/**
	 * 文件的 name （是<input type="file" name="file" ...>中的 name）
	 */
	protected List<File> file;

	/**
	 * 文件名（由属性 file + FileName 固定组成）
	 */
	protected List<String> fileFileName;

	/**
	 * 文件类型（由属性 file + ContentType 固定组成）
	 */
	protected List<String> fileContentType;

	/**
	 * 在线留言的页面验证码
	 */
	protected String verifyCode;
	
	/**
	 * map
	 */
	protected Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 检验验证码是否相同
	 * 
	 * @param sessionVerifyCode Session中的验证码
	 * @param verifyCode 用户页面中输入的验证码
	 * @return
	 */
	protected Boolean isValidVerifyCode(String sessionVerifyCode, String verifyCode) {
		logger.info("Session中的验证码：" + sessionVerifyCode);
		logger.info("用户输入的验证码：" + verifyCode);

		// 比较
		if (CommonUtil.stringEqualsIgnoreCase(sessionVerifyCode, verifyCode)) {
			return true;
		} else {
			this.addActionError(this.getText("system.login.verifyCode.incorrect"));
			return false;
		}
	}

	/**
	 * 根据用户名得到用户对象
	 * 
	 * @param username
	 * @return
	 */
	protected User getUserByUsername(String username) {
		logger.info("用户输入的用户名：" + username);

		// 获取用户对象
		user = userService.getUserByUsername(MD5.toMD5(username));
		logger.info("用户对象：" + user);

		// 如果对象为空，显示通用的提示信息(请输入正确的用户名和密码！)
		if (null == user) {
			this.addActionError(this.getText("system.login.username.not.exist"));
		}
		return user;
	}

	/**
	 * 验证密码是否相等
	 * 
	 * @param dbPassword 数据库中的MD5密码
	 * @param inputPassword 用户页面中输入的密码
	 * @return
	 */
	protected Boolean equalPassword(String dbPassword, String inputPassword) {
		if (CommonUtil.stringEquals(dbPassword, MD5.toMD5(inputPassword))) {
			return true;
		} else {
			// 密码错误，显示通用的提示信息(请输入正确的用户名和密码！)
			this.addActionError(this.getText("system.login.password.incorrect"));
			return false;
		}
	}

	/**
	 * 在服务器端写 Session
	 */
	protected void writeSessionInServer() {
		// 将用户对象放到登录拦截器中
		session.put(LoginInterceptor.USER_SESSION_KEY, user);
	}

	/**
	 * 清理：Session
	 */
	protected void cleanSession() {
		session.put(LoginInterceptor.USER_SESSION_KEY, null);
		session.put("returnUrl", null);
	}

	/**
	 * 用户对象的get方法
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 用户对象的set方法
	 * 
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 在线留言对象的get方法
	 * 
	 * @return
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * 在线留言对象的set方法
	 * 
	 * @param message
	 */
	public void setMessage(Message message) {
		this.message = message;
	}

	/**
	 * 菜单对象的get方法
	 * 
	 * @return
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * 菜单对象的set方法
	 * 
	 * @param project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * 图片对象的get方法
	 * 
	 * @return
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * 图片对象的set方法
	 * 
	 * @param picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * 在线留言列表的get方法
	 * 
	 * @return
	 */
	public List<Message> getMessageList() {
		return messageList;
	}

	/**
	 * 菜单列表的get方法
	 * 
	 * @return
	 */
	public List<Project> getProjectList() {
		return projectList;
	}

	/**
	 * 图片列表的get方法
	 * 
	 * @return
	 */
	public List<Picture> getPictureList() {
		return pictureList;
	}
	
	/**
	 * 所有图片列表的get方法
	 * 
	 * @return
	 */
	public List<Picture> getAllPictureList() {
		return allPictureList;
	}
	
	/**
	 * 导航菜单列表的get方法
	 * 
	 * @return
	 */
	public List<Project> getMenuList() {
		return menuList;
	}

	/**
	 * 首页精品展示列表的get方法
	 * 
	 * @return
	 */
	public List<Picture> getShowList() {
		return showList;
	}

	/**
	 * 8个精选案例展示列表的get方法
	 * 
	 * @return
	 */
	public List<Picture> getCaseList() {
		return caseList;
	}

	/**
	 * 用户服务的set方法
	 * 
	 * @param userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 在线留言服务的set方法
	 * 
	 * @param messageService
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 菜单服务的set方法
	 * 
	 * @param projectService
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	/**
	 * 图片服务的set方法
	 * 
	 * @param pictureService
	 */
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	/**
	 * 站点服务的set方法
	 * 
	 * @param siteService
	 */
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	/**
	 * 上传文件 的 get 方法
	 * 
	 * @return
	 */
	public List<File> getFile() {
		return file;
	}

	/**
	 * 上传文件 的 set 方法
	 * 
	 * @param file
	 */
	public void setFile(List<File> file) {
		this.file = file;
	}

	/**
	 * 上传文件的文件名 的 get 方法
	 * 
	 * @return
	 */
	public List<String> getFileFileName() {
		return fileFileName;
	}

	/**
	 * 上传文件的文件名 的 set 方法
	 * 
	 * @param fileFileName
	 */
	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * 上传文件的文件类型 的 get 方法
	 * 
	 * @return
	 */
	public List<String> getFileContentType() {
		return fileContentType;
	}

	/**
	 * 上传文件的文件类型 的 set 方法
	 * 
	 * @param fileContentType
	 */
	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * 验证码的set方法
	 * 
	 * @param verifyCode
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 批量上传
	 */
	protected void upload() {
		for (int i = 0; i < file.size(); ++i) {
			// 存储到磁盘上和数据库中的文件名规则：年月日时分秒毫秒.文件后缀：20110801121212123.jpeg
			String fileName = new StringBuffer().append(String.valueOf(String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", new Date()))).append(".").append(fileFileName.get(i).substring(fileFileName.get(i).lastIndexOf(".") + 1, fileFileName.get(i).length()).toLowerCase()).toString();
			logger.info("存储到磁盘上和数据库中的文件名：" + fileName);

			InputStream is = null;
			OutputStream os = null;

			// 上传目录
			String root = new StringBuffer().append(servletContext.getRealPath("/")).append("upload").append(File.separator).toString();
			logger.info("图片上传的根路径：" + root);

			// 最后上传的文件
			File destFile = new File(root, fileName);
			logger.info("最后上传的文件：" + destFile);

			try {
				is = new FileInputStream(file.get(i));
				os = new FileOutputStream(destFile);

				// 缓冲区大小，控制一次读512个字节，减少I/O操作，提高读取效率
				byte[] buffer = new byte[512];

				int length = 0;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}

				// 生成缩略图（参数：原图片文件，生成的目标缩略图文件，宽度，高度）其中：n代表：本栏目首页；c代表：首页案例部分
				CommonUtil.generateHighQulityThumbnail(destFile.toString(), destFile.getParentFile() + File.separator + "n_" + fileName, 720, 540);
				CommonUtil.generateHighQulityThumbnail(destFile.toString(), destFile.getParentFile() + File.separator + "c_" + fileName, 245, 150);

				// 关闭输入输出流
				is.close();
				os.flush();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			picture.setLink(fileName);
			pictureService.save(picture);
		}
	}
	
	/**
	 * 将List数据放入Map中，供页面使用
	 * 
	 * @param obj 放入的对象
	 */
	protected void putListToMap(String obj) {
		String s = "";
		for (int i = 0; i < projectService.getCategoryList(obj).size(); i++) {
			Project p = projectService.getCategoryList(obj).get(i);
			s += "'" + p.getProjectId() + "',";
		}
		pictureList = pictureService.getPictureListByProjectId(s.substring(0, s.length() - 1));
		map.put(obj, pictureList);
	}

}
