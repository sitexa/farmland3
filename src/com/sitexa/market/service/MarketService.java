package com.sitexa.market.service;

import com.sitexa.farm.data.Land;
import com.sitexa.framework.config.StrutsConfig;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.util.DrawImage;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.market.data.*;
import com.sitexa.site.data.Site;
import com.sitexa.sys.Sequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-10-28
 * Time: 10:59:38
 */
@SuppressWarnings("uncheked")
public class MarketService {
    private static Log log = LogFactory.getLog(MarketService.class);
    private static final String MARKET_DIR = "k";
    private static final String TEMP_DIR = "temp";

    public static Market getMarket(String itemId) {
        MarketDAO dao = new MarketDAO();
        return dao.findById(itemId);
    }

    public static List<Market> getMarketBySite(Site site) {
        List<Market> result = new ArrayList<Market>();
        if (site == null) return result;

        String hql;
        String typeId = site.getType().getTypeId();
        if ("2".equals(typeId)) {
            hql = "select * from v_market_site where state=?";
        } else if ("3".equals(typeId)) {
            hql = "select * from v_market_site where city=?";
        } else if ("4".equals(typeId)) {
            hql = "select * from v_market_site where county=?";
        } else {
            hql = " select * from v_market_site where siteId = ?";
        }

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(Market.class).setParameter(0, site);
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    public static List<MarketCategory> getMarketCategory() {
        MarketCategoryDAO dao = new MarketCategoryDAO();
        return dao.findAll();
    }

    public static MarketCategory getMarketCategory(String cateId) {
        if (cateId == null || "".equals(cateId)) return null;
        MarketCategoryDAO dao = new MarketCategoryDAO();
        return dao.findById(cateId);
    }

    public static void save(Market market) {
        if (market == null) return;

        try {
            market.setItemId(Sequence.getId());
            market.setCreateDate(new Date());

            //String cateId = market.getCategory().getCateId();
            //MarketCategory cate = getMarketCategory(cateId);
            //market.setCategory(cate);

            MarketDAO dao = new MarketDAO();
            dao.save(market);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void save(Market market, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        if (market == null) return;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            //save market
            market.setItemId(Sequence.getId());
            market.setCreateDate(new Date());
            MarketDAO dao = new MarketDAO();
            dao.save(market);
            //save marketPicture
            if (imgNames != null && imgNames.length > 0)
                MarketService.createMarketPictures(market.getItemId(), imgNames, imgTitles, imgDescriptions);
            tx.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void update(Market market) {
        if (market == null) return;

        MarketDAO dao = new MarketDAO();
        try {
            Market market1 = dao.findById(market.getItemId());
            market1.setItemTitle(market.getItemTitle());
            market1.setContents(market.getContents());

            dao.update(market1);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
    }

    public static void incVwCnt(Market market) {
        if (market == null) return;

        MarketDAO dao = new MarketDAO();
        try {
            Market market1 = dao.findById(market.getItemId());
            market1.setVwCnt(market1.getVwCnt() + 1);
            dao.update(market1);
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
    }

    public static void delete(String id) {
        if (id == null || "".equals(id)) return;
        MarketDAO dao = new MarketDAO();
        Market market = dao.findById(id);
        if (market == null) return;

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            Set<Market> children = market.getChildren();
            Iterator<Market> it1 = children.iterator();
            while (it1.hasNext()) {
                dao.delete(dao.findById(it1.next().getItemId()));
            }
            Set<MarketPicture> mps = market.getMarketPictures();
            Iterator<MarketPicture> it2 = mps.iterator();
            while (it2.hasNext()) {
                MarketService.delMarketPictureById(it2.next().getPicId());
            }
            dao.delete(market);
            tx.commit();
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
    }

    /**
     * @return result
     * @author leim        2008.11.25
     */
    public static List<Market> getMarkets(Page page, Site site, String itemType, String cateId) {
        List<Market> result = null;
        if (site == null) return result;

        String hql;
        String typeId = site.getType().getTypeId();
        if ("2".equals(typeId)) {
            hql = "select * from v_market_site where state=?";
        } else if ("3".equals(typeId)) {
            hql = "select * from v_market_site where city=?";
        } else if ("4".equals(typeId)) {
            hql = "select * from v_market_site where county=?";
        } else {
            hql = " select * from v_market_site where siteId = ?";
        }
        hql += " and itemType = ? and cateId = ? and parentId is null";
        hql += " order by createDate desc";
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(hql).addEntity(Market.class)
                    .setParameter(0, site)
                    .setParameter(1, itemType)
                    .setParameter(2, cateId);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setMaxResults(page.getPageSize()).setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            result = query.list();
        } catch (HibernateException he) {
            log.error(he);
        }
        return result;
    }

    /**
     * @param marketPicture
     * @author leim 2009.11.23
     * 保存Market图片
     */
    public static void saveMarketPicture(MarketPicture marketPicture) {
        System.out.println("MarketService.saveMarketPicture");
        if (marketPicture == null) return;
        System.out.println("MarketPicture = " + marketPicture);
        MarketPictureDAO dao = new MarketPictureDAO();
        dao.save(marketPicture);
    }

    /**
     * @param id
     * @author leim 2009.11.23
     * 根据picId获取Market图片
     */
    public static MarketPicture getMarketPictureById(String id) {
        System.out.println("MarketService.getMarketPictureById");
        MarketPictureDAO dao = new MarketPictureDAO();
        return dao.findById(id);
    }

    /**
     * @param id
     * @author leim 2009.11.23
     * 根据图片ID删除Market图片(数据库记录&图片文件)
     */
    public static boolean delMarketPictureById(String id) {
        boolean success = false;
        if (!"".equals(id)) {
            MarketPictureDAO dao = new MarketPictureDAO();
            MarketPicture marketPicture = dao.findById(id);
            if (marketPicture != null) {
                String path = StrutsConfig.getSaveDir() + File.separator + MARKET_DIR;
                Session session = HibernateSessionFactory.getSession();
                Transaction tx = session.beginTransaction();
                tx.begin();
                //删除数据记录
                dao.delete(marketPicture);
                //删除图片文件
                path += File.separator + marketPicture.getMarket().getItemId() + File.separator + marketPicture.getPicUrl();
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
     * @param upload
     * @param itemId
     * @param title
     * @param description
     * @return jsonObj
     * @author leim 2009.11.27
     * 创建Market图片
     */
    public static void createMarketPicture(File upload, String fileName, String itemId, String picId, String title, String description) {
        String path = StrutsConfig.getSaveDir();
        path += File.separator + MARKET_DIR + File.separator + itemId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            //保存文件
            DrawImage.saveFile(upload, path, fileName);

            //写入记录
            MarketPicture marketPic = new MarketPicture();
            marketPic.setPicId(picId);
            marketPic.setMarket(MarketService.getMarket(itemId));
            marketPic.setPicUrl(fileName);
            marketPic.setTitle(title);

            Image img = ImageUtil.abbrev(ImageIO.read(new FileInputStream(upload)));
            byte[] imgData = ImageUtil.getCompressedImage(img);
            marketPic.setAbbrev(imgData);

            marketPic.setDescription(description);

            MarketPictureDAO dao = new MarketPictureDAO();
            dao.save(marketPic);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
    }

    /**
     * @param itemId
     * @return jsonObj
     * @author leim 2009.11.27
     * 创建Market图片
     */
    public static void createMarketPictures(String itemId, String[] imgNames, String[] imgTitles, String[] imgDescriptions) {
        String tempPath = StrutsConfig.getSaveDir();
        tempPath += File.separator + TEMP_DIR;
        String path = StrutsConfig.getSaveDir();
        path += File.separator + MARKET_DIR + File.separator + itemId;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            File file;
            String picId;
            MarketPicture marketPic;
            if (imgNames != null && imgNames.length > 0) {
                for (int i = 0; i < imgNames.length; i++) {
                    file = new File(tempPath, imgNames[i]);
                    if (file.exists()) {
                        picId = Sequence.getId();
                        createMarketPicture(file, imgNames[i], itemId, picId, imgTitles[i], imgDescriptions[i]);
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
     * @param marketPicture
     * @author leim 2009.11.27
     * 更新Market图片记录
     */
    public static void updateMarketPicture(MarketPicture marketPicture) {
        System.out.println("MarketService.updateMarketPicture");
        if (marketPicture == null || marketPicture.getPicId() == null || "".equals(marketPicture.getPicId())) return;
        MarketPictureDAO dao = new MarketPictureDAO();
        MarketPicture marketPic = dao.findById(marketPicture.getPicId());
        if (marketPic == null) return;
        marketPic.setTitle(marketPicture.getTitle());
        marketPic.setDescription(marketPicture.getDescription());
        dao.update(marketPic);
    }

    public static List<Market> getNewMarkets(Site site, Land land, int cnt) {
        List<Market> result = new ArrayList<Market>();
        String sql = "select * from (select *,row_number() over (partition by itemId order by itemId) as rn from v_market_pic) as t ";
        sql += " where rn=1 ";
        if (land != null) {
            sql += "and landId='" + land.getLandId() + "'";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

            if ("2".equals(siteType)) {
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
            return getNewMarkets(cnt);
        }
        sql += " order by createDate desc ";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(Market.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }

        return result;
    }

    public static List<Market> getNewMarkets(int cnt) {
        String hql = "from Market where parent is null order by createDate desc ";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setMaxResults(cnt);
        return query.list();
    }

    public static List<MarketPicture> getNewMarketPictures(Site site, Land land, int cnt) {
        List<MarketPicture> result;
        String sql = "select * from (select *,row_number() over (partition by itemId order by itemId) as rn from v_market_pic) as t ";
        sql += " where rn=1 ";

        if (land != null) {
            sql += " and landId='" + land.getLandId() + "'";
        } else if (site != null) {
            String siteType = site.getType().getTypeId();
            String siteId = site.getSiteId();

            if ("2".equals(siteType)) {
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
            return getNewMarketPictures(cnt);
        }
        sql += " order by createDate desc";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createSQLQuery(sql).addEntity(MarketPicture.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static List<MarketPicture> getNewMarketPictures(int cnt) {
        List<MarketPicture> result;
        String sql = "select * from (select *,row_number() over (partition by itemId order by itemId) as rn from v_market_pic) as t ";
        sql += " where rn=1 order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createSQLQuery(sql).addEntity(MarketPicture.class);
            query.setMaxResults(cnt);
            result = query.list();
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
        return result;
    }

    public static void main(String[] args) {
        List newMarkets = MarketService.getNewMarkets(5);
        System.out.println("newMarkets.size() = " + newMarkets.size());
        List pics = getNewMarketPictures(5);
        System.out.println("pics.size() = " + pics.size());
    }
}
