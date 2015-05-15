package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.framework.config.StrutsConfig;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.util.DrawImage;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-10-13
 * Time: 14:56:21
 */
@SuppressWarnings("unchecked")
public class LandService {
    static private Log log = LogFactory.getLog(LandService.class);
    public static final String LAND_DIR = "l";
    private static final String TEMP_DIR = "temp";

    public static void main(String[] args) {
        Land land = getStarLand();
        System.out.println("land.getDescription() = " + land.getDescription());
    }


    public static List<Land> getNewLands(Site site, int cnt) {
        List<Land> result = new ArrayList<Land>();
        if (site == null) return getNewLands(cnt);
        String siteType = site.getType().getTypeId();
        String siteId = site.getSiteId();

        String sql = "select * from v_land_site where status='1' ";
        if ("1".equals(siteType)) {
            sql += " and country = '" + siteId + "'";
        } else if ("2".equals(siteType)) {
            sql += " and state = '" + siteId + "'";
        } else if ("3".equals(siteType)) {
            sql += " and city = '" + siteId + "'";
        } else if ("4".equals(siteType)) {
            sql += " and county = '" + siteId + "'";
        } else if ("5".equals(siteType)) {
            sql += " and town = '" + siteId + "'";
        } else if ("6".equals(siteType)) {
            sql += " and village = '" + siteId + "'";
        } else if ("9".equals(siteType)) {
            sql += " and siteId = '" + siteId + "'";
        }
        sql += " order by StartDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Land.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public static Land getStarLand() {
        String hql = "from Land where tag='1' order by startDate desc ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);
        int c = query.list().size();
        if (c > 0) return (Land) query.list().get(0);
        else return null;
    }

    public static List<Land> getNewLands(int cnt) {
        System.out.println("LandService.getNewLands");
        String hql = "from Land order by startDate desc ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setMaxResults(cnt);
        return query.list();
    }

    public static List<String> getLandTypes() {
        return Arrays.asList(Land.LAND_TYPES);
    }

