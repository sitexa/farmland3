package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.user.data.Member;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * FarmStore entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.FarmStore
 */

public class FarmStoreDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmStoreDAO.class);
    // property constants
    public static final String BUYER_ID = "buyerId";
    public static final String OBJECT_ID = "objectId";
    public static final String ITEM_ID = "itemId";
    public static final String ITEM_NAME = "itemName";
    public static final String QUANTITY = "quantity";
    public static final String ACREAGE = "acreage";
    public static final String REMARK = "remark";

    public void save(FarmStore transientInstance) {
        log.debug("saving FarmStore instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(FarmStore transientInstance) {
        log.debug("updating FarmStore instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void delete(FarmStore persistentInstance) {
        log.debug("deleting FarmStore instance");
        try {
        	super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FarmStore findById(java.lang.String id) {
        log.debug("getting FarmStore instance with id: " + id);
        try {
            FarmStore instance = (FarmStore) getSession().get(
                    "com.sitexa.farm.data.FarmStore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmStore instance) {
        log.debug("finding FarmStore instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.FarmStore").add(
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
        log.debug("finding FarmStore instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmStore as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public FarmStore findIt(Farm farm, Member buyer, String itemId) {
        log.debug("finding FarmStore instance with property: Farm:" + farm
        		+ ", buyer: " + buyer + ", itemId: " + itemId);
        try {
            String sql = "select top 1 * from t_farm_store where 1=1";
            if(farm!=null && farm.getFarmId()!=null && !"".equals(farm.getFarmId())){
            	sql = sql + " and farmId=" + farm.getFarmId();
            }
            if(buyer!=null && buyer.getMemberId()!=null && !"".equals(buyer.getMemberId())){
            	sql = sql + " and buyerId=" + buyer.getMemberId();
            }
            if(itemId!=null && !"".equals(itemId)){
            	sql = sql + " and itemId=" + itemId;
            }            
            Query queryObject = getSession().createSQLQuery(sql).addEntity(FarmStore.class);
            List list = queryObject.list();
            
            if(list.size()>0)	return (FarmStore)list.get(0);
            return null;
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
    
    public List findByBuyerId(Object buyerId) {
        return findByProperty(BUYER_ID, buyerId);
    }

    public List findByObjectId(Object objectId) {
        return findByProperty(OBJECT_ID, objectId);
    }

    public List findByItemId(Object itemId) {
        return findByProperty(ITEM_ID, itemId);
    }

    public List findByItemName(Object itemName) {
        return findByProperty(ITEM_NAME, itemName);
    }

    public List findByQuantity(Object quantity) {
        return findByProperty(QUANTITY, quantity);
    }

    public List findByAcreage(Object acreage) {
        return findByProperty(ACREAGE, acreage);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all FarmStore instances");
        try {
            String queryString = "from FarmStore";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmStore merge(FarmStore detachedInstance) {
        log.debug("merging FarmStore instance");
        try {
            FarmStore result = (FarmStore) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmStore instance) {
        log.debug("attaching dirty FarmStore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmStore instance) {
        log.debug("attaching clean FarmStore instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

}