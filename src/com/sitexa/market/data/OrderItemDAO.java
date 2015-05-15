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
 * OrderItem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.market.data.OrderItem
 */

public class OrderItemDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(OrderItemDAO.class);
    // property constants
    public static final String OBJECT_ID = "objectId";
    public static final String ITEM_ID = "itemId";
    public static final String ITEM_MODEL = "itemModel";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_PRICE = "itemPrice";
    public static final String FINAL_PRICE = "finalPrice";
    public static final String ITEM_QUANTITY = "itemQuantity";

    public void save(OrderItem transientInstance) {
        log.debug("saving OrderItem instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(OrderItem persistentInstance) {
        log.debug("deleting OrderItem instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(OrderItem item) {
        super.update(item);
    }

    public OrderItem findById(java.lang.String id) {
        log.debug("getting OrderItem instance with id: " + id);
        try {
            OrderItem instance = (OrderItem) getSession().get(
                    "com.sitexa.market.data.OrderItem", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(OrderItem instance) {
        log.debug("finding OrderItem instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.market.data.OrderItem").add(
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
        log.debug("finding OrderItem instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from OrderItem as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByObjectId(Object objectId) {
        return findByProperty(OBJECT_ID, objectId);
    }

    public List findByItemId(Object itemId) {
        return findByProperty(ITEM_ID, itemId);
    }

    public List findByItemModel(Object itemModel) {
        return findByProperty(ITEM_MODEL, itemModel);
    }

    public List findByItemName(Object itemName) {
        return findByProperty(ITEM_NAME, itemName);
    }

    public List findByItemPrice(Object itemPrice) {
        return findByProperty(ITEM_PRICE, itemPrice);
    }

    public List findByFinalPrice(Object finalPrice) {
        return findByProperty(FINAL_PRICE, finalPrice);
    }

    public List findByItemQuantity(Object itemQuantity) {
        return findByProperty(ITEM_QUANTITY, itemQuantity);
    }

    public List findAll() {
        log.debug("finding all OrderItem instances");
        try {
            String queryString = "from OrderItem";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public OrderItem merge(OrderItem detachedInstance) {
        log.debug("merging OrderItem instance");
        try {
            OrderItem result = (OrderItem) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(OrderItem instance) {
        log.debug("attaching dirty OrderItem instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(OrderItem instance) {
        log.debug("attaching clean OrderItem instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}