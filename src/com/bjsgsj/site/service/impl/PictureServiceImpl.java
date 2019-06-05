package com.bjsgsj.site.service.impl;

import java.io.File;
import java.util.List;

import com.bjsgsj.site.param.PictureQueryParam;
import com.bjsgsj.site.pojos.Picture;
import com.bjsgsj.site.service.PictureService;
import com.bjsgsj.util.Pager;

/**
 * 图片服务的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:28
 */
public class PictureServiceImpl extends BaseServiceImpl implements PictureService {

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#getById(int)
	 */
	@Override
	public Picture getById(int id) {
		return pictureDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#getPictureList(com.bjsgsj.site.param.PictureQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Picture> getPictureList(PictureQueryParam param, Pager pager) {
		return pictureDao.getPictureList(param, pager);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#getPictureList(com.bjsgsj.site.param.PictureQueryParam)
	 */
	@Override
	public List<Picture> getPictureList(PictureQueryParam param) {
		return pictureDao.getPictureList(param);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#getShowList()
	 */
//	@Override
//	public List<Picture> getShowList() {
//		return pictureDao.getShowList();
//	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#save(com.bjsgsj.site.pojos.Picture)
	 */
	@Override
	public void save(Picture picture) {
		pictureDao.save(picture);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#update(com.bjsgsj.site.pojos.Picture)
	 */
	@Override
	public void update(Picture picture) {
		pictureDao.update(picture);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.service.PictureService#delete(int)
	 */
	@Override
	public void delete(int pictureId) {
		// 图片对象
		Picture picture = pictureDao.getById(pictureId);

		// 删除数据库记录
		if (null != picture) {
			pictureDao.delete(picture);
			
			// 图片的物理地址
			String fileAddress = new StringBuffer().append(servletContext.getRealPath("/")).append("upload").append(File.separator).append(picture.getLink()).toString();
			String c_fileAddress = new StringBuffer().append(servletContext.getRealPath("/")).append("upload").append(File.separator).append("c_").append(picture.getLink()).toString();
			String n_fileAddress = new StringBuffer().append(servletContext.getRealPath("/")).append("upload").append(File.separator).append("n_").append(picture.getLink()).toString();
			String s_fileAddress = new StringBuffer().append(servletContext.getRealPath("/")).append("upload").append(File.separator).append("s_").append(picture.getLink()).toString();
			
			// 删除物理文件
			new File(fileAddress).delete();
			new File(c_fileAddress).delete();
			new File(n_fileAddress).delete();
			new File(s_fileAddress).delete();
		}
	}
	
	@Override
	public List<Picture> getPictureListByProjectId(String projectId) {
		return pictureDao.getPictureListByProjectId(projectId);
	}
	
	@Override
	public List<Picture> getAllPictureListByProjectId(String projectId) {
		return pictureDao.getAllPictureListByProjectId(projectId);
	}

}