    public static Land getById(String landId) {
        System.out.println("LandService.getById");
        if (landId == null || "".equals(landId)) return null;
        Land land = null;
        try {
            LandDAO dao = new LandDAO();
            land = dao.findById(landId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return land;
    }

    public static void create(Land land) {
        System.out.println("LandService.create");
        if (land == null) return;
        if (null == land.getLandId() || "".equals(land.getLandId()))
            land.setLandId(Sequence.getId());
        LandDAO dao = new LandDAO();
        dao.save(land);
    }

    public static void create(Land land, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        System.out.println("LandService.create");
        if (land == null) return;
        if (null == land.getLandId() || "".equals(land.getLandId()))
            land.setLandId(Sequence.getId());
        land.setStartDate(new Date());
        LandDAO dao = new LandDAO();
        dao.save(land);

        if (imgNames != null && imgNames.length > 0)
            createFarmPictures(land.getLandId(), imgNames, imgTitles, imgDescriptions);
    }

    public static void update(Land land) {
        System.out.println("LandService.update");
        if (land == null) return;
        LandDAO dao = new LandDAO();
        Land land1 = dao.findById(land.getLandId());

        land1.setLandLord(land.getLandLord());
        land1.setAddress(land.getAddress());
        land1.setDescription(land.getDescription());
        land1.setLandName(land.getLandName());
        Site site1 = SiteService.getSite(land.getSite().getSiteId());
        land1.setSite(site1);
        land1.setLandType(land.getLandType());
        land1.setLatitude(land.getLatitude());
        land1.setLongitude(land.getLongitude());
        dao.update(land1);
    }

    public static void delete(String landId) {
        LandDAO dao = new LandDAO();
        Land land = dao.findById(landId);
        if (land == null) return;
        Session session = HibernateSessionFactory.getSession();
        Transaction ts = session.beginTransaction();
        try {
            ts.begin();
            FarmDAO fDao = new FarmDAO();
            FarmPictureDAO fpDao = new FarmPictureDAO();
            LandPictureDAO lpDao = new LandPictureDAO();

            List<Farm> farmList = fDao.findByProperty("land", land);
            Iterator it = farmList.iterator();
            Farm farm;
            while (it.hasNext()) {
                farm = (Farm) it.next();
                fpDao.deleteByFarm(farm);
                fDao.delete(farm);
            }
            lpDao.deleteByLand(land);
            dao.delete(land);
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Land> getAllLand() {
        LandDAO dao = new LandDAO();
        return dao.findAll();
    }

    public static List<Land> getLands(Page page) {
        String hql = "from Land order by startDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);
        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);
        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * page.getPageSize());
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public static List<Land> getLandsBySite(Site site) {
        List<Land> result;
        String hql;

        String typeId = site.getType().getTypeId();
        if ("1".equals(typeId)) {
            hql = "select * from v_land_site where country=?";
        } else if ("2".equals(typeId)) {
            hql = "select * from v_land_site where state=?";
        } else if ("3".equals(typeId)) {
            hql = "select * from v_land_site where city=?";
        } else if ("4".equals(typeId)) {
            hql = "select * from v_land_site where county=?";
        } else {
            hql = " select * from v_land_site where siteId = ?";
        }
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(hql).addEntity(Land.class).setParameter(0, site.getSiteId());

        result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Land> getLandsBySite(Site site, Page page) {
        List<Land> result;
        String hql;

        String typeId = site.getType().getTypeId();
        if ("1".equals(typeId)) {
            hql = "select * from v_land_site where country=?";
        } else if ("2".equals(typeId)) {
            hql = "select * from v_land_site where state=?";
        } else if ("3".equals(typeId)) {
            hql = "select * from v_land_site where city=?";
        } else if ("4".equals(typeId)) {
            hql = "select * from v_land_site where county=?";
        } else {
            hql = " select * from v_land_site where siteId = ?";
        }
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(hql).addEntity(Land.class).setParameter(0, site.getSiteId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);
        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        result = query.list();
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Land> getLandByName(String name) {
        List<Land> result;
        Session sess = HibernateSessionFactory.getSession();
        try {
            Criteria c = sess.createCriteria(Land.class);
            c.add(Restrictions.like("landName", "%" + name + "%"));
            result = c.list();
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Land> getLandByName(String name, Page page) {
        List<Land> result;
        Session sess = HibernateSessionFactory.getSession();
        try {
            Criteria c = sess.createCriteria(Land.class);
            c.add(Restrictions.like("landName", "%" + name + "%"));

            int t = c.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            c.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            result = c.list();
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result;
    }
    //Begin		农场图片	--------------------------------------------------------------------------

    /**
     * @param id
     * @author leim 2009.11.23
     * 根据picId获取农场图片
     */
    public static LandPicture getLandPictureById(String id) {
        LandPictureDAO dao = new LandPictureDAO();
        return dao.findById(id);
    }

    /**
     * @param upload
     * @param uploadFileName
     * @param landId
     * @param title
     * @param description
     * @return jsonObj
     * @author leim 2009.11.25
     * 创建农场图片
     */
    public static JSONObject createLandPicture(File upload, String uploadFileName, String landId, String title, String description) {
        JSONObject jsonObj = null;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + LAND_DIR + File.separator + landId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            String newFilename = UUID.randomUUID().toString().replaceAll("-", "")
                    + FileUtil.getExtention(uploadFileName);
            //保存文件
            DrawImage.saveFile(upload, path, newFilename);

            //写入记录

            LandPicture landPic = new LandPicture();
            landPic.setPicId(Sequence.getId());
            landPic.setLand(LandService.getById(landId));
            landPic.setPicUrl(newFilename);
            landPic.setTitle(title);

            Image img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(upload)));
            byte[] imgData = ImageUtil.getCompressedImage(img);
            landPic.setAbbrev(imgData);

            landPic.setDescription(description);
            landPic.setPicTag(0);
            LandPictureDAO dao = new LandPictureDAO();
            dao.save(landPic);
            //返回json
            jsonObj = new JSONObject();
            jsonObj.put("picId", landPic.getPicId());
            jsonObj.put("title", landPic.getTitle());
            jsonObj.put("description", landPic.getDescription());
            jsonObj.put("picId", landPic.getPicId());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return jsonObj;
    }

    private static void createFarmPictures(String landId, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        String tempPath = StrutsConfig.getSaveDir();
        tempPath += File.separator + TEMP_DIR;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + LAND_DIR + File.separator + landId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            File file;
            String picId;
            LandPicture landPic;
            if (imgNames != null && imgNames.length > 0) {
                for (int i = 0; i < imgNames.length; i++) {
                    file = new File(tempPath, imgNames[i]);
                    if (file.exists()) {
                        picId = Sequence.getId();
                        createLandPicture(file, imgNames[i], landId, imgTitles[i], imgDescriptions[i]);
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    public static List<Member> getMembers(Land land) {
        String sql = "select distinct model.member from Farm as model where model.land =? and model.member is not null";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql);
        query.setParameter(0, land);
        return query.list();
    }

    public static List<Land> getLandsByMember(Member member) {
        List<Land> result = new ArrayList<Land>();
        if (member == null) return result;
        try {
            String sql = "select * from v_land_site where lordId=? order by startDate desc";
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Land.class).setParameter(0, member);
            result = query.list();
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
