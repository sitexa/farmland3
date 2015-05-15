package com.sitexa.post.data;

import java.util.List;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * LandPost entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.LandPost
 */

public class LandPostDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(LandPostDAO.class);
    // property constants
    public static final String TYPE_ID = "typeId";

    public void save(LandPost transientInstance) {
        log.debug("saving LandPost instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(LandPost persistentInstance) {
        log.debug("deleting LandPost instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public LandPost findById(java.lang.String id) {
        log.debug("getting LandPost instance with id: " + id);
        try {
            LandPost instance = (LandPost) getSession().get(
                    "com.sitexa.post.data.LandPost", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(LandPost instance) {
        log.debug("finding LandPost instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.LandPost").add(
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
        log.debug("finding LandPost instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from LandPost as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByTypeId(Object typeId) {
        return findByProperty(TYPE_ID, typeId);
    }

    public List findAll() {
        log.debug("finding all LandPost instances");
        try {
            String queryString = "from LandPost";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public LandPost merge(LandPost detachedInstance) {
        log.debug("merging LandPost instance");
        try {
            LandPost result = (LandPost) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(LandPost instance) {
        log.debug("attaching dirty LandPost instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(LandPost instance) {
        log.debug("attaching clean LandPost instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}