package com.bjsgsj.site.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.context.ServletContextAware;

import com.bjsgsj.site.service.SiteService;
import com.bjsgsj.util.CommonUtil;

import freemarker.template.TemplateException;

/**
 * 站点服务的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-06-10 15:05:01
 */
public class SiteServiceImpl extends BaseServiceImpl implements SiteService, ServletContextAware {

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.SiteService#generateHTML(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public void generateHTML(String folderName, String ftl, String htmlName, Map<String, Object> map) {
		try {
			CommonUtil.generateHTML(folderName, ftl, htmlName, map, servletContext);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

}
