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
 * Market entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.market.data.Market
 */

public class MarketDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MarketDAO.class);
    // property constants
    public static final String ITEM_TITLE = "itemTitle";
    public static final String SITE_ID = "siteId";
    public static final String CONTENTS = "contents";
    public static final String CONTACT = "contact";
    public static final String VW_CNT = "vwCnt";
    public static final String STATUS = "status";

    public void save(Market transientInstance) {
        log.debug("saving Market instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Market persistentInstance) {
        try {
            super.update(persistentInstance);
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public void delete(Market persistentInstance) {
        log.debug("deleting Market instance");
        try {
        	super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Market findById(java.lang.String id) {
        log.debug("getting Market instance with id: " + id);
        try {
            Market instance = (Market) getSession().get(
                    "com.sitexa.market.data.Market", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Market instance) {
        log.debug("finding Market instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.market.data.Market").add(
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
        log.debug("finding Market instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Market as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByItemTitle(Object itemTitle) {
        return findByProperty(ITEM_TITLE, itemTitle);
    }

    public List findBySiteId(Object siteId) {
        return findByProperty(SITE_ID, siteId);
    }

    public List findByContents(Object contents) {
        return findByProperty(CONTENTS, contents);
    }

    public List findByContact(Object contact) {
        return findByProperty(CONTACT, contact);
    }

    public List findByVwCnt(Object vwCnt) {
        return findByProperty(VW_CNT, vwCnt);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findAll() {
        log.debug("finding all Market instances");
        try {
            String queryString = "from Market";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Market merge(Market detachedInstance) {
        log.debug("merging Market instance");
        try {
            Market result = (Market) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Market instance) {
        log.debug("attaching dirty Market instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Market instance) {
        log.debug("attaching clean Market instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}