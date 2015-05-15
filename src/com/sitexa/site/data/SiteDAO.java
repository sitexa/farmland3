package com.sitexa.site.data;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for Site
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see com.sitexa.site.data.Site
 */
@SuppressWarnings("deprecation")
public class SiteDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(SiteDAO.class);

    public static final String CLASS_NAME = "Site";
    public static final String ID_NAME = "siteId";

    public SiteDAO() {
        super(CLASS_NAME, ID_NAME);
    }

    // property constants
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String STATUS = "status";

    public void save(Site transientInstance) {
        log.debug("saving Site instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void update(Site site) {
        log.debug("saving Site instance");
        try {
            super.update(site);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void saveOrUpdate(Site transientInstance) {
        log.debug("saving Site instance");
        try {
            super.saveOrUpdate(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Site persistentInstance) {
        log.debug("deleting Site instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Site findRoot() {
        try {
            return (Site) getSession().createQuery("from Site as model where parent is null order by siteId desc").list().get(0);
        } catch (RuntimeException re) {
            log.error("findRoot failed", re);
            throw re;
        }
    }

    public Site findById(java.lang.String id) {
        log.debug("getting Site instance with id: " + id);
        try {
            return (Site) getSession().get("com.sitexa.site.data.Site", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Site> findByExample(Site instance) {
        log.debug("finding Site instance by example");
        try {
            List<Site> results = getSession().createCriteria(
                    "com.sitexa.site.data.Site").add(
                    Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<Site> findByProperty(String propertyName, Object value) {
        log.debug("finding Site instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Site as model where model."
                    + propertyName + "= ? order by siteId ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Site> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List<Site> findByAddress(Object address) {
        return findByProperty(ADDRESS, address);
    }

    public List<Site> findByStatus(Object status) {
        return findByProperty(STATUS, status);
    }

    public List<Site> findAll() {
        log.debug("finding all Site instances");
        try {
            String queryString = "from Site";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Site> findHot(int num) throws BaseException {
        String sql = "select siteId,name from t_site where siteid in (select siteId from " +
                "(select top " + num + " siteId,count(*) as cnt from t_post " +
                "group by siteId order by cnt desc ) as _tmp )";
        List<Site> list = new ArrayList<Site>();
        Connection conn = getSession().connection();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Site site = new Site();
                site.setSiteId(rs.getString(1));
                site.setName(rs.getString(2));
                list.add(site);
            }
        } catch (SQLException e) {
            log.error(e);
            BaseException.threw(e);
        }
        return list;
    }

    public Site merge(Site detachedInstance) {
        log.debug("merging Site instance");
        try {
            Site result = (Site) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Site instance) {
        log.debug("attaching dirty Site instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Site instance) {
        log.debug("attaching clean Site instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}