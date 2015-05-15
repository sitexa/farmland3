package com.sitexa.farm.service;

import com.sitexa.farm.data.Land;
import com.sitexa.farm.data.LandPicture;
import com.sitexa.farm.data.LandPictureDAO;
import com.sitexa.framework.Constants;
import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.sys.Sequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * User: xnpeng
 * Date: 2010-4-21
 * Time: 14:01:47
 */
public class LandPictureService {

    private static Log log = LogFactory.getLog(LandPictureService.class);
    private static String mid_path = "l";

    public static LandPicture getPicture(String picId) {
        LandPictureDAO dao = new LandPictureDAO();
        return dao.findById(picId);
    }

    @SuppressWarnings("unchecked")
    public static List<LandPicture> getPictures(Land land) {
        LandPictureDAO dao = new LandPictureDAO();
        return dao.findByProperty("land", land);
    }

    public static void updatePicture(LandPicture picture) {
        LandPictureDAO dao = new LandPictureDAO();
        dao.update(picture);
    }

    public static void updatePictures(List<LandPicture> pictures) {
        LandPictureDAO dao = new LandPictureDAO();
        dao.saveOrUpdate(pictures);
    }

    public static void deletePicture(String picId) {
        try {
            LandPictureDAO dao = new LandPictureDAO();
            LandPicture pic = dao.findById(picId);
            String rootPath = AppConfig.getProperty("struts.multipart.saveDir");
            String filename = rootPath + File.separator + mid_path + File.separator +
                    pic.getLand().getLandId() + File.separator + pic.getPicUrl();
            ImageUtil.deleteFromFileSystem(filename);
            dao.delete(pic);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public static void createPictures(List<LandPicture> pictures,
                                      List<File> uploads,
                                      List<String> uploadFileNames,
                                      List<String> uploadContentTypes) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < uploads.size(); i++) {

                if (!acceptType(uploadContentTypes.get(i))) continue;

                LandPicture pic = pictures.get(i);
                File upload = uploads.get(i);
                String uploadFileName = uploadFileNames.get(i);
                String watermarker = Constants.WATERMARKER;
                Image image = ImageUtil.marker(upload, watermarker);
                String picUrl = save2Filesystem(image, upload, uploadFileName, pic);

                Image img = ImageUtil.abbrev(image);
                byte[] imgData = ImageUtil.getCompressedImage(img);

                pic.setPicId(Sequence.getId());
                pic.setPicUrl(picUrl);
                pic.setAbbrev(imgData);
                session.saveOrUpdate(pic);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * Land pictures save to /uploads/l/landId/,while site pictures save to /uploads/s/siteId/
     * and post picture save to /uploads/p/postId/
     *
     * @param image          - image
     * @param upload         - upload file
     * @param uploadFileName - upload file name
     * @param pic            - land picture
     * @return String - file name
     */
    private static String save2Filesystem(Image image, File upload, String uploadFileName, LandPicture pic) {
        String path = upload.getParent() + File.separator + mid_path + File.separator + pic.getLand().getLandId();

        String filename = UUID.randomUUID().toString().replaceAll("-", "")
                + FileUtil.getExtention(uploadFileName);

        File dstFile = new File(path + File.separator + filename);

        try {
            ImageUtil.writeImage(image, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        return filename;
    }

    private static boolean acceptType(String type) {
        System.out.println("type:" + type);
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));

    }
}
