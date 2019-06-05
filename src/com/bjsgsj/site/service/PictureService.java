package com.bjsgsj.site.service;

import java.util.List;

import com.bjsgsj.site.param.PictureQueryParam;
import com.bjsgsj.site.pojos.Picture;
import com.bjsgsj.util.Pager;

/**
 * 图片服务
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:31
 */
public interface PictureService {

	/**
	 * 根据主键标识获得对象
	 * 
	 * @param id
	 * @return
	 */
	public Picture getById(int id);
	
	/**
	 * 得到带分页的菜单列表
	 * 
	 * @param param
	 * @param pager
	 * @return
	 */
	public List<Picture> getPictureList(PictureQueryParam param, Pager pager);
	
	/**
	 * 获取首页12个最新效果图
	 * 
	 * @param param
	 * @return
	 */
	public List<Picture> getPictureList(PictureQueryParam param);
	
	/**
	 * 获取首页精品展示列表
	 * 
	 * @return
	 */
//	public List<Picture> getShowList();
	
	/**
	 * 保存图片
	 * 
	 * @param picture
	 */
	public void save(Picture picture);
	
	/**
	 * 修改图片
	 * 
	 * @param picture
	 */
	public void update(Picture picture);
	
	/**
	 * 
	 * 
	 * @param pictureId
	 */
	public void delete(int pictureId);
	
	public List<Picture> getPictureListByProjectId(String projectId);
	
	public List<Picture> getAllPictureListByProjectId(String projectId);

}
