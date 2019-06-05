package com.bjsgsj.site.service;

import java.util.Map;

/**
 * 站点服务
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-03-07 20:03:22
 */
public interface SiteService {
	
	/**
	 * 生成静态的HTML文件
	 * 
	 * @param folderName 模板加载的父目录
	 * @param ftl 模板文件
	 * @param htmlName 最后生成的文件名称
	 * @param map 数据
	 */
	public void generateHTML(String folderName, String ftl, String htmlName, Map<String, Object> map);
	
}
