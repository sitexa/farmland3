/**
* @作者 leim
* @创建日期 2010-6-11
* @版本 V 1.0
*/ 
package com.sitexa.farm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.FarmPlanDAO;
import com.sitexa.farm.data.FarmStore;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.data.SeedDAO;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.Sequence;

public class PlanService {
	private static Log log = LogFactory.getLog(PlanService.class);
	
    public static FarmPlan getById(String id) {
        if (id == null || "".equals(id)) return null;
        FarmPlanDAO dao = new FarmPlanDAO();
        return dao.findById(id);
    }

    public static List<FarmPlan> getPlans(Land land) {
        List<FarmPlan> result = new ArrayList<FarmPlan>();
        if (land == null) return result;
        String hql = "select * from t_farm_plan where landId=?";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(FarmPlan.class)
                    .setParameter(0, land);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }
    
    public static List<FarmPlan> getPlans(Land land, Page page) {
        List<FarmPlan> result = new ArrayList<FarmPlan>();
        if (land == null) return result;
        String hql = "select * from t_farm_plan where landId=?";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(FarmPlan.class)
                    .setParameter(0, land);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            if (tot < page.getCurrent() && tot>0) page.setCurrent(tot);
            page.setTotal(tot);

            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }
    
    public static void create(FarmPlan plan) {
        if (plan == null || plan.getLand() == null) return;
        FarmPlanDAO dao = new FarmPlanDAO();
        plan.setPlanId(Sequence.getId());
        dao.save(plan);
    }
    
    public static void update(FarmPlan plan) {
        if (plan == null || plan.getPlanId() == null) return;
        FarmPlanDAO dao = new FarmPlanDAO();
        FarmPlan plan1 = dao.findById(plan.getPlanId());
        if (plan1 == null) return;
        plan1.setPlanName(plan.getPlanName());
        plan1.setPrice(plan.getPrice());
        plan1.setDescription(plan.getDescription());
        plan1.setRemark(plan.getRemark());
        dao.update(plan1);
    }
    
    public static void delete(FarmPlan plan) {
        if (plan == null || "".equals(plan.getPlanId())) return;
        FarmPlanDAO dao = new FarmPlanDAO();
        dao.delete(plan);
    }
}
