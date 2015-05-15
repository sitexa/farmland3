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
 * FarmVisit entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.sitexa.farm.data.FarmVisit
 * @author MyEclipse Persistence Tools
 */

public class FarmVisitDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(FarmVisitDAO.class);
	// property constants
	public static final String VISITOR = "visitor";
	public static final String NAME = "name";
	public static final String CONTENTS = "contents";
	public static final String VW_CNT = "vwCnt";
	public static final String PARENT_ID = "parentId";

	public void save(FarmVisit transientInstance) {
		log.debug("saving FarmVisit instance");
		try {
			super.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FarmVisit persistentInstance) {
		log.debug("deleting FarmVisit instance");
		try {
			super.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FarmVisit findById(java.lang.String id) {
		log.debug("getting FarmVisit instance with id: " + id);
		try {
			FarmVisit instance = (FarmVisit) getSession().get(
					"com.sitexa.farm.data.FarmVisit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FarmVisit instance) {
		log.debug("finding FarmVisit instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.FarmVisit").add(
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
		log.debug("finding FarmVisit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FarmVisit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByVisitor(Object visitor) {
		return findByProperty(VISITOR, visitor);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByContents(Object contents) {
		return findByProperty(CONTENTS, contents);
	}

	public List findByVwCnt(Object vwCnt) {
		return findByProperty(VW_CNT, vwCnt);
	}

	public List findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List findAll() {
		log.debug("finding all FarmVisit instances");
		try {
			String queryString = "from FarmVisit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FarmVisit merge(FarmVisit detachedInstance) {
		log.debug("merging FarmVisit instance");
		try {
			FarmVisit result = (FarmVisit) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FarmVisit instance) {
		log.debug("attaching dirty FarmVisit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FarmVisit instance) {
		log.debug("attaching clean FarmVisit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}