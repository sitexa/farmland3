package com.sitexa.site.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for City
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.site.data.City
 */

public class CityDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(CityDAO.class);
    // property constants
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String CODE = "code";
    public static final String ROME = "rome";
    public static final String CODE2 = "code2";

    public void save(City transientInstance) {
        log.debug("saving City instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(City persistentInstance) {
        log.debug("deleting City instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public City findById(java.lang.String id) {
        log.debug("getting City instance with id: " + id);
        try {
            City instance = (City) getSession().get(
                    "com.sitexa.site.data.City", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(City instance) {
        log.debug("finding City instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.site.data.City").add(
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
        log.debug("finding City instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from City as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByState(Object state) {
        return findByProperty(STATE, state);
    }

    public List findByCity(Object city) {
        return findByProperty(CITY, city);
    }

    public List findByCode(Object code) {
        return findByProperty(CODE, code);
    }

    public List findByRome(Object rome) {
        return findByProperty(ROME, rome);
    }

    public List findByCode2(Object code2) {
        return findByProperty(CODE2, code2);
    }

    public List findAll() {
        log.debug("finding all City instances");
        try {
            String queryString = "from City";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public City merge(City detachedInstance) {
        log.debug("merging City instance");
        try {
            City result = (City) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(City instance) {
        log.debug("attaching dirty City instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(City instance) {
        log.debug("attaching clean City instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List getStateList() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List getCityList(String stateId) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List getCountyList(String cityId) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}