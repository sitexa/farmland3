package com.sitexa.farm.data;

import com.sitexa.framework.hibernate.BaseDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-5-20
 * Time: 10:55:30
 */
public class FactionDAO extends BaseDAO {
    private static final Log log = LogFactory.getLog(FactionDAO.class);
    public static final String ACTION_NAME = "actionName";
    public static final String CONTENTS = "contents";
    public static final String EXPENSE = "expense";
    public static final String MATERIALS = "materials";
    public static final String REMARK = "remark";

    public void save(Faction transientInstance) {
        log.debug("saving Faction instance");
        try {
            super.save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Faction persistentInstance) {
        System.out.println("FactionDAO.delete");
        log.debug("deleting Faction instance");
        try {
            super.delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public void delete(String id) {
        String sql = "delete Faction where id = ?";
        try {
            getSession().createQuery(sql).setParameter(0, id).executeUpdate();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public void update(com.sitexa.farm.data.Faction action) {
        super.update(action);
    }

    public Faction findById(java.lang.String id) {
        log.debug("getting Faction instance with id: " + id);
        try {
            Faction instance = (Faction) getSession().get(
                    "com.sitexa.farm.data.Faction", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public Faction findByLandAndAction(String landId, String actionId) {
        try {
            String queryString = "from Faction as model where model.land.landId=? and model.actionId=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, landId).setParameter(1, actionId);
            return (Faction) queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByLand(String landId, String status) {
        try {
            String queryString = "from Faction as model where model.land.landId=? and model.status=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, landId).setParameter(1, status);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by starLand failed", re);
            throw re;
        }
    }

    public List findByExample(Faction instance) {
        log.debug("finding Faction instance by example");
        try {
            List results = getSession().createCriteria(
                    "com.sitexa.farm.data.Faction").add(
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
        log.debug("finding Faction instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Faction as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByActionName(Object actionName) {
        return findByProperty(ACTION_NAME, actionName);
    }

    public List findByContents(Object contents) {
        return findByProperty(CONTENTS, contents);
    }

    public List findByExpense(Object expense) {
        return findByProperty(EXPENSE, expense);
    }

    public List findByMaterials(Object materials) {
        return findByProperty(MATERIALS, materials);
    }

    public List findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all Faction instances");
        try {
            String queryString = "from Faction";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Faction merge(Faction detachedInstance) {
        log.debug("merging Faction instance");
        try {
            Faction result = (Faction) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Faction instance) {
        log.debug("attaching dirty Faction instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Faction instance) {
        log.debug("attaching clean Faction instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    //2010.5.14 lei

    public int getCropsStatusCount(Faction faction) {
        log.debug("get crops_status count by Faction");
        try {
            String hql = "select count(*) c from t_crops_status where actionId=?";
            Query query = getSession().createSQLQuery(hql).setParameter(0, faction);
            return Integer.parseInt(query.list().get(0).toString());
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public int getFarmingCount(Faction faction) {
        log.debug("get Farming count by Faction");
        try {
            String hql = "select count(*) c from t_farming where factionId=?";
            Query query = getSession().createSQLQuery(hql).setParameter(0, faction);
            return Integer.parseInt(query.list().get(0).toString());
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}
