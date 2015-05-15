package com.sitexa.farm.rest.admin.work;

import com.sitexa.farm.data.*;
import com.sitexa.farm.service.FarmService;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import org.hibernate.Query;
import org.hibernate.Session;

import java.text.DateFormat;
import java.util.*;

public class WorkService {

    public static void main(String[] args) {
        Date dt = new Date();
        System.out.println("dt.toString() = " + dt.toString());
        String s = DateFormat.getDateInstance().format(dt);
        System.out.println("s = " + s);
    }

    public static List<Farm> getFarmsByLand(Land land) {
        List<Farm> result;
        String sql = "select * from v_farm where landId=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).addEntity(Farm.class).setParameter(0, land);

        return query.list();
    }

    public static boolean farmingWork(String farmingId) {
        boolean success = false;
        FarmingDAO dao = new FarmingDAO();
        Farming farming = dao.findById(farmingId);
        if (farming != null) {
            farming.setState("1");
            dao.save(farming);
            success = true;
        }
        return success;
    }

    public static List getCrops(Farm farm, Page page) {
        if (farm != null && !"".equals(farm.getFarmId())) {
            String hql = " from Crops where farm=? order by startDate desc";
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql).setParameter(0, farm);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);
            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            return query.list();
        }
        return null;
    }

    public static List<Farming> getFarmingByLand(Land land, Page page) {

        String hql = " from Farming as model where model.paymode='1' and (model.state is null or model.state <>'1') and model.farm.land.landId=?  order by model.startTime asc ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, land.getLandId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        return query.list();
    }

    public static List getFarmingByFarm(Farm farm, Page page) {
        if (farm == null || "".equals(farm.getFarmId())) return null;
        String hql = "from Farming as model where model.farm=? and model.paymode='1' and (model.state<>'1' or model.state is null) order by model.seed.seedId";

        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);
        query.setParameter(0, farm);

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);
        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        List result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List getCropsList(Farm farm, Page page) {
        if (farm == null || "".equals(farm.getFarmId())) return null;
        List<Crops> cropses = getCrops(farm, page);
        if (cropses == null) return null;
        List cropsList = new ArrayList();
        FarmingDAO dao = new FarmingDAO();
        Map crops = new HashMap();
        for (Crops c : cropses) {
            crops.put("seedId", c.getSeed().getSeedId());
            crops.put("seedName", c.getSeed().getSeedName());
            crops.put("seedNumber", c.getSeedNumber());

            Farming farming = dao.findLastFarming(farm, c.getSeed());
            if (farming != null) {
                if (farming.getFaction() != null) {
                    crops.put("actionName", farming.getFaction().getActionName());
                }
                crops.put("startTime", farming.getStartTime());
                crops.put("state", farming.getState());
            } else {
                crops.put("startTime", null);
                crops.put("state", null);
            }
            cropsList.add(crops);
        }

        return cropsList;
    }

    public static boolean updateRemark(String farmingId, String remark) {
        if (farmingId == null || "".equals(farmingId)) return false;
        boolean success = false;
        FarmingDAO dao = new FarmingDAO();
        Farming farming = dao.findById(farmingId);
        if (farming != null) {
            farming.setRemark(remark);
            dao.save(farming);
            success = true;
        }
        return success;
    }
}
