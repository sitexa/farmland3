package com.sitexa.farm.service;

import com.sitexa.farm.data.*;
import com.sitexa.framework.Constants;
import com.sitexa.framework.config.StrutsConfig;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.util.DrawImage;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.site.data.Site;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Member;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: xnpeng
 * Date: 2009-10-13
 * Time: 18:03:02
 */
@SuppressWarnings("unchecked")
public class FarmService {
    private static Log log = LogFactory.getLog(FarmService.class);
    private static final String TEMP_DIR = "temp";
    private static final String FARM_DIR = "f";
    private static final int UNIT_PRICE = 120;

    public static void main(String[] args) {

        String landId = "1000445";
        Page page = new Page(10);
        Land land = LandService.getById(landId);

        List list = getFarmByLand(land, page);
        for (int i = 0; i < list.size(); i++) {
            Farm farm = (Farm) list.get(i);
            System.out.println("farm.getFarmName() = " + farm.getFarmName());
        }

        System.out.println("list.size() = " + list.size());

    }

    public static List<Farm> searchFarms(Page page) {
        List<Farm> result = new ArrayList<Farm>();
        String hql = "from Farm order by rentDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);

        try {
            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Farm> searchFarms(Land land, Page page) {
        String landId;
        if (land == null) landId = null;
        else landId = land.getLandId();
        return searchFarms(landId, page);
    }

    public static List<Farm> searchFarms(String landId, Page page) {
        List<Farm> result = new ArrayList<Farm>();
        String hql;
        if (landId != null && !"".equals(landId)) {
            hql = " from Farm where land.landId=? order by rentDate desc ";
        } else {
            hql = " from Farm order by rentDate desc ";
        }

        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);

        if (landId != null && !"".equals(landId)) {
            query.setParameter(0, landId);
        }

        try {
            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Farm> searchNewFarms(Site site, Land land, Page page) {
        List<Farm> result = new ArrayList<Farm>();
        String sql = "select * from v_farm_site where memberId is not null ";
        if (land != null) {
            sql += "and landId='" + land.getLandId() + "' ";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

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
        }
        sql += "order by rentDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Farm.class);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public static List<Farm> searchFarms(Site site, Land land, Page page) {
        List<Farm> result = new ArrayList<Farm>();
        String sql = "select * from v_farm_site where memberId is not null ";
        if (land != null) {
            sql += "and landId='" + land.getLandId() + "' ";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

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
        }
        sql += "order by rentDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Farm.class);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public static List<Farm> getNewFarms(Site site, Land land, int cnt) {
        List<Farm> result = new ArrayList<Farm>();
        String sql = "select * from v_farm_site where memberId is not null ";
        if (land != null) {
            sql += "and landId='" + land.getLandId() + "' ";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

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
        } else {
            return getNewFarms(cnt);
        }

        sql += "order by rentDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Farm.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getNewFarms(int cnt) {
        System.out.println("FarmService.getNewFarms");
        String hql = "from Farm as model where model.member is not null order by rentDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setMaxResults(cnt);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getHotFarms(int cnt) {
        String hql = "from Farm as model where model.member is not null order by score desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setMaxResults(cnt);
        return query.list();
    }

    public static List<FarmPicture> getHotFarmPictures(Site site, Land land, int cnt) {
        List<FarmPicture> result = new ArrayList<FarmPicture>();

        String sql = "select * from (select *,row_number() over (partition by farmid order by farmid) as rn from v_farm_pic) as t ";
        sql += "  where rn=1 ";

        if (land != null) {
            sql += " and landId='" + land.getLandId() + "'";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

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
        } else {
            return getHotFarmPictures(cnt);
        }

        sql += " order by score desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(FarmPicture.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<FarmPicture> getHotFarmPictures(int cnt) {
        System.out.println("FarmService.getHotFarmPictures");
        String sql = "select * from (select *,row_number() over (partition by farmid order by farmid) as rn from v_farm_pic) as t ";
        sql += "  where rn=1 order by score desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).addEntity(FarmPicture.class).setMaxResults(cnt);
        return query.list();
    }

    public static Farm getById(String farmId) {
        if (farmId == null) return null;
        FarmDAO dao = new FarmDAO();
        return dao.findById(farmId);
    }

    public static void setScore(String farmId, int point) {
        if (farmId == null) return;
        FarmDAO dao = new FarmDAO();
        Farm farm = dao.findById(farmId);
        farm.setScore(farm.getScore() + point);
        dao.update(farm);
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getFarmBySite(Site site) {
        List<Farm> result = new ArrayList<Farm>();
        if (site == null) return result;

        String typeId = site.getType().getTypeId();
        String hql = " from Farm where site = ? ";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(hql).setParameter(0, site);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getFarmByMember(Member member) {
        List<Farm> result = new ArrayList<Farm>();
        if (member == null) return result;

        String hql = "from Farm as model where model.member = ?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getFriendFarms(Member member) {
        List<Farm> result = new ArrayList<Farm>();
        if (member == null) return result;

        String sql = "select * from t_farm where memberid in (select friendid from t_friend where memberid=? and status=1)";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).addEntity(Farm.class).setParameter(0, member.getMemberId());
        return query.list();
    }

    public static void create(Farm farm) {
        System.out.println("FarmService.create");
        if (farm == null) return;

        if (null == farm.getFarmId() || "".equals(farm.getFarmId()))
            farm.setFarmId(Sequence.getId());

        FarmDAO dao = new FarmDAO();
        dao.save(farm);
    }

    /**
     * 填写:ID,编号,面积,价格,land,site
     *
     * @param farms;
     */
    public static void createFarms(List<Farm> farms) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < farms.size(); i++) {
                Farm farm = farms.get(i);
                farm.setFarmId(Sequence.getId());
                farm.setSite(farm.getLand().getSite());
                farm.setRentPrice(farm.getAcreage() * UNIT_PRICE + "");
                session.saveOrUpdate(farm);
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

    /**
     * 修改编号,面积,价格.
     *
     * @param farms ;
     */
    public static void updateFarms(List<Farm> farms) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < farms.size(); i++) {
                Farm farm = farms.get(i);
                Farm f = (Farm) session.get(Farm.class, farm.getFarmId());

                f.setFarmNo(farm.getFarmNo());
                f.setAcreage(farm.getAcreage());
                f.setRentPrice(farm.getAcreage() * UNIT_PRICE + "");

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

    public static void create(Farm farm, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        if (farm == null) return;

        if (null == farm.getFarmId() || "".equals(farm.getFarmId()))
            farm.setFarmId(Sequence.getId());
        FarmDAO dao = new FarmDAO();
        dao.save(farm);
        if (imgNames != null && imgNames.length > 0)
            FarmService.createFarmPictures(farm.getFarmId(), imgNames, imgTitles, imgDescriptions);
    }

    public static void update(Farm farm) {
        if (farm == null) return;
        FarmDAO dao = new FarmDAO();

        Farm farm1 = dao.findById(farm.getFarmId());
        farm1.setFarmNo(farm.getFarmNo());
        farm1.setFarmName(farm.getFarmName());
        farm1.setCoordination(farm.getCoordination());
        farm1.setAcreage(farm.getAcreage());
//        farm1.setRentPrice(farm.getAcreage() * UNIT_PRICE + "");
        farm1.setRentPrice(farm.getRentPrice());
        farm1.setRemark(farm.getRemark());
        farm1.setPloughStatus(farm.getPloughStatus());
        dao.update(farm1);
    }

    public static Farm updateSlogan(Farm farm) {
        if (farm == null) return null;
        FarmDAO dao = new FarmDAO();

        Farm farm1 = dao.findById(farm.getFarmId());
        farm1.setFarmName(farm.getFarmName());
        farm1.setRemark(farm.getRemark());
        farm1.setSlogan(farm.getSlogan());
        dao.update(farm1);
        return farm1;
    }

    public static Farm updateNameAndSlogan(Farm farm) {
        if (farm == null) return null;
        FarmDAO dao = new FarmDAO();

        Farm farm1 = dao.findById(farm.getFarmId());
        farm1.setFarmName(farm.getFarmName());
        farm1.setSlogan(farm.getSlogan());
        dao.update(farm1);
        return farm1;
    }

    public static void delete(String id) {
        if (id == null || "".equals(id)) return;
        FarmDAO dao = new FarmDAO();
        Farm farm1 = dao.findById(id);
        dao.delete(farm1);
    }

    //Begin		农庄图片

    /**
     * @param id
     * @author leim 2009.11.23
     * 根据picId获取农庄图片
     */
    public static FarmPicture getFarmPictureById(String id) {
        FarmPictureDAO dao = new FarmPictureDAO();
        return dao.findById(id);
    }

    /**
     * @param farmPicture
     * @author leim 2009.11.23
     * 保存农庄图片
     */
    public static void saveFarmPicture(FarmPicture farmPicture) {
        if (farmPicture == null) return;
        FarmPictureDAO dao = new FarmPictureDAO();
        dao.save(farmPicture);
    }

    /**
     * 保存临时图片
     *
     * @param upload
     * @param uploadFileName
     * @return jsonObj
     * @author leim    2009.12.23
     */
    public static JSONObject uploadImgTemp(File upload, String uploadFileName) {
        String newFileName = "";
        String path = StrutsConfig.getSaveDir();
        path += File.separator + TEMP_DIR;
        newFileName = UUID.randomUUID().toString().replaceAll("-", "")
                + FileUtil.getExtention(uploadFileName);
        //保存文件
        DrawImage.saveFile(upload, path, newFileName);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("newFileName", newFileName);
        jsonObj.put("oldFileName", uploadFileName);
        return jsonObj;
    }

    /**
     * @param upload
     * @param uploadFileName
     * @param farmId
     * @param title
     * @param description
     * @return jsonObj
     * @author leim 2009.11.25
     * 创建农场图片
     */
    public static JSONObject createFarmPicture(File upload, String uploadFileName, String farmId, String title, String description) {
        System.out.println("FarmService.createFarmPicture");
        JSONObject jsonObj = null;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + FARM_DIR + File.separator + farmId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String newFilename = UUID.randomUUID().toString().replaceAll("-", "")
                    + FileUtil.getExtention(uploadFileName);
            String filename = path + File.separator + newFilename;

            Image image = ImageUtil.marker(upload, Constants.WATERMARKER);
            ImageUtil.writeImage(image, filename);

            FarmPicture farmPic = new FarmPicture();
            farmPic.setPicId(Sequence.getId());
            farmPic.setFarm(FarmService.getById(farmId));
            farmPic.setPicUrl(newFilename);
            farmPic.setTitle(title);

            Image img = ImageUtil.abbrev(image);
            byte[] imgData = ImageUtil.getCompressedImage(img);
            farmPic.setAbbrev(imgData);
            farmPic.setDescription(description);
            FarmService.saveFarmPicture(farmPic);
            //返回json
            jsonObj = new JSONObject();
            jsonObj.put("picId", farmPic.getPicId());
            jsonObj.put("title", farmPic.getTitle());
            jsonObj.put("description", farmPic.getDescription());
            jsonObj.put("farmId", farmId);
            jsonObj.put("picUrl", farmPic.getPicUrl());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return jsonObj;
    }

    public static void createFarmPictures(String farmId, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        String tempPath = StrutsConfig.getSaveDir();
        tempPath += File.separator + TEMP_DIR;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + FARM_DIR + File.separator + farmId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            File file;
            if (imgNames != null && imgNames.length > 0) {
                for (int i = 0; i < imgNames.length; i++) {
                    file = new File(tempPath, imgNames[i]);
                    if (file.exists()) {
                        createFarmPicture(file, imgNames[i], farmId, imgTitles[i], imgDescriptions[i]);
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    /**
     * @param farmPicture
     * @author leim 2009.11.23
     * 删除农庄图片记录
     */
    public static void delFarmPicture(FarmPicture farmPicture) {
        System.out.println("FarmService.delFarmPicture");
        if (farmPicture == null) return;
        FarmPictureDAO dao = new FarmPictureDAO();
        dao.delete(farmPicture);
    }

    /**
     * @param id
     * @author leim 2009.11.23
     * 根据图片ID删除农庄图片(数据库记??图片文件)
     */
    public static boolean delFarmPictureById(String id) {
        boolean success = false;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + FARM_DIR;
        if (!"".equals(id)) {
            FarmPicture farmPicture = FarmService.getFarmPictureById(id);
            if (farmPicture != null) {
                Session session = HibernateSessionFactory.getSession();
                Transaction tx = session.beginTransaction();
                tx.begin();
                //删除数据记录
                FarmService.delFarmPicture(farmPicture);
                //删除图片文件
                path += File.separator + farmPicture.getFarm().getFarmId() + File.separator + farmPicture.getPicUrl();
                File imgFile = new File(path);
                if (imgFile.exists() && imgFile.isFile()) {
                    imgFile.delete();
                }
                success = true;
                tx.commit();
            }
        }
        return success;
    }

    /**
     * @param farmPicture
     * @author leim 2009.11.23
     * 更新农庄图片记录
     */
    public static void updateFarmPicture(FarmPicture farmPicture) {
        System.out.println("FarmService.updateFarmPicture");
        if (farmPicture == null) return;
        FarmPictureDAO dao = new FarmPictureDAO();
        FarmPicture pic = dao.findById(farmPicture.getPicId());
        if (pic != null) {
            pic.setTitle(farmPicture.getTitle());
            pic.setDescription(farmPicture.getDescription());
            dao.update(pic);
        }
    }

    //End		农庄图片

    public static void updateFarmPictures(List<FarmPicture> pictures) {
        FarmPictureDAO dao = new FarmPictureDAO();
        dao.saveOrUpdate(pictures);
    }

    @SuppressWarnings("unchecked")
    public static List<Farm> getJoinedFarms(Land land) {
        String hql = " from Farm as model where model.land=? and model.member is not null";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, land);
        return query.list();
    }

    public static List<Farm> getFarmByName(String name, Page page) {
        String hql = " from Farm as model where model.farmName like ? order by rentDate desc";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, "%" + name + "%");
        //Criteria c = sess.createCriteria(Farm.class);
        //c.add(Restrictions.like("farmName", "%" + name + "%"));

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        return query.list();
    }

    /**
     * 获取已购买农庄列表；
     *
     * @param land;
     * @param page;
     * @return farm list;
     */
    public static List<Farm> getFarmByLand(Land land, Page page) {
        String hql = " from Farm as model where model.member<>null and  model.land = ? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, land);
        //Criteria c = sess.createCriteria(Farm.class);
        //c.add(Restrictions.like("farmName", "%" + name + "%"));

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        return query.list();
    }

    /**
     * 获取委托操作而且未完成的农庄列表；
     *
     * @param land;
     * @param page  ;
     * @return farm list.
     */
    public static List<Farm> getFarmByLand2(Land land, Page page) {
        String sql = "select distinct farmid from t_farming where paymode='1' " +
                " and (state is null or state<>'1') and farmId in (select farmid from t_farm where landId=?)";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createSQLQuery(sql).setParameter(0, land.getLandId());

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));

        List list = query.list();
        String idList = "";
        for (Object aList : list) {
            String s = (String) aList;
            idList += "'" + s + "',";
        }
        if (idList.length() > 0) idList = idList.substring(0, idList.length() - 1);

        sql = "select * from t_farm as model where model.farmId in(" + idList + ") order by model.farmNo ";
        query = session.createSQLQuery(sql).addEntity(Farm.class);

        return query.list();
    }

    /**
     * 获取某农场所有农庄
     *
     * @param land;
     * @param page;
     * @return farm list;
     */
    public static List<Farm> getFarmByLand3(Land land, Page page) {
        String hql = " from Farm as model where model.land = ? ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, land);

        int t = query.list().size();
        int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
        page.setTotal(tot);

        query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
        return query.list();
    }


    public static List<Farm> searchFarms(String word, Page page, boolean enter) {
        List<Farm> result = new ArrayList<Farm>();
        String sql = "select * from v_farm_site where 1=1";
        if (enter) {
            sql += " and memberId is not null ";
        }
        if (word != null && !"".equals(word)) {
            sql += " and farmName like '%" + word + "%' ";
        }
        sql += " order by rentDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Farm.class);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public static void updateRentPrice(Farm farm) {
        if (farm == null || farm.getFarmId() == null || farm.getRentPrice() == null) return;
        FarmDAO dao = new FarmDAO();
        Farm f = dao.findById(farm.getFarmId());
        if (f != null) {
            f.setRentMode(farm.getRentMode());
            f.setRentPrice(farm.getRentPrice());
            dao.update(f);
        }
    }
}