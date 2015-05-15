package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * FarmPicture entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.farm.data.FarmPicture
 */

public class FarmPictureDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FarmPictureDAO.class);
    // property constants
    public static final String ABBREV = "abbrev";
    public static final String PIC_URL = "picUrl";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public void save(FarmPicture transientInstance) {
        log.debug("saving FarmPicture instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /**
     * @param transientInstance
     * @author leim 2009.11.24
     */
    public void update(FarmPicture transientInstance) {
        log.debug("updating FarmPicture instance");
        try {
            super.update(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(List<FarmPicture> pictures) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            int i = 0;
            for (FarmPicture picture : pictures) {
                FarmPicture pic = findById(picture.getPicId());

                if (picture.getTitle() != null && picture.getTitle().length() > 50)
                    pic.setTitle(picture.getTitle().substring(0, 50));
                else
                    pic.setTitle(picture.getTitle());

                if (picture.getDescription() != null && picture.getDescription().length() > 200)
                    pic.setDescription(picture.getDescription().substring(0, 200));
                else
                    pic.setDescription(picture.getDescription());

                session.update(pic);
                if (++i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
            session.close();
        } catch (HibernateException e) {
            log.error(e);
            tx.rollback();
            throw e;
        }
    }

    public void delete(FarmPicture persistentInstance) {
        log.debug("deleting FarmPicture instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    
    public void deleteByFarm(Farm farm) {
        log.debug("deleting Farms by starLand");
        try {
        	String sql = "delete from t_farm_picture where farmId=?";
        	getSession().createSQLQuery(sql).setParameter(0, farm.getFarmId()).executeUpdate();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FarmPicture findById(java.lang.String id) {
        log.debug("getting FarmPicture instance with id: " + id);
        try {
            FarmPicture instance = (FarmPicture) getSession().get(
                    "com.sitexa.farm.data.FarmPicture", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(FarmPicture instance) {
        log.debug("finding FarmPicture instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.FarmPicture").add(
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
        log.debug("finding FarmPicture instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from FarmPicture as model where model."
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
        log.debug("finding all FarmPicture instances");
        try {
            String queryString = "from FarmPicture";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FarmPicture merge(FarmPicture detachedInstance) {
        log.debug("merging FarmPicture instance");
        try {
            FarmPicture result = (FarmPicture) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FarmPicture instance) {
        log.debug("attaching dirty FarmPicture instance");
        try {
            super.saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FarmPicture instance) {
        log.debug("attaching clean FarmPicture instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}