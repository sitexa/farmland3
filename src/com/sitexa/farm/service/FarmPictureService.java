package com.sitexa.farm.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.FarmPicture;
import com.sitexa.farm.data.FarmPictureDAO;
import com.sitexa.framework.Constants;
import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.site.data.SitePicture;
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
 * Date: 2010-4-18
 * Time: 11:55:29
 */
public class FarmPictureService {

    private static Log log = LogFactory.getLog(SitePicture.class);
    private static String mid_path = "f";

    public static FarmPicture getPicture(String picId) {
        FarmPictureDAO dao = new FarmPictureDAO();
        return dao.findById(picId);
    }

    @SuppressWarnings("unchecked")
    public static List<FarmPicture> getPictures(Farm farm) {
        FarmPictureDAO dao = new FarmPictureDAO();
        return dao.findByProperty("farm", farm);
    }

    public static void updatePicture(FarmPicture picture) {
        FarmPictureDAO dao = new FarmPictureDAO();
        FarmPicture pic = dao.findById(picture.getPicId());
        pic.setTitle(picture.getTitle());
        pic.setDescription(picture.getDescription());
        dao.update(pic);
    }

    public static void updatePictures(List<FarmPicture> pictures) {
        System.out.println("SitePictureService.updatePictures");
        FarmPictureDAO dao = new FarmPictureDAO();
        dao.saveOrUpdate(pictures);
    }

    public static void deletePicture(String picId) throws BaseException {
        try {
            System.out.println("SitePictureService.deletePicture");
            FarmPictureDAO dao = new FarmPictureDAO();
            FarmPicture pic = dao.findById(picId);
            deleteFromFilesystem(pic);
            dao.delete(pic);
        } catch (Exception e) {
            log.error(e);
            BaseException.threw(e);
        }
    }

    public static void createPictures(List<FarmPicture> pictures,
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

                FarmPicture pic = pictures.get(i);
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

    private static String save2Filesystem(Image image, File upload, String uploadFileName, FarmPicture pic) {
        String path = upload.getParent() + File.separator + mid_path + File.separator + pic.getFarm().getFarmId();

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

    private static void deleteFromFilesystem(FarmPicture picture) {
        String rootPath = AppConfig.getProperty("struts.multipart.saveDir");
        String filename = rootPath + File.separator + mid_path + File.separator +
                picture.getFarm().getFarmId() + File.separator + picture.getPicUrl();
        ImageUtil.deleteFromFileSystem(filename);
    }

    public static boolean acceptType(String type) {
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));

    }
}
