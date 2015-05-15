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
 * Trusteeship entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sitexa.farm.data.Trusteeship
 * @author MyEclipse Persistence Tools
 */

public class TrusteeshipDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(TrusteeshipDAO.class);
	// property constants
	public static final String MEMBER_ID = "memberId";
	public static final String TRUSTEE_ID = "trusteeId";
	public static final String CONTENTS = "contents";
	public static final String REMARK = "remark";

	public void save(Trusteeship transientInstance) {
		log.debug("saving Trusteeship instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Trusteeship persistentInstance) {
		log.debug("deleting Trusteeship instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Trusteeship findById(java.lang.String id) {
		log.debug("getting Trusteeship instance with id: " + id);
		try {
			Trusteeship instance = (Trusteeship) getSession().get(
					"com.sitexa.farm.data.Trusteeship", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Trusteeship instance) {
		log.debug("finding Trusteeship instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.Trusteeship").add(
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
		log.debug("finding Trusteeship instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Trusteeship as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMemberId(Object memberId) {
		return findByProperty(MEMBER_ID, memberId);
	}

	public List findByTrusteeId(Object trusteeId) {
		return findByProperty(TRUSTEE_ID, trusteeId);
	}

	public List findByContents(Object contents) {
		return findByProperty(CONTENTS, contents);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Trusteeship instances");
		try {
			String queryString = "from Trusteeship";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Trusteeship merge(Trusteeship detachedInstance) {
		log.debug("merging Trusteeship instance");
		try {
			Trusteeship result = (Trusteeship) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Trusteeship instance) {
		log.debug("attaching dirty Trusteeship instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Trusteeship instance) {
		log.debug("attaching clean Trusteeship instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}