package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Business entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Business
 */

public class BusinessDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(BusinessDAO.class);
    public static final String CLASS_NAME = "Business";
    public static final String ID_NAME = "id";

    public BusinessDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String IMG_PATH = "imgPath";

    public void save(Business transientInstance) {
        log.debug("saving Business instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Business persistentInstance) {
        log.debug("deleting Business instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Business findById(java.lang.String id) {
        log.debug("getting Business instance with id: " + id);
        try {
            Business instance = (Business) getSession().get(
                    "com.sitexa.post.data.Business", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Business instance) {
        log.debug("finding Business instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Business").add(
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
        log.debug("finding Business instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Business as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByImgPath(Object imgPath) {
        return findByProperty(IMG_PATH, imgPath);
    }

    public List findAll() {
        log.debug("finding all Business instances");
        try {
            String queryString = "from Business";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Business merge(Business detachedInstance) {
        log.debug("merging Business instance");
        try {
            Business result = (Business) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Business instance) {
        log.debug("attaching dirty Business instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Business instance) {
        log.debug("attaching clean Business instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}