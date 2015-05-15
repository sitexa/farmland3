package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

public class FarmPostDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmPostDAO.class);
    // property constants
    public static final String FARM_ID = "farmId";

    public void save(FarmPost transientInstance) {
        log.debug("saving FarmPost instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(FarmPost transientInstance) {
        log.debug("update FarmPost instance");
        try {
            super.update(transientInstance);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    public void delete(FarmPost persistentInstance) {
        log.debug("deleting FarmPost instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FarmPost findById(java.lang.String id) {
        log.debug("getting FarmPost instance with id: " + id);
        try {
            FarmPost instance = (FarmPost) getSession().get(
                    "com.sitexa.post.data.FarmPost", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmPost instance) {
        log.debug("finding FarmPost instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.FarmPost").add(
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
        log.debug("finding FarmPost instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmPost as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByFarmId(Object farmId) {
        return findByProperty(FARM_ID, farmId);
    }

    public List findAll() {
        log.debug("finding all FarmPost instances");
        try {
            String queryString = "from FarmPost";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmPost merge(FarmPost detachedInstance) {
        log.debug("merging FarmPost instance");
        try {
            FarmPost result = (FarmPost) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmPost instance) {
        log.debug("attaching dirty FarmPost instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmPost instance) {
        log.debug("attaching clean FarmPost instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}