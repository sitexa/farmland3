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
 * FriendGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.user.data.FriendGroup
 */

public class FriendGroupDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FriendGroupDAO.class);
    // property constants
    public static final String GROUP_NAME = "groupName";

    public void save(FriendGroup transientInstance) {
        log.debug("saving FriendGroup instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FriendGroup persistentInstance) {
        log.debug("deleting FriendGroup instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FriendGroup findById(java.lang.String id) {
        log.debug("getting FriendGroup instance with id: " + id);
        try {
            FriendGroup instance = (FriendGroup) getSession().get(
                    "com.sitexa.user.data.FriendGroup", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FriendGroup instance) {
        log.debug("finding FriendGroup instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.user.data.FriendGroup").add(
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
        log.debug("finding FriendGroup instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FriendGroup as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByGroupName(Object groupName) {
        return findByProperty(GROUP_NAME, groupName);
    }

    public List findAll() {
        log.debug("finding all FriendGroup instances");
        try {
            String queryString = "from FriendGroup";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FriendGroup merge(FriendGroup detachedInstance) {
        log.debug("merging FriendGroup instance");
        try {
            FriendGroup result = (FriendGroup) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FriendGroup instance) {
        log.debug("attaching dirty FriendGroup instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FriendGroup instance) {
        log.debug("attaching clean FriendGroup instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}