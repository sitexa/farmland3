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
 * PollOption entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.PollOption
 */

public class PollOptionDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PollOptionDAO.class);

    // property constants
    public static final String OPTION_TEXT = "optionText";

    public void save(PollOption transientInstance) {
        log.debug("saving PollOption instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(PollOption persistentInstance) {
        log.debug("deleting PollOption instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public PollOption findById(com.sitexa.post.data.PollOptionId id) {
        log.debug("getting PollOption instance with id: " + id);
        try {
            PollOption instance = (PollOption) getSession().get(
                    "com.sitexa.post.data.PollOption", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(PollOption instance) {
        log.debug("finding PollOption instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.PollOption").add(
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
        log.debug("finding PollOption instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from PollOption as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByOptionText(Object optionText) {
        return findByProperty(OPTION_TEXT, optionText);
    }

    public List findAll() {
        log.debug("finding all PollOption instances");
        try {
            String queryString = "from PollOption";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public PollOption merge(PollOption detachedInstance) {
        log.debug("merging PollOption instance");
        try {
            PollOption result = (PollOption) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PollOption instance) {
        log.debug("attaching dirty PollOption instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(PollOption instance) {
        log.debug("attaching clean PollOption instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}