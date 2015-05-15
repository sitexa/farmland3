package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * LandPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.LandPicture
 */

public class LandPictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(LandPictureDAO.class);
    // property constants
    public static final String PIC_URL = "picUrl";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public void save(LandPicture transientInstance) {
        log.debug("saving LandPicture instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(LandPicture persistentInstance) {
        log.debug("deleting LandPicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void deleteByLand(Land land) {
        log.debug("deleting LandPicture by starLand");
        try {
        	String sql = "delete from t_land_picture where landId=?";
        	getSession().createSQLQuery(sql).setParameter(0, land.getLandId()).executeUpdate();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /**
     * @param persistentInstance
     * @author leim 2009.11.24
     * 更新图片记录
     */
    public void update(LandPicture persistentInstance) {
        log.debug("updating LandPicture instance");
        try {
            super.update(persistentInstance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(List<LandPicture> pictures) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (LandPicture picture : pictures) {
                LandPicture pic = findById(picture.getPicId());
                if (picture.getTitle() != null && picture.getTitle().length() > 50)
                    pic.setTitle(picture.getTitle().substring(0, 50));
                else
                    pic.setTitle(picture.getTitle());
                if (picture.getDescription() != null && picture.getDescription().length() > 200)
                    pic.setDescription(picture.getDescription().substring(0, 200));
                else
                    pic.setDescription(picture.getDescription());
                session.update(pic);
                if (++i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }
    }

    public LandPicture findById(java.lang.String id) {
        log.debug("getting LandPicture instance with id: " + id);
        try {
            LandPicture instance = (LandPicture) getSession().get(
                    "com.sitexa.farm.data.LandPicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(LandPicture instance) {
        log.debug("finding LandPicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.LandPicture").add(
                    Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding LandPicture instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from LandPicture as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPicUrl(Object picUrl) {
        return findByProperty(PIC_URL, picUrl);
    }

    public List findByTitle(Object title) {
        return findByProperty(TITLE, title);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all LandPicture instances");
        try {
            String queryString = "from LandPicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public LandPicture merge(LandPicture detachedInstance) {
        log.debug("merging LandPicture instance");
        try {
            LandPicture result = (LandPicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(LandPicture instance) {
        log.debug("attaching dirty LandPicture instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(LandPicture instance) {
        log.debug("attaching clean LandPicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}