package com.sitexa.post.data;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Poll
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Poll
 */

public class PollDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(PollDAO.class);
    public static final String CLASS_NAME = "PollD";
    public static final String ID_NAME = "id";

    public PollDAO() {
        super(CLASS_NAME, ID_NAME);
    }
    
    // property constants

    public void save(Poll transientInstance) throws BaseException {
        super.save(transientInstance);
    }

    public void delete(Poll persistentInstance) {
        log.debug("deleting Poll instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Poll findById(java.lang.String id) {
        log.debug("getting Poll instance with id: " + id);
        try {
            Poll instance = (Poll) getSession().get(
                    "com.sitexa.post.data.Poll", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Poll instance) {
        log.debug("finding Poll instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Poll").add(
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
        log.debug("finding Poll instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Poll as model where model."
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
        log.debug("finding all Poll instances");
        try {
            String queryString = "from Poll";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Poll merge(Poll detachedInstance) {
        log.debug("merging Poll instance");
        try {
            Poll result = (Poll) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Poll instance) {
        log.debug("attaching dirty Poll instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Poll instance) {
        log.debug("attaching clean Poll instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}