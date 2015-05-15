package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for Land
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.sitexa.farm.data.Land
 * @author MyEclipse Persistence Tools
 */

public class LandDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(LandDAO.class);
	// property constants
	public static final String LAND_NAME = "landName";
	public static final String LAND_LORD = "landLord";
	public static final String SITE_ID = "siteId";
	public static final String LAND_TYPE = "landType";
	public static final String RENT_PRICE = "rentPrice";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String DESCRIPTION = "description";
	public static final String REMARK = "remark";

	public void save(Land transientInstance) {
		log.debug("saving Land instance");
		try {
			super.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

    public void update(Land land){
        super.update(land);
    }

	public void delete(Land persistentInstance) {
		log.debug("deleting Land instance");
		try {
			super.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Land findById(java.lang.String id) {
		log.debug("getting Land instance with id: " + id);
		try {
			Land instance = (Land) getSession().get("com.sitexa.farm.data.Land",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Land instance) {
		log.debug("finding Land instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.Land").add(Example.create(instance))
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
		log.debug("finding Land instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Land as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLandName(Object landName) {
		return findByProperty(LAND_NAME, landName);
	}

	public List findByLandLord(Object landLord) {
		return findByProperty(LAND_LORD, landLord);
	}

	public List findBySiteId(Object siteId) {
		return findByProperty(SITE_ID, siteId);
	}

	public List findByLandType(Object landType) {
		return findByProperty(LAND_TYPE, landType);
	}

	public List findByRentPrice(Object rentPrice) {
		return findByProperty(RENT_PRICE, rentPrice);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Land instances");
		try {
			String queryString = "from Land";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Land merge(Land detachedInstance) {
		log.debug("merging Land instance");
		try {
			Land result = (Land) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Land instance) {
		log.debug("attaching dirty Land instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Land instance) {
		log.debug("attaching clean Land instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}