package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Farming entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.Farming
 */

public class FarmingDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmingDAO.class);
    // property constants
    public static final String QUANTITY = "quantity";
    public static final String AMOUNT = "amount";
    public static final String CONTENTS = "contents";
    public static final String REMARK = "remark";

    public void save(Farming transientInstance) {
        log.debug("saving Farming instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Farming transientInstance) {
        log.debug("updating Farming instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void delete(Farming persistentInstance) {
        log.debug("deleting Farming instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    //2010.5.13 lei

    public void delete(Faction faction) {
        String sql = "delete Farming where faction=?";
        try {
            getSession().createQuery(sql).setParameter(0, faction).executeUpdate();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void deleteFarming(Farm farm) {
        String sql = "delete Farming where farm=?";
        try {
            getSession().createQuery(sql).setParameter(0, farm).executeUpdate();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public void deleteFaction(String id) {
        String sql = "delete Farming where id=?";
        try {
            getSession().createQuery(sql).setParameter(0, id).executeUpdate();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public Farming findById(java.lang.String id) {
        log.debug("getting Farming instance with id: " + id);
        try {
            Farming instance = (Farming) getSession().get(
                    "com.sitexa.farm.data.Farming", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Farming instance) {
        log.debug("finding Farming instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.Farming")
                    .add(Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Farming instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Farming as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByQuantity(Object quantity) {
        return findByProperty(QUANTITY, quantity);
    }

    public List findByAmount(Object amount) {
        return findByProperty(AMOUNT, amount);
    }

    public List findByContents(Object contents) {
        return findByProperty(CONTENTS, contents);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Farming instances");
        try {
            String queryString = "from Farming";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Farming merge(Farming detachedInstance) {
        log.debug("merging Farming instance");
        try {
            Farming result = (Farming) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Farming instance) {
        log.debug("attaching dirty Farming instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Farming instance) {
        log.debug("attaching clean Farming instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public Farming findLastFarming(Farm farm, Seed seed) {
        log.debug("finding Last Farming By Farm and Seed");
        Farming farming = null;
        try {
            String queryString = "from Farming as model where model.farm=? and model.seed=? order by startTime desc";
            Query queryObject = getSession().createQuery(queryString)
            								.setParameter(0, farm)
            								.setParameter(1, seed)
            								.setMaxResults(1);
            if(queryObject.list().size()>0){
            	farming = (Farming) queryObject.list().get(0);
            }
            return farming;
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}