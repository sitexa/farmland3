package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Crops
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.Crops
 */

public class CropsDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(CropsDAO.class);
    // property constants
    public static final String SEED_NUMBER = "seedNumber";
    public static final String VITAL_POWER = "vitalPower";
    public static final String MATURITY = "maturity";

    public void save(Crops transientInstance) {
        log.debug("saving Crops instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Crops persistentInstance) {
        log.debug("deleting Crops instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void deleteCrops(Farm farm) {
        String sql = "delete Crops where farm = ?";
        try {
            getSession().createQuery(sql).setParameter(0, farm).executeUpdate();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public void update(Crops crops) {
        super.update(crops);
    }

    public Crops findById(java.lang.String id) {
        log.debug("getting Crops instance with id: " + id);
        try {
            Crops instance = (Crops) getSession().get(
                    "com.sitexa.farm.data.Crops", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Crops instance) {
        log.debug("finding Crops instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.Crops").add(Example.create(instance))
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
        log.debug("finding Crops instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Crops as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findBySeedNumber(Object seedNumber) {
        return findByProperty(SEED_NUMBER, seedNumber);
    }

    public List findByVitalPower(Object vitalPower) {
        return findByProperty(VITAL_POWER, vitalPower);
    }

    public List findByMaturity(Object maturity) {
        return findByProperty(MATURITY, maturity);
    }

    public List findAll() {
        log.debug("finding all Crops instances");
        try {
            String queryString = "from Crops";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Crops merge(Crops detachedInstance) {
        log.debug("merging Crops instance");
        try {
            Crops result = (Crops) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Crops instance) {
        log.debug("attaching dirty Crops instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Crops instance) {
        log.debug("attaching clean Crops instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}