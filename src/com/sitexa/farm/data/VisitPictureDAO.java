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
 * VisitPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.sitexa.farm.data.VisitPicture
 * @author MyEclipse Persistence Tools
 */

public class VisitPictureDAO extends BaseDAO {
	private static final Log log = LogFactory.getLog(VisitPictureDAO.class);
	// property constants
	public static final String ABBREV = "abbrev";
	public static final String PIC_URL = "picUrl";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";

	public void save(VisitPicture transientInstance) {
		log.debug("saving VisitPicture instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VisitPicture persistentInstance) {
		log.debug("deleting VisitPicture instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VisitPicture findById(java.lang.String id) {
		log.debug("getting VisitPicture instance with id: " + id);
		try {
			VisitPicture instance = (VisitPicture) getSession().get(
					"com.sitexa.farm.data.VisitPicture", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VisitPicture instance) {
		log.debug("finding VisitPicture instance by example");
		try {
			List results = getSession().createCriteria(
					"com.sitexa.farm.data.VisitPicture").add(
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
		log.debug("finding VisitPicture instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VisitPicture as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAbbrev(Object abbrev) {
		return findByProperty(ABBREV, abbrev);
	}

	public List findByPicUrl(Object picUrl) {
		return findByProperty(PIC_URL, picUrl);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all VisitPicture instances");
		try {
			String queryString = "from VisitPicture";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VisitPicture merge(VisitPicture detachedInstance) {
		log.debug("merging VisitPicture instance");
		try {
			VisitPicture result = (VisitPicture) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VisitPicture instance) {
		log.debug("attaching dirty VisitPicture instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VisitPicture instance) {
		log.debug("attaching clean VisitPicture instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}