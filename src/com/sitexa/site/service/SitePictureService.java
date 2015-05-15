package com.sitexa.site.service;

import com.sitexa.framework.Constants;
import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.data.SitePictureDAO;
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
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 10:28:59
 */
public class SitePictureService {

    private static Log log = LogFactory.getLog(SitePicture.class);
    private static String mid_path = "s";

    public static SitePicture getPicture(String picId) {
        SitePictureDAO dao = new SitePictureDAO();
        return dao.findById(picId);
    }

    @SuppressWarnings("unchecked")
    public static List<SitePicture> getPictures(Site site) {
        SitePictureDAO dao = new SitePictureDAO();
        return dao.findByProperty("site", site);
    }

    public static void updatePicture(SitePicture picture) {
        SitePictureDAO dao = new SitePictureDAO();
        SitePicture pic = dao.findById(picture.getPicId());
        pic.setPicTitle(picture.getPicTitle());
        pic.setDescription(picture.getDescription());
        dao.update(pic);
    }

    public static void updatePictures(List<SitePicture> pictures) {
        System.out.println("SitePictureService.updatePictures");
        SitePictureDAO dao = new SitePictureDAO();
        dao.saveOrUpdate(pictures);
    }

    public static void deletePicture(String picId) throws BaseException {
        try {
            System.out.println("SitePictureService.deletePicture");
            SitePictureDAO dao = new SitePictureDAO();
            SitePicture pic = dao.findById(picId);
            deleteFromFilesystem(pic);
            dao.delete(pic);
        } catch (Exception e) {
            log.error(e);
            BaseException.threw(e);
        }
    }

    public static void createPictures(List<SitePicture> pictures,
                                      List<File> uploads,
                                      List<String> uploadFileNames,
                                      List<String> uploadContentTypes)
            throws BaseException {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < uploads.size(); i++) {

                if (!acceptType(uploadContentTypes.get(i))) continue;

                SitePicture pic = pictures.get(i);
                File upload = uploads.get(i);
                String uploadFileName = uploadFileNames.get(i);

                Image image = ImageUtil.marker(upload, Constants.WATERMARKER);
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
            BaseException.threw(e);
        }
    }

    private static String save2Filesystem(Image image, File upload, String uploadFileName, SitePicture pic) {
        String path = upload.getParent() + File.separator + mid_path + File.separator + pic.getSite().getSiteId();

        String filename = UUID.randomUUID().toString().replaceAll("-", "")
                + FileUtil.getExtention(uploadFileName);

        File dstFile = new File(path + File.separator + filename);

//      FileUtil.copyFile(file, dstFile);
        try {
            ImageUtil.writeImage(image, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }
        return filename;
    }

    private static void deleteFromFilesystem(SitePicture picture) {
//        AppConfig cfg = new AppConfig();
        String rootPath = AppConfig.getProperty("struts.multipart.saveDir");
        String filename = rootPath + File.separator + mid_path + File.separator +
                picture.getSite().getSiteId() + File.separator + picture.getPicUrl();
        ImageUtil.deleteFromFileSystem(filename);
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
