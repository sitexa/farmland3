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
 * Affair entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.Affair
 */

public class AffairDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(AffairDAO.class);
    public static final String CLASS_NAME = "Affair";
    public static final String ID_NAME = "id";

    public AffairDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants

    public void save(Affair transientInstance) {
        log.debug("saving Affair instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Affair persistentInstance) {
        log.debug("deleting Affair instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Affair findById(java.lang.String id) {
        log.debug("getting Affair instance with id: " + id);
        try {
            Affair instance = (Affair) getSession().get(
                    "com.sitexa.post.data.Affair", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Affair instance) {
        log.debug("finding Affair instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.Affair").add(
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
        log.debug("finding Affair instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Affair as model where model."
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
        log.debug("finding all Affair instances");
        try {
            String queryString = "from Affair";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Affair merge(Affair detachedInstance) {
        log.debug("merging Affair instance");
        try {
            Affair result = (Affair) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Affair instance) {
        log.debug("attaching dirty Affair instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Affair instance) {
        log.debug("attaching clean Affair instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}