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
 * MenuInRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.MenuInRole
 */

public class MenuInRoleDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MenuInRoleDAO.class);

    // property constants

    public void save(MenuInRole transientInstance) {
        log.debug("saving MenuInRole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MenuInRole persistentInstance) {
        log.debug("deleting MenuInRole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public MenuInRole findById(com.sitexa.sys.data.MenuInRoleId id) {
        log.debug("getting MenuInRole instance with id: " + id);
        try {
            MenuInRole instance = (MenuInRole) getSession().get(
                    "com.sitexa.sys.data.MenuInRole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MenuInRole instance) {
        log.debug("finding MenuInRole instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.MenuInRole").add(
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
        log.debug("finding MenuInRole instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from MenuInRole as model where model."
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
        log.debug("finding all MenuInRole instances");
        try {
            String queryString = "from MenuInRole";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MenuInRole merge(MenuInRole detachedInstance) {
        log.debug("merging MenuInRole instance");
        try {
            MenuInRole result = (MenuInRole) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MenuInRole instance) {
        log.debug("attaching dirty MenuInRole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MenuInRole instance) {
        log.debug("attaching clean MenuInRole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}