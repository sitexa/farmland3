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
 * ViewStatsAddr entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.ViewStatsAddr
 */

public class ViewStatsAddrDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(ViewStatsAddrDAO.class);
    // property constants
    public static final String REMOTE_ADDR = "remoteAddr";
    public static final String TIMES = "times";
    public static final String REGION = "region";

    public void save(ViewStatsAddr transientInstance) {
        log.debug("saving ViewStatsAddr instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ViewStatsAddr persistentInstance) {
        log.debug("deleting ViewStatsAddr instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ViewStatsAddr findById(java.lang.Long id) {
        log.debug("getting ViewStatsAddr instance with id: " + id);
        try {
            ViewStatsAddr instance = (ViewStatsAddr) getSession().get(
                    "com.sitexa.sys.data.ViewStatsAddr", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(ViewStatsAddr instance) {
        log.debug("finding ViewStatsAddr instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.ViewStatsAddr").add(
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
        log.debug("finding ViewStatsAddr instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ViewStatsAddr as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRemoteAddr(Object remoteAddr) {
        return findByProperty(REMOTE_ADDR, remoteAddr);
    }

    public List findByTimes(Object times) {
        return findByProperty(TIMES, times);
    }

    public List findByRegion(Object region) {
        return findByProperty(REGION, region);
    }

    public List findAll() {
        log.debug("finding all ViewStatsAddr instances");
        try {
            String queryString = "from ViewStatsAddr";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ViewStatsAddr merge(ViewStatsAddr detachedInstance) {
        log.debug("merging ViewStatsAddr instance");
        try {
            ViewStatsAddr result = (ViewStatsAddr) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ViewStatsAddr instance) {
        log.debug("attaching dirty ViewStatsAddr instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ViewStatsAddr instance) {
        log.debug("attaching clean ViewStatsAddr instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}