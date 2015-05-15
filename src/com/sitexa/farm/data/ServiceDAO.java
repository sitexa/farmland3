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
 * Service entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sitexa.farm.data.Service
 * @author MyEclipse Persistence Tools
 */

public class ServiceDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(ServiceDAO.class);
	// property constants

	public void save(Service transientInstance) {
		log.debug("saving Service instance");
		try {
			super.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	/**
	 * @author leim 2009.12.5
	 * @param transientInstance
	 */
	public void update(Service transientInstance) {
		log.debug("saving Service instance");
		try {
			super.update(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(Service persistentInstance) {
		log.debug("deleting Service instance");
		try {
			super.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Service findById(java.lang.String id) {
		log.debug("getting Service instance with id: " + id);
		try {
			Service instance = (Service) getSession().get(
					"com.sitexa.farm.data.Service", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Service instance) {
		log.debug("finding Service instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.Service")
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
		log.debug("finding Service instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Service as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Service instances");
		try {
			String queryString = "from Service";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Service merge(Service detachedInstance) {
		log.debug("merging Service instance");
		try {
			Service result = (Service) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Service instance) {
		log.debug("attaching dirty Service instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Service instance) {
		log.debug("attaching clean Service instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}