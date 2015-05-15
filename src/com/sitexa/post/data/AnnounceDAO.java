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
 * Announce entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Announce
 */

public class AnnounceDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(AnnounceDAO.class);
    public static final String CLASS_NAME = "Announce";
    public static final String ID_NAME = "id";

    public AnnounceDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants

    public void save(Announce transientInstance) {
        log.debug("saving Announce instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Announce persistentInstance) {
        log.debug("deleting Announce instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Announce findById(java.lang.String id) {
        log.debug("getting Announce instance with id: " + id);
        try {
            Announce instance = (Announce) getSession().get(
                    "com.sitexa.post.data.Announce", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Announce instance) {
        log.debug("finding Announce instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Announce").add(
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
        log.debug("finding Announce instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Announce as model where model."
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
        log.debug("finding all Announce instances");
        try {
            String queryString = "from Announce";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Announce merge(Announce detachedInstance) {
        log.debug("merging Announce instance");
        try {
            Announce result = (Announce) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Announce instance) {
        log.debug("attaching dirty Announce instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Announce instance) {
        log.debug("attaching clean Announce instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}