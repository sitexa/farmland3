package com.sitexa.farm.service;

import com.sitexa.farm.data.Faction;
import com.sitexa.farm.data.FactionDAO;
import com.sitexa.farm.data.Land;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.sys.Sequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 11:26:44
 */
@SuppressWarnings("unchecked")
public class FactionService {
    private static Log log = LogFactory.getLog(FactionService.class);
    private static final String TEMP_DIR = "temp";

    public static Faction getById(String factionId) {
        FactionDAO dao = new FactionDAO();
        return dao.findById(factionId);
    }

    public static Faction getByLandAndAction(String landId, String actionId) {
        if (landId == null || "".equals(landId) || actionId == null || "".equals(actionId)) return null;
        FactionDAO dao = new FactionDAO();
        return dao.findByLandAndAction(landId, actionId);
    }

    public static List<Faction> getFactions(Land land) {
        if (land == null) return null;
        FactionDAO dao = new FactionDAO();
        return dao.findByProperty("land", land);
    }

    public static List<Faction> getFactions(Land land, Page page) {
        List<Faction> result = new ArrayList<Faction>();
        if (land == null) return result;
        String hql = "from Faction where land.landId=? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, land.getLandId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);
        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        result = query.list();
        return result;
    }

    public static List<Faction> getFactionsInUse(Land land) {
        if (land == null) return null;
        FactionDAO dao = new FactionDAO();
        return dao.findByLand(land.getLandId(), "1");
    }

    public static void create(Faction faction) {
        if (faction == null) return;
        if (faction.getLand() == null) return;
        faction.setFactionId(Sequence.getId());
        FactionDAO dao = new FactionDAO();
        dao.save(faction);
    }

    public static void update(Faction faction) {
        if (faction == null || faction.getFactionId() == null || faction.getLand() == null) return;
        FactionDAO dao = new FactionDAO();
        Faction f = dao.findById(faction.getFactionId());
        if (f == null) return;
        f.setActionName(faction.getActionName());
        f.setContents(faction.getContents());
        f.setExpense(faction.getExpense());
        f.setMaterials(faction.getMaterials());
        f.setRemark(faction.getRemark());
        if (faction.getImg() != null) {
            f.setImg(faction.getImg());
        }
        if (!"1".equals(faction.getStatus()))
            f.setStatus("");
        dao.update(f);
    }

    public static void update(List<Faction> factions) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < factions.size(); i++) {
                Faction faction = factions.get(i);
                Faction f = (Faction) session.get(Faction.class, faction.getFactionId());

                f.setActionName(faction.getActionName());
                f.setContents(faction.getContents());
                f.setExpense(faction.getExpense());
                f.setMaterials(faction.getMaterials());

                //todo...

                session.saveOrUpdate(f);
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

    public static void delete(Faction faction) {
        if (faction == null) return;
        FactionDAO dao = new FactionDAO();
        if (dao.getCropsStatusCount(faction) + dao.getFarmingCount(faction) == 0) {
            dao.delete(faction);
        } else {
            System.out.println("使用中，不删除。");
        }
    }

    public static boolean updateImg(File upload, String uploadFileName, String svcId) {
        boolean success = false;
        FactionDAO dao = new FactionDAO();
        Faction service = dao.findById(svcId);
        if (service != null) {
            Image img;
            try {
                img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(upload)));
                byte[] imgData = ImageUtil.getCompressedImage(img);
                service.setImg(imgData);
                dao.update(service);
                success = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public static void main(String[] args) {
        Faction f = getByLandAndAction("1000445", "1001566");
        System.out.println("f.getFactionId() = " + f.getFactionId());
    }

}
