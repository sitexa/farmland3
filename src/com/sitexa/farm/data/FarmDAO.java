package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Farm
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.Farm
 */

public class FarmDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmDAO.class);
    // property constants
    public static final String MEMBER_ID = "memberId";
    public static final String ACREAGE = "acreage";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String RENT_PRICE = "rentPrice";
    public static final String CROPS_SUITABLE = "cropsSuitable";
    public static final String CROPS_ORIGINAL = "cropsOriginal";
    public static final String CAPACITY_ORIGINAL = "capacityOriginal";
    public static final String DISPOSAL_ORIGINAL = "disposalOriginal";
    public static final String REMARK = "remark";

    public void save(Farm transientInstance) {
        log.debug("saving Farm instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Farm farm) {
        super.update(farm);
    }

    public void delete(Farm persistentInstance) {
        log.debug("deleting Farm instance");
        try {
             super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Farm findById(java.lang.String id) {
        log.debug("getting Farm instance with id: " + id);
        try {
            Farm instance = (Farm) getSession().get("com.sitexa.farm.data.Farm",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Farm instance) {
        log.debug("finding Farm instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.Farm").add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Farm instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Farm as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByMemberId(Object memberId) {
        return findByProperty(MEMBER_ID, memberId);
    }

    public List findByAcreage(Object acreage) {
        return findByProperty(ACREAGE, acreage);
    }

    public List findByLongitude(Object longitude) {
        return findByProperty(LONGITUDE, longitude);
    }

    public List findByLatitude(Object latitude) {
        return findByProperty(LATITUDE, latitude);
    }

    public List findByRentPrice(Object rentPrice) {
        return findByProperty(RENT_PRICE, rentPrice);
    }

    public List findByCropsSuitable(Object cropsSuitable) {
        return findByProperty(CROPS_SUITABLE, cropsSuitable);
    }

    public List findByCropsOriginal(Object cropsOriginal) {
        return findByProperty(CROPS_ORIGINAL, cropsOriginal);
    }

    public List findByCapacityOriginal(Object capacityOriginal) {
        return findByProperty(CAPACITY_ORIGINAL, capacityOriginal);
    }

    public List findByDisposalOriginal(Object disposalOriginal) {
        return findByProperty(DISPOSAL_ORIGINAL, disposalOriginal);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Farm instances");
        try {
            String queryString = "from Farm";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Farm merge(Farm detachedInstance) {
        log.debug("merging Farm instance");
        try {
            Farm result = (Farm) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Farm instance) {
        log.debug("attaching dirty Farm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Farm instance) {
        log.debug("attaching clean Farm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}