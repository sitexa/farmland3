package com.sitexa.post.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for House
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.post.data.House
 */

public class HouseDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(HouseDAO.class);
    // property constants
    public static final String BUILDING = "building";
    public static final String ROOM = "room";
    public static final String AREA = "area";
    public static final String AREA2 = "area2";
    public static final String LANDLORD = "landlord";

    public void save(House transientInstance) {
        log.debug("saving House instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(House persistentInstance) {
        log.debug("deleting House instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public House findById(java.lang.String id) {
        log.debug("getting House instance with id: " + id);
        try {
            House instance = (House) getSession().get(
                    "com.sitexa.post.data.House", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(House instance) {
        log.debug("finding House instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.post.data.House").add(
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
        log.debug("finding House instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from House as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByBuilding(Object building) {
        return findByProperty(BUILDING, building);
    }

    public List findByRoom(Object room) {
        return findByProperty(ROOM, room);
    }

    public List findByArea(Object area) {
        return findByProperty(AREA, area);
    }

    public List findByArea2(Object area2) {
        return findByProperty(AREA2, area2);
    }

    public List findByLandlord(Object landlord) {
        return findByProperty(LANDLORD, landlord);
    }

    public List findAll() {
        log.debug("finding all House instances");
        try {
            String queryString = "from House";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public House merge(House detachedInstance) {
        log.debug("merging House instance");
        try {
            House result = (House) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(House instance) {
        log.debug("attaching dirty House instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(House instance) {
        log.debug("attaching clean House instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}