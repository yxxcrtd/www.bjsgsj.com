package com.bjsgsj.site.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bjsgsj.site.dao.PictureDao;
import com.bjsgsj.site.param.PictureQueryParam;
import com.bjsgsj.site.param.base.QueryHelper;
import com.bjsgsj.site.pojos.Picture;
import com.bjsgsj.util.Pager;

/**
 * 图片DAO的实现
 * 
 * @author Yang XinXin
 * @version 1.0.0, 2011-07-10 17:35:17
 */
@SuppressWarnings("unchecked")
public class PictureDaoHibernate extends HibernateDaoSupport implements PictureDao {
	
	/**
	 * 获取首页精品展示列表
	 */
//	private static final String LOAD_SHOW_LIST = "FROM Picture WHERE show IS TRUE ORDER BY pictureId DESC";
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#getById(int)
	 */
	@Override
	public Picture getById(int id) {
		return (Picture) this.getHibernateTemplate().get(Picture.class, id);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#getPictureList(com.bjsgsj.site.param.PictureQueryParam, com.bjsgsj.util.Pager)
	 */
	@Override
	public List<Picture> getPictureList(PictureQueryParam param, Pager pager) {
		QueryHelper queryHelper = param.createQuery(false);
		if (null == pager) {
			return queryHelper.queryData(this.getHibernateTemplate(), -1, param.count);
		} else {
			return queryHelper.queryDataAndTotalCount(this.getHibernateTemplate(), pager);
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#getPictureList(com.bjsgsj.site.param.PictureQueryParam)
	 */
	@Override
	public List<Picture> getPictureList(PictureQueryParam param) {
		QueryHelper query = param.createQuery(true);
		return query.queryData(this.getHibernateTemplate(), 0, 12);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#getShowList()
	 */
//	@Override
//	public List<Picture> getShowList() {
//		return this.getHibernateTemplate().find(LOAD_SHOW_LIST);
//	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#save(com.bjsgsj.site.pojos.Picture)
	 */
	@Override
	public void save(Picture picture) {
		this.getHibernateTemplate().save(picture);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#update(com.bjsgsj.site.pojos.Picture)
	 */
	@Override
	public void update(Picture picture) {
		this.getHibernateTemplate().update(picture);
	}
	
	/* (non-Javadoc)
	 * 
	 * @see com.bjsgsj.site.dao.PictureDao#delete(com.bjsgsj.site.pojos.Picture)
	 */
	@Override
	public void delete(Picture picture) {
		this.getHibernateTemplate().delete(picture);
	}
	
	@Override
	public List<Picture> getPictureListByProjectId(String projectId) {
		return this.getHibernateTemplate().find("FROM Picture WHERE cases IS TRUE AND projectId in (" + projectId + ") ORDER BY pictureId DESC");
	}
	
	@Override
	public List<Picture> getAllPictureListByProjectId(String projectId) {
		return this.getHibernateTemplate().find("FROM Picture WHERE projectId in (" + projectId + ") ORDER BY pictureId ASC");
	}

}
