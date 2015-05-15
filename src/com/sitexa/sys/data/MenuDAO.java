package com.sitexa.sys.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Menu
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.sys.data.Menu
 */

public class MenuDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(MenuDAO.class);
    // property constants
    public static final String MENU_CODE = "menuCode";
    public static final String CAPTION = "caption";
    public static final String CAPTION_CN = "captionCn";
    public static final String IMAGE_PATH = "imagePath";
    public static final String LINK_PATH = "linkPath";
    public static final String STATUS = "status";
    public static final String REMARK = "remark";

    public void save(Menu transientInstance) {
        log.debug("saving Menu instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Menu persistentInstance) {
        log.debug("deleting Menu instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Menu findById(java.lang.String id) {
        log.debug("getting Menu instance with id: " + id);
        try {
            Menu instance = (Menu) getSession().get(
                    "com.sitexa.sys.data.Menu", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Menu instance) {
        log.debug("finding Menu instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.sys.data.Menu").add(
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
        log.debug("finding Menu instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Menu as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByMenuCode(Object menuCode) {
        return findByProperty(MENU_CODE, menuCode);
    }

    public List findByCaption(Object caption) {
        return findByProperty(CAPTION, caption);
    }

    public List findByCaptionCn(Object captionCn) {
        return findByProperty(CAPTION_CN, captionCn);
    }

    public List findByImagePath(Object imagePath) {
        return findByProperty(IMAGE_PATH, imagePath);
    }

    public List findByLinkPath(Object linkPath) {
        return findByProperty(LINK_PATH, linkPath);
    }

    public List findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Menu instances");
        try {
            String queryString = "from Menu";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Menu merge(Menu detachedInstance) {
        log.debug("merging Menu instance");
        try {
            Menu result = (Menu) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Menu instance) {
        log.debug("attaching dirty Menu instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Menu instance) {
        log.debug("attaching clean Menu instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}