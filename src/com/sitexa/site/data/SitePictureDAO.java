package com.sitexa.site.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * SitePicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.site.data.SitePicture
 */

public class SitePictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SitePictureDAO.class);
    // property constants
    public static final String PIC_TITLE = "picTitle";
    public static final String PIC_URL = "picUrl";
    public static final String DESCRIPTION = "description";

    public SitePictureDAO() {
        super("SitePicture", "picId");
    }

    public void save(SitePicture picture) {
        log.debug("saving SitePicture instance");
        try {
            if (picture.getPicTitle() != null && picture.getPicTitle().length() > 50)
                picture.setPicTitle(picture.getPicTitle().substring(0, 50));

            if (picture.getDescription() != null && picture.getDescription().length() > 200)
                picture.setDescription(picture.getDescription().substring(0, 200));

            super.save(picture);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(SitePicture picture) {
        try {
            if (picture.getPicTitle() != null && picture.getPicTitle().length() > 50)
                picture.setPicTitle(picture.getPicTitle().substring(0, 50));

            if (picture.getDescription() != null && picture.getDescription().length() > 200)
                picture.setDescription(picture.getDescription().substring(0, 200));

            super.update(picture);
        } catch (HibernateException e) {
            log.error(e);
            throw e;
        }
    }

    public void saveOrUpdate(List<SitePicture> pictures) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (SitePicture picture : pictures) {
                SitePicture pic = findById(picture.getPicId());

                if (picture.getPicTitle() != null && picture.getPicTitle().length() > 50)
                    pic.setPicTitle(picture.getPicTitle().substring(0, 50));
                else
                    pic.setPicTitle(picture.getPicTitle());

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
            session.close();
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }

    }

    public void delete(String picId) {
        System.out.println("delete image record.");
        delete(findById(picId));
    }

    public void delete(SitePicture persistentInstance) {
        log.debug("deleting SitePicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public SitePicture findById(java.lang.String id) {
        log.debug("getting SitePicture instance with id: " + id);
        try {
            SitePicture instance = (SitePicture) getSession().get(
                    "com.sitexa.site.data.SitePicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SitePicture instance) {
        log.debug("finding SitePicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.site.data.SitePicture").add(
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
        log.debug("finding SitePicture instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SitePicture as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPicTitle(Object picTitle) {
        return findByProperty(PIC_TITLE, picTitle);
    }

    public List findByPicUrl(Object picUrl) {
        return findByProperty(PIC_URL, picUrl);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all SitePicture instances");
        try {
            String queryString = "from SitePicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SitePicture merge(SitePicture detachedInstance) {
        log.debug("merging SitePicture instance");
        try {
            SitePicture result = (SitePicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SitePicture instance) {
        log.debug("attaching dirty SitePicture instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SitePicture instance) {
        log.debug("attaching clean SitePicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}