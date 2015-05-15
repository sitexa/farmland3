package com.sitexa.farm.service;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.Seed;
import com.sitexa.farm.data.SeedDAO;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.Sequence;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-26
 * Time: 10:21:41
 */
public class SeedService {
    private static Log log = LogFactory.getLog(SeedService.class);

    public static Seed getById(String id) {
        if (id == null || "".equals(id)) return null;
        SeedDAO dao = new SeedDAO();
        return dao.findById(id);
    }

    public static List<Seed> getSeeds(Land land) {
        List<Seed> result = new ArrayList<Seed>();
        if (land == null) return result;
        SeedDAO dao = new SeedDAO();
        return dao.findByProperty("land", land);
    }

    //2010.5.14 lei

    public static List<Seed> getSeeds(Land land, Page page) {
        List<Seed> result = new ArrayList<Seed>();
        if (land == null) return result;
        String hql = "select * from t_seed where landId=?";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(Seed.class)
                    .setParameter(0, land);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            if (tot < page.getCurrent() && tot > 0) page.setCurrent(tot);
            page.setTotal(tot);

            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    public static void create(Seed seed) {
        if (seed == null || seed.getLand() == null) return;
        SeedDAO dao = new SeedDAO();
        seed.setSeedId(Sequence.getId());
        dao.save(seed);
    }

    public static void update(Seed seed) {
        if (seed == null || seed.getSeedId() == null || seed.getLand() == null) return;
        SeedDAO dao = new SeedDAO();
        Seed seed1 = dao.findById(seed.getSeedId());
        if (seed1 == null) return;
        seed1.setSeedName(seed.getSeedName());
        if (seed.getImg() != null) seed1.setImg(seed.getImg());
        seed1.setDescription(seed.getDescription());
        seed1.setPrice(seed.getPrice());
        seed1.setPlantTime(seed.getPlantTime());
        seed1.setHarvestTime(seed.getHarvestTime());
        seed1.setStatus(seed.getStatus());
        dao.update(seed1);
    }

    public static void update(List<Seed> seeds) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < seeds.size(); i++) {
                Seed svc = seeds.get(i);
                Seed s = (Seed) session.get(Seed.class, svc.getSeedId());

                s.setSeedName(svc.getSeedName());
                s.setDescription(svc.getDescription());
                s.setPrice(svc.getPrice());
                s.setPlantTime(svc.getPlantTime());

                //todo...

                session.saveOrUpdate(s);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public static void delete(Seed seed) {
        if (seed == null) return;
        SeedDAO dao = new SeedDAO();
        if (dao.getCropsCount(seed) + dao.getFarmingCount(seed) == 0) {
            dao.delete(seed);
        } else {
            System.out.println("使用中，不删除。");
        }
    }

    public static JSONObject getCrops(String seedId) {
        if (seedId == null || "".equals(seedId)) return null;
        JSONObject json = new JSONObject();
        SeedDAO dao = new SeedDAO();
        Seed seed = dao.findById(seedId);
        if (seed != null) {
            json.put("result", "1");
            json.put("seedId", seed.getSeedId());
            json.put("seedName", seed.getSeedName());
            json.put("price", seed.getPrice());
            json.put("uom", seed.getUom());
            json.put("description", seed.getDescription());
            return json;
        } else {
            json.put("msg", "没有数据");
        }
        return json;
    }

    public static JSONObject getJSONSeedByLand(String landId) {
        JSONObject json = new JSONObject();
        String sql = "from Seed where land.landId=? and status='1' order by seedId";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql).setParameter(0, landId);
        List list = query.list();
        for (Object aList : list) {
            Seed seed = (Seed) aList;
            json.put(seed.getSeedId(), seed.getSeedName());
        }
        return json;
    }

    public static void main(String[] args) {
        JSONObject json = getJSONSeedByLand("1000445");
        System.out.println("json = " + json);
    }
}
