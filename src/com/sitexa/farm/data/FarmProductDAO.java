package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * FarmProduct entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sitexa.farm.data.FarmProduct
 * @author MyEclipse Persistence Tools
 */

public class FarmProductDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(FarmProductDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String SPECS = "specs";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String REMARK = "remark";

	public void save(FarmProduct transientInstance) {
		log.debug("saving FarmProduct instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FarmProduct persistentInstance) {
		log.debug("deleting FarmProduct instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FarmProduct findById(java.lang.String id) {
		log.debug("getting FarmProduct instance with id: " + id);
		try {
			FarmProduct instance = (FarmProduct) getSession().get(
					"com.sitexa.farm.data.FarmProduct", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FarmProduct instance) {
		log.debug("finding FarmProduct instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.FarmProduct").add(
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
		log.debug("finding FarmProduct instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FarmProduct as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List findBySpecs(Object specs) {
		return findByProperty(SPECS, specs);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all FarmProduct instances");
		try {
			String queryString = "from FarmProduct";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FarmProduct merge(FarmProduct detachedInstance) {
		log.debug("merging FarmProduct instance");
		try {
			FarmProduct result = (FarmProduct) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FarmProduct instance) {
		log.debug("attaching dirty FarmProduct instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FarmProduct instance) {
		log.debug("attaching clean FarmProduct instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}