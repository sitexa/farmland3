package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.framework.hibernate.HibernateSessionFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Seed
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.Seed
 */

public class SeedDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SeedDAO.class);
    // property constants
    public static final String SEED_NAME = "seedName";
    public static final String IMG = "img";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String UOM = "uom";

    public void save(Seed transientInstance) {
        log.debug("saving Seed instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Seed persistentInstance) {
        log.debug("deleting Seed instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(Seed seed) {
        super.update(seed);
    }

    public Seed findById(java.lang.String id) {
        log.debug("getting Seed instance with id: " + id);
        try {
            Seed instance = (Seed) getSession().get(
                    "com.sitexa.farm.data.Seed", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Seed instance) {
        log.debug("finding Seed instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.Seed").add(Example.create(instance))
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
        log.debug("finding Seed instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Seed as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findBySeedName(Object seedName) {
        return findByProperty(SEED_NAME, seedName);
    }

    public List findByImg(Object img) {
        return findByProperty(IMG, img);
    }

    public List findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findByPrice(Object price) {
        return findByProperty(PRICE, price);
    }

    public List findByUom(Object uom) {
        return findByProperty(UOM, uom);
    }

    public List findAll() {
        log.debug("finding all Seed instances");
        try {
            String queryString = "from Seed order by seedName";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Seed merge(Seed detachedInstance) {
        log.debug("merging Seed instance");
        try {
            Seed result = (Seed) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Seed instance) {
        log.debug("attaching dirty Seed instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Seed instance) {
        log.debug("attaching clean Seed instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    //2010.5.14 lei    
    public int getCropsCount(Seed seed){
    	log.debug("get Crops count by Seed");
        try {
        	String hql = "select count(*) c from t_crops where seedId=?";
            Query query = getSession().createSQLQuery(hql).setParameter(0, seed);
            return Integer.parseInt(query.list().get(0).toString()) ;
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public int getFarmingCount(Seed seed){
    	log.debug("get Farming count by Seed");
        try {
        	String hql = "select count(*) c from t_farming where seedId=?";
            Query query = getSession().createSQLQuery(hql).setParameter(0, seed);
            return Integer.parseInt(query.list().get(0).toString()) ;
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}