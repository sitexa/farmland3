package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * ViewStatsUri entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.ViewStatsUri
 */

public class ViewStatsUriDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(ViewStatsUriDAO.class);
    // property constants
    public static final String REQUEST_URI = "requestUri";
    public static final String TIMES = "times";
    public static final String CATEGORY = "category";

    public void save(ViewStatsUri transientInstance) {
        log.debug("saving ViewStatsUri instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ViewStatsUri persistentInstance) {
        log.debug("deleting ViewStatsUri instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ViewStatsUri findById(java.lang.Long id) {
        log.debug("getting ViewStatsUri instance with id: " + id);
        try {
            ViewStatsUri instance = (ViewStatsUri) getSession().get(
                    "com.sitexa.sys.data.ViewStatsUri", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(ViewStatsUri instance) {
        log.debug("finding ViewStatsUri instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.ViewStatsUri").add(
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
        log.debug("finding ViewStatsUri instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ViewStatsUri as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRequestUri(Object requestUri) {
        return findByProperty(REQUEST_URI, requestUri);
    }

    public List findByTimes(Object times) {
        return findByProperty(TIMES, times);
    }

    public List findByCategory(Object category) {
        return findByProperty(CATEGORY, category);
    }

    public List findAll() {
        log.debug("finding all ViewStatsUri instances");
        try {
            String queryString = "from ViewStatsUri";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ViewStatsUri merge(ViewStatsUri detachedInstance) {
        log.debug("merging ViewStatsUri instance");
        try {
            ViewStatsUri result = (ViewStatsUri) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ViewStatsUri instance) {
        log.debug("attaching dirty ViewStatsUri instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ViewStatsUri instance) {
        log.debug("attaching clean ViewStatsUri instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}