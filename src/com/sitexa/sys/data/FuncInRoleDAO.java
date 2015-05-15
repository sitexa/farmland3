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
 * FuncInRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.FuncInRole
 */

public class FuncInRoleDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FuncInRoleDAO.class);

    // property constants

    public void save(FuncInRole transientInstance) {
        log.debug("saving FuncInRole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FuncInRole persistentInstance) {
        log.debug("deleting FuncInRole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FuncInRole findById(com.sitexa.sys.data.FuncInRoleId id) {
        log.debug("getting FuncInRole instance with id: " + id);
        try {
            FuncInRole instance = (FuncInRole) getSession().get(
                    "com.sitexa.sys.data.FuncInRole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FuncInRole instance) {
        log.debug("finding FuncInRole instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.FuncInRole").add(
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
        log.debug("finding FuncInRole instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FuncInRole as model where model."
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
        log.debug("finding all FuncInRole instances");
        try {
            String queryString = "from FuncInRole";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FuncInRole merge(FuncInRole detachedInstance) {
        log.debug("merging FuncInRole instance");
        try {
            FuncInRole result = (FuncInRole) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FuncInRole instance) {
        log.debug("attaching dirty FuncInRole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FuncInRole instance) {
        log.debug("attaching clean FuncInRole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}