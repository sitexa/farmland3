package com.sitexa.market.data;

import com.sitexa.framework.hibernate.BaseDAO;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Order
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.market.data.Order
 */

public class OrderDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(OrderDAO.class);
    // property constants
    public static final String CUSTOMER_NAME = "customerName";
    public static final String CUSTOMER_COMPANY = "customerCompany";
    public static final String CUSTOMER_ADDRESS = "customerAddress";
    public static final String CUSTOMER_CITY = "customerCity";
    public static final String CUSTOMER_STATE = "customerState";
    public static final String CUSTOMER_COUNTRY = "customerCountry";
    public static final String CUSTOMER_ZIP_CODE = "customerZipCode";
    public static final String CUSTOMER_PHONE = "customerPhone";
    public static final String CUSTOMER_EMAIL = "customerEmail";
    public static final String DELIVERY_NAME = "deliveryName";
    public static final String DELIVERY_COMPANY = "deliveryCompany";
    public static final String DELIVERY_ADDRESS = "deliveryAddress";
    public static final String DELIVERY_CITY = "deliveryCity";
    public static final String DELIVERY_STATE = "deliveryState";
    public static final String DELIVERY_COUNTRY = "deliveryCountry";
    public static final String DELIVERY_ZIP_CODE = "deliveryZipCode";
    public static final String BILLING_NAME = "billingName";
    public static final String BILLING_COMPANY = "billingCompany";
    public static final String BILLING_ADDRESS = "billingAddress";
    public static final String BILLING_CITY = "billingCity";
    public static final String BILLING_STATE = "billingState";
    public static final String BILLING_COUNTRY = "billingCountry";
    public static final String BILLING_ZIP_CODE = "billingZipCode";
    public static final String PAYMENT_METHOD = "paymentMethod";
    public static final String CC_TYPE = "ccType";
    public static final String CC_OWNER = "ccOwner";
    public static final String CC_NUMBER = "ccNumber";
    public static final String CC_EXPIRES = "ccExpires";
    public static final String COURIER = "courier";
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String CURRENCY = "currency";
    public static final String CURRENCY_VALUE = "currencyValue";

    public void save(Order transientInstance) {
        log.debug("saving Order instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Order persistentInstance) {
        log.debug("deleting Order instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void update(Order order) {
        super.update(order);
    }

    public Order findById(java.lang.String id) {
        log.debug("getting Order instance with id: " + id);
        try {
            Order instance = (Order) getSession().get(
                    "com.sitexa.market.data.Order", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Order> findByMember(Member member) {
        return findByProperty("member", member);
    }


    public List findByExample(Order instance) {
        log.debug("finding Order instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.market.data.Order")
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
        log.debug("finding Order instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Order as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByCustomerName(Object customerName) {
        return findByProperty(CUSTOMER_NAME, customerName);
    }

    public List findByCustomerCompany(Object customerCompany) {
        return findByProperty(CUSTOMER_COMPANY, customerCompany);
    }

    public List findByCustomerAddress(Object customerAddress) {
        return findByProperty(CUSTOMER_ADDRESS, customerAddress);
    }

    public List findByCustomerCity(Object customerCity) {
        return findByProperty(CUSTOMER_CITY, customerCity);
    }

    public List findByCustomerState(Object customerState) {
        return findByProperty(CUSTOMER_STATE, customerState);
    }

    public List findByCustomerCountry(Object customerCountry) {
        return findByProperty(CUSTOMER_COUNTRY, customerCountry);
    }

    public List findByCustomerZipCode(Object customerZipCode) {
        return findByProperty(CUSTOMER_ZIP_CODE, customerZipCode);
    }

    public List findByCustomerPhone(Object customerPhone) {
        return findByProperty(CUSTOMER_PHONE, customerPhone);
    }

    public List findByCustomerEmail(Object customerEmail) {
        return findByProperty(CUSTOMER_EMAIL, customerEmail);
    }

    public List findByDeliveryName(Object deliveryName) {
        return findByProperty(DELIVERY_NAME, deliveryName);
    }

    public List findByDeliveryCompany(Object deliveryCompany) {
        return findByProperty(DELIVERY_COMPANY, deliveryCompany);
    }

    public List findByDeliveryAddress(Object deliveryAddress) {
        return findByProperty(DELIVERY_ADDRESS, deliveryAddress);
    }

    public List findByDeliveryCity(Object deliveryCity) {
        return findByProperty(DELIVERY_CITY, deliveryCity);
    }

    public List findByDeliveryState(Object deliveryState) {
        return findByProperty(DELIVERY_STATE, deliveryState);
    }

    public List findByDeliveryCountry(Object deliveryCountry) {
        return findByProperty(DELIVERY_COUNTRY, deliveryCountry);
    }

    public List findByDeliveryZipCode(Object deliveryZipCode) {
        return findByProperty(DELIVERY_ZIP_CODE, deliveryZipCode);
    }

    public List findByBillingName(Object billingName) {
        return findByProperty(BILLING_NAME, billingName);
    }

    public List findByBillingCompany(Object billingCompany) {
        return findByProperty(BILLING_COMPANY, billingCompany);
    }

    public List findByBillingAddress(Object billingAddress) {
        return findByProperty(BILLING_ADDRESS, billingAddress);
    }

    public List findByBillingCity(Object billingCity) {
        return findByProperty(BILLING_CITY, billingCity);
    }

    public List findByBillingState(Object billingState) {
        return findByProperty(BILLING_STATE, billingState);
    }

    public List findByBillingCountry(Object billingCountry) {
        return findByProperty(BILLING_COUNTRY, billingCountry);
    }

    public List findByBillingZipCode(Object billingZipCode) {
        return findByProperty(BILLING_ZIP_CODE, billingZipCode);
    }

    public List findByPaymentMethod(Object paymentMethod) {
        return findByProperty(PAYMENT_METHOD, paymentMethod);
    }

    public List findByCcType(Object ccType) {
        return findByProperty(CC_TYPE, ccType);
    }

    public List findByCcOwner(Object ccOwner) {
        return findByProperty(CC_OWNER, ccOwner);
    }

    public List findByCcNumber(Object ccNumber) {
        return findByProperty(CC_NUMBER, ccNumber);
    }

    public List findByCcExpires(Object ccExpires) {
        return findByProperty(CC_EXPIRES, ccExpires);
    }

    public List findByCourier(Object courier) {
        return findByProperty(COURIER, courier);
    }

    public List findByTotalPrice(Object totalPrice) {
        return findByProperty(TOTAL_PRICE, totalPrice);
    }

    public List findByCurrency(Object currency) {
        return findByProperty(CURRENCY, currency);
    }

    public List findByCurrencyValue(Object currencyValue) {
        return findByProperty(CURRENCY_VALUE, currencyValue);
    }

    public List findAll() {
        log.debug("finding all Order instances");
        try {
            String queryString = "from Order";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Order merge(Order detachedInstance) {
        log.debug("merging Order instance");
        try {
            Order result = (Order) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Order instance) {
        log.debug("attaching dirty Order instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Order instance) {
        log.debug("attaching clean Order instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}