package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * MemberPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.MemberPicture
 */

public class MemberPictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MemberPictureDAO.class);
    // property constants
    public static final String PIC_TITLE = "picTitle";
    public static final String ABBREV = "abbrev";
    public static final String PIC_URL = "picUrl";
    public static final String DESCRIPTION = "description";

    public MemberPictureDAO() {
        super("MemberPicture", "memberId");
    }

    public void update(MemberPicture picture) {
        try {
            if (picture.getPicTitle() != null && picture.getPicTitle().length() > 50)
                picture.setPicTitle(picture.getPicTitle().substring(0, 50));
            if (picture.getDescription() != null && picture.getDescription().length() > 200)
                picture.setDescription(picture.getDescription().substring(0, 200));

            super.update(picture);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    public void save(MemberPicture picture) {
        log.debug("saving MemberPicture instance");
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

    public void saveOrUpdate(List<MemberPicture> pictures) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (MemberPicture picture : pictures) {
                MemberPicture pic = findById(picture.getPicId());
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
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }

    }

    public void delete(MemberPicture persistentInstance) {
        log.debug("deleting MemberPicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MemberPicture findById(java.lang.String id) {
        log.debug("getting MemberPicture instance with id: " + id);
        try {
            MemberPicture instance = (MemberPicture) getSession().get(
                    "com.sitexa.user.data.MemberPicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MemberPicture instance) {
        log.debug("finding MemberPicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.MemberPicture").add(
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
        log.debug("finding MemberPicture instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MemberPicture as model where model."
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

    public List findByAbbrev(Object abbrev) {
        return findByProperty(ABBREV, abbrev);
    }

    public List findByPicUrl(Object picUrl) {
        return findByProperty(PIC_URL, picUrl);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all MemberPicture instances");
        try {
            String queryString = "from MemberPicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MemberPicture merge(MemberPicture detachedInstance) {
        log.debug("merging MemberPicture instance");
        try {
            MemberPicture result = (MemberPicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MemberPicture instance) {
        log.debug("attaching dirty MemberPicture instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MemberPicture instance) {
        log.debug("attaching clean MemberPicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}