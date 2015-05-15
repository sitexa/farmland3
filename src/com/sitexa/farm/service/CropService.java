package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.farm.rest.myfarm.ShopController;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberDAO;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-5-20
 * Time: 14:41:53
 */
public class CropService {

    public static String getByFarmAndSeed(String farmId, String seedId) {
        String hql = "from Crops where farm.farmId=? and seed.seedId=? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farmId).setParameter(1, seedId);
        Crops crops = (Crops) query.uniqueResult();
        if (crops != null) {
            return crops.getCropsId();
        } else {
            return "";
        }
    }

    public static JSONObject getJSONByFarmAndSeed(String farmId, String seedId) {
        if (farmId == null || seedId == null) return null;
        String hql = "from Crops where farm.farmId=? and seed.seedId=? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farmId).setParameter(1, seedId);
        Crops crops = (Crops) query.uniqueResult();
        JSONObject json = new JSONObject();
        if (crops != null) {
            json.put("result", "1");
            json.put("cropsId", crops.getCropsId());
            json.put("seedNumber", crops.getSeedNumber());
            SeedDAO dao = new SeedDAO();
            Seed seed = dao.findById(seedId);
            if (seed != null) {
                json.put("seedId", seed.getSeedId());
                json.put("seedName", seed.getSeedName());
                json.put("price", seed.getPrice());
                json.put("uom", seed.getUom());
                json.put("description", seed.getDescription());
                //System.out.println("seed.getDescription() = " + seed.getDescription());
            }
            return json;
        } else {
            SeedDAO dao = new SeedDAO();
            Seed seed = dao.findById(seedId);
            if (seed != null) {
                json.put("result", "2");
                json.put("seedId", seed.getSeedId());
                json.put("seedName", seed.getSeedName());
                json.put("price", seed.getPrice());
                json.put("uom", seed.getUom());
                json.put("description", seed.getDescription());
                //System.out.println("seed.getDescription() = " + seed.getDescription());
                return json;
            }
        }
        return json;
    }

    public static JSONObject getSeedInfo(String farmId, String memberId, String seedId, String type) {
        if (farmId == null || seedId == null) return null;
        JSONObject json = new JSONObject();
        if ("plant".equals(type)) {
            SeedDAO dao = new SeedDAO();
            Seed seed = dao.findById(seedId);
            FarmDAO dao1 = new FarmDAO();
            Farm farm = dao1.findById(farmId);
            MemberDAO dao2 = new MemberDAO();
            Member member = dao2.findById(memberId);
            Object[] object = StoreService.getGood(farm, member, ShopController.SEED_ID, seedId);
            if (object != null) json.put("seedNumber", object[2]);
            json.put("description", seed.getDescription());
        } else {
            String hql = "from Crops where farm.farmId=? and seed.seedId=? ";
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql).setParameter(0, farmId).setParameter(1, seedId);
            Crops crops = (Crops) query.uniqueResult();
            json.put("seedNumber", crops.getSeedNumber());
            SeedDAO dao = new SeedDAO();
            Seed seed = dao.findById(seedId);
            if (seed != null) {
                json.put("description", seed.getDescription());
            }
        }
        return json;
    }

    public static Crops getByFarmAndSeed(Farm farm, Seed seed) {
        String hql = "from Crops where farm=? and seed=? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, farm).setParameter(1, seed);
        return (Crops) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public static List<Crops> getByFarm(Farm farm) {
        String sql = "from Crops where farm=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql).setParameter(0, farm);
        return query.list();
    }

    public static List<Crops> getByFarm(Farm farm, Page page) {
        String sql = "from Crops where farm=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql).setParameter(0, farm);

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        return query.list();
    }

    public static JSONObject getJSONSeedByFarm(String farmId) {
        JSONObject json = new JSONObject();
        String sql = "from Crops where farm.farmId=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql).setParameter(0, farmId);
        List list = query.list();
        for (Object aList : list) {
            Crops crops = (Crops) aList;
            Seed seed = crops.getSeed();
            json.put(seed.getSeedId(), seed.getSeedName());
        }
        return json;
    }

    public static Crops getById(String cropsId) {
        CropsDAO dao = new CropsDAO();
        return dao.findById(cropsId);
    }

    public static List<Seed> getSeedsByFarm(Farm farm) {
        String sql = "from Crops where farm=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql).setParameter(0, farm);
        List list = query.list();
        List<Seed> result = new ArrayList<Seed>();
        for (Object aList : list) {
            Crops crops = (Crops) aList;
            result.add(crops.getSeed());
        }
        return result;
    }

    public static String saveRemark(String cropsId, String remark) {
        System.out.println("CropService.saveRemark");
        System.out.println("cropsId = " + cropsId);
        System.out.println("remark = " + remark);
        if (cropsId == null || "".equals(cropsId)) return "0";
        try {
            CropsDAO dao = new CropsDAO();
            Crops crops = dao.findById(cropsId);
            if (crops == null) return "0";
            crops.setRemark(remark);
            dao.update(crops);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

    public static void main(String[] args) {
        JSONObject json = getJSONSeedByFarm("1000451");
        System.out.println("json = " + json);
    }
}
