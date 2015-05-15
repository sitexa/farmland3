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
 * Message entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Message
 */

public class MessageDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MessageDAO.class);
    public static final String CLASS_NAME = "Message";
    public static final String ID_NAME = "id";

    public MessageDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants

    public void save(Message transientInstance) {
        super.save(transientInstance);
    }

    public void delete(Message persistentInstance) {
        log.debug("deleting Message instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Message findById(java.lang.String id) {
        log.debug("getting Message instance with id: " + id);
        try {
            Message instance = (Message) getSession().get(
                    "com.sitexa.post.data.Message", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Message instance) {
        log.debug("finding Message instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Message").add(
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
        log.debug("finding Message instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Message as model where model."
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
        log.debug("finding all Message instances");
        try {
            String queryString = "from Message";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Message merge(Message detachedInstance) {
        log.debug("merging Message instance");
        try {
            Message result = (Message) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Message instance) {
        log.debug("attaching dirty Message instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Message instance) {
        log.debug("attaching clean Message instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}