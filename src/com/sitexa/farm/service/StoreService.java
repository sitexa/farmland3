/**
* @作者 leim
* @创建日期 2010-6-13
* @版本 V 1.0
*/ 
package com.sitexa.farm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPlan;
import com.sitexa.farm.data.FarmPlanDAO;
import com.sitexa.farm.data.FarmStore;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.data.SeedDAO;
import com.sitexa.farm.rest.myfarm.ShopController;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.user.data.Member;

public class StoreService {
    private static Log log = LogFactory.getLog(StoreService.class);

    public static List<Object[]> getGoods(Farm farm, Member member, String group) {
    	List<Object[]> result = new ArrayList<Object[]>();
    	if(farm==null || farm.getFarmId()==null || "".equals(farm.getFarmId()))	return result;
    	if(member==null || member.getMemberId()==null || "".equals(member.getMemberId()))	return result;
    	if(group==null || "".equals(group))	return result;
    	
    	String hql = "select itemId, min(itemName) as itemName, sum(acreage) as acreage from t_farm_store where objectId=? and (farmId=? or buyerId=?) group by itemId";
    	try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql)
            		.setParameter(0, group)
                    .setParameter(1, farm.getFarmId())
                    .setParameter(2, member.getMemberId());

            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }
    
    public static Object[] getGood(Farm farm, Member member, String group, String itemId) {
    	List<Object[]> result = new ArrayList<Object[]>();
    	if(farm==null || farm.getFarmId()==null || "".equals(farm.getFarmId()))	return null;
    	if(member==null || member.getMemberId()==null || "".equals(member.getMemberId()))	return null;
    	if(group==null || "".equals(group))	return null;
    	
    	String hql = "select itemId, min(itemName) as itemName, sum(acreage) as acreage from t_farm_store where objectId=? and (farmId=? or buyerId=?) and itemId=? group by itemId";
    	try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql)
            		.setParameter(0, group)
                    .setParameter(1, farm.getFarmId())
                    .setParameter(2, member.getMemberId())
                    .setParameter(3, itemId);

            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        if(result.size()>0){
        	return result.get(0);
        }
        return null;
    }
    
    public static FarmPlan getFarmPlanById(String id) {
    	if(id==null || "".equals(id))	return null;
    	FarmPlanDAO dao = new FarmPlanDAO();
    	return dao.findById(id);
    }
    
    public static List<Seed> getSeedsFromStore(Farm farm, Member member) {
    	List<Seed> result = new ArrayList<Seed>();
    	if(farm==null || farm.getFarmId()==null || "".equals(farm.getFarmId()))	return result;
    	if(member==null || member.getMemberId()==null || "".equals(member.getMemberId()))	return result;
    	
    	String hql = "select * from t_seed where seedId in (select distinct itemId from t_farm_store where objectId=? and (farmId=? or buyerId=?))";
    	try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(Seed.class)
            		.setParameter(0, ShopController.SEED_ID)
                    .setParameter(1, farm.getFarmId())
                    .setParameter(2, member.getMemberId());

            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }
    
    public static List<FarmStore> getSeedsInfoFromStore(Farm farm, Member member, String itemId) {
    	List<FarmStore> result = new ArrayList<FarmStore>();
    	if(farm==null || farm.getFarmId()==null || "".equals(farm.getFarmId()))	return result;
    	if(member==null || member.getMemberId()==null || "".equals(member.getMemberId()))	return result;
    	
    	String hql = "select * from t_farm_store where objectId=? and itemId=? and (farmId=? or buyerId=?)";
    	try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(FarmStore.class)
            		.setParameter(0, ShopController.SEED_ID)
            		.setParameter(1, itemId)
                    .setParameter(2, farm.getFarmId())
                    .setParameter(3, member.getMemberId());

            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }
    
}
