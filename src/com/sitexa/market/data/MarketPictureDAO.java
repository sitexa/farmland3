package com.sitexa.market.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * MarketPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.market.data.MarketPicture
 */

public class MarketPictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MarketPictureDAO.class);
    // property constants
    public static final String PIC_URL = "picUrl";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public void save(MarketPicture transientInstance) {
        log.debug("saving MarketPicture instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(MarketPicture persistentInstance) {
        log.debug("deleting MarketPicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(MarketPicture persistentInstance) {
        log.debug("updating MarketPicture instance");
        try {
            super.update(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MarketPicture findById(java.lang.String id) {
        log.debug("getting MarketPicture instance with id: " + id);
        try {
            MarketPicture instance = (MarketPicture) getSession().get(
                    "com.sitexa.market.data.MarketPicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MarketPicture instance) {
        log.debug("finding MarketPicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.market.data.MarketPicture").add(
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
        log.debug("finding MarketPicture instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from MarketPicture as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByPicUrl(Object picUrl) {
        return findByProperty(PIC_URL, picUrl);
    }

    public List findByTitle(Object title) {
        return findByProperty(TITLE, title);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all MarketPicture instances");
        try {
            String queryString = "from MarketPicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MarketPicture merge(MarketPicture detachedInstance) {
        log.debug("merging MarketPicture instance");
        try {
            MarketPicture result = (MarketPicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MarketPicture instance) {
        log.debug("attaching dirty MarketPicture instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(MarketPicture instance) {
        log.debug("attaching clean MarketPicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}