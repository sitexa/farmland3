package com.sitexa.user.service;

import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.data.MemberPictureDAO;
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
 * Date: 2009-4-4
 * Time: 23:09:18
 */
public class MemberPictureService {
    private static Log log = LogFactory.getLog(MemberPictureService.class);
    private static String watermarker = "sitexa.com";
    private static String mid_path = "m";

    public static MemberPicture getPicture(String picId) {
        MemberPictureDAO dao = new MemberPictureDAO();
        return dao.findById(picId);
    }

    @SuppressWarnings("unchecked")
    public static List<MemberPicture> getPictures(Member member) {
        MemberPictureDAO dao = new MemberPictureDAO();
        return dao.findByProperty("member", member);
    }

    public static void updatePicture(MemberPicture picture) throws BaseException {
        MemberPictureDAO dao = new MemberPictureDAO();
        dao.update(picture);
    }

    public static void updatePictures(List<MemberPicture> pictures) throws BaseException {
        MemberPictureDAO dao = new MemberPictureDAO();
        dao.saveOrUpdate(pictures);
    }

    public static void deletePicture(String picId) throws BaseException {
        try {
            MemberPictureDAO dao = new MemberPictureDAO();
            MemberPicture pic = dao.findById(picId);
//            AppConfig cfg = new AppConfig();
            String rootPath = AppConfig.getProperty("struts.multipart.saveDir");
            String filename = rootPath + File.separator + mid_path + File.separator +
                    pic.getMember().getMemberId() + File.separator + pic.getPicUrl();
            ImageUtil.deleteFromFileSystem(filename);
            dao.delete(pic);
        } catch (Exception e) {
            log.error(e);
            BaseException.threw(e);
        }
    }

    public static void createPictures(List<MemberPicture> pictures,
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

                MemberPicture pic = pictures.get(i);
                File upload = uploads.get(i);
                String uploadFileName = uploadFileNames.get(i);

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
            BaseException.threw(e);
        }
    }

    /**
     * Member pictures save to /uploads/m/memberId/,while site pictures save to /uploads/s/siteId/
     * and post picture save to /uploads/p/postId/
     *
     * @param image          - image
     * @param upload         - upload file
     * @param uploadFileName - upload file name
     * @param pic            - member picture
     * @return String - file name
     */
    private static String save2Filesystem(Image image, File upload, String uploadFileName, MemberPicture pic) {
        String path = upload.getParent() + File.separator + mid_path + File.separator + pic.getMember().getMemberId();

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
