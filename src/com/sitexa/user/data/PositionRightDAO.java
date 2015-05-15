package com.sitexa.user.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * PositionRight entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.PositionRight
 */

public class PositionRightDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PositionRightDAO.class);

    // property constants

    public void save(PositionRight transientInstance) {
        log.debug("saving PositionRight instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(PositionRight persistentInstance) {
        log.debug("deleting PositionRight instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PositionRight findById(com.sitexa.user.data.PositionRightId id) {
        log.debug("getting PositionRight instance with id: " + id);
        try {
            PositionRight instance = (PositionRight) getSession().get(
                    "com.sitexa.user.data.PositionRight", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PositionRight instance) {
        log.debug("finding PositionRight instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.PositionRight").add(
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
        log.debug("finding PositionRight instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from PositionRight as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all PositionRight instances");
        try {
            String queryString = "from PositionRight";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PositionRight merge(PositionRight detachedInstance) {
        log.debug("merging PositionRight instance");
        try {
            PositionRight result = (PositionRight) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PositionRight instance) {
        log.debug("attaching dirty PositionRight instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PositionRight instance) {
        log.debug("attaching clean PositionRight instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}