package com.sitexa.post.service;

import com.sitexa.framework.config.AppConfig;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.util.FileUtil;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.data.PostPictureDAO;
import com.sitexa.sys.Sequence;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * User: Oscar Peng, xnpeng@163.com
 * Date: 11-1-13
 * Time: 下午4:37
 */
//==============图片处理方法==================//
// 1, createPicture
// 2, acceptType
// 3, updatePicture
// 4, movePicture
// 5, deletePicture
// 6, save2FileSystem
public class PostPictureService {

    private static String mid_path = "p";
    private static String tmp_dir = "_tmp_";
    static String rootPath = AppConfig.getProperty("struts.multipart.saveDir");
    static String markPath = "logo.jpg";

    /**
     * 上传图片.
     *
     * @param pictures           - PostPicture
     * @param uploads            - file list
     * @param uploadFileNames    - string list
     * @param uploadContentTypes - string list
     * @throws com.sitexa.framework.exception.BaseException
     *                             - BaseException
     * @throws java.io.IOException - IO Exception
     */
    public static void createPictures(List<PostPicture> pictures, List<File> uploads, List<String> uploadFileNames, List<String> uploadContentTypes)
            throws Exception {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < uploads.size(); i++) {

                if (!acceptType(uploadContentTypes.get(i))) continue;

                PostPicture pic = pictures.get(i);
                File upload = uploads.get(i);
                String uploadFileName = uploadFileNames.get(i);

                File markFile = new File(rootPath + File.separator + markPath);
                //给图片加水印
                //Image image = ImageUtil.marker(upload, Constants.WATERMARKER);
                //加水印图
                Image image = ImageUtil.marker(upload, markFile);

                String dir = tmp_dir;
                if (pic.getPost() != null) {
                    dir = pic.getPost().getId();
                }
                String picUrl = save2Filesystem(image, upload, uploadFileName, dir);
                //生成缩略图
                Image img = ImageUtil.abbrev(image);
                byte[] imgData = ImageUtil.getCompressedImage(img);

                if (pic.getPicId() == null)
                    pic.setPicId(Sequence.newId("post"));
                pic.setPicUrl(picUrl);
                pic.setAbbrev(imgData);

                session.save(pic);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            session.close();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 将图片保存到文件系统中指定的目录下.
     *
     * @param image          - 图片对象
     * @param upload         - 图片文件
     * @param uploadFileName - 图片文件名
     * @param dir            - 目录名
     * @return 文件名, UUID
     * @throws com.sitexa.framework.exception.BaseException
     *          - 文件读写异常
     */
    private static String save2Filesystem(Image image, File upload, String uploadFileName, String dir) {
        String path = upload.getParent() + File.separator + mid_path + File.separator + dir;

        String filename = UUID.randomUUID().toString().replaceAll("-", "")
                + FileUtil.getExtention(uploadFileName);

        File dstFile = new File(path + File.separator + filename);

        try {
            ImageUtil.writeImage(image, dstFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filename;
    }

    /**
     * 可接受的图片类型.
     *
     * @param type - 类型名
     * @return - boolean
     */
    private static boolean acceptType(String type) {
        return ("image/gif".equalsIgnoreCase(type)) ||
                ("image/jpg".equalsIgnoreCase(type)) ||
                ("image/jpeg".equalsIgnoreCase(type)) ||
                ("image/pjpeg".equalsIgnoreCase(type)) ||
                ("image/bmp".equalsIgnoreCase(type)) ||
                ("image/png".equalsIgnoreCase(type));
    }

    /**
     * 不但要更新数据库,还要更新文件系统.
     *
     * @param pictures need to update to db
     */
    public static void updatePictures(List<PostPicture> pictures) {
        System.out.println("PostService.updatePictures");
        PostPictureDAO dao = new PostPictureDAO();
        try {
            for (PostPicture picture : pictures) {
                dao.update(picture);
                movePicture(picture);
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    /**
     * 将图片从监时目录移到正式目录.
     *
     * @param picture - post picture;
     */
    private static void movePicture(PostPicture picture) {
        System.out.println("PostService.movePicture");
        String srcFilename = rootPath + File.separator + mid_path + File.separator +
                tmp_dir + File.separator + picture.getPicUrl();
        String dstFilename = rootPath + File.separator + mid_path + File.separator +
                picture.getPost().getId() + File.separator + picture.getPicUrl();
        File srcFile = new File(srcFilename);
        File dstFile = new File(dstFilename);
        if (!srcFile.renameTo(dstFile)) {
            try {
                FileUtil.copyFile(srcFile, dstFile);
                if (!srcFile.delete()) {
                    BaseException.threw("delete file failed!");
                }
            } catch (BaseException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean deletePictures(String idList) {
        String[] ids = idList.split(",");
        for (String id : ids) {
            String path = rootPath + File.separator + mid_path + File.separator + id;
            if (!deleteDirectory(new File(path))) return false;
        }
        return true;
    }

    private static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (path.delete());

    }

    /**
     * 不但从数据库中删除,还要从文件系统中(_tmp_)删除.
     *
     * @param picId picture id.
     * @throws com.sitexa.framework.exception.BaseException
     *          - exception
     */
    public static void deletePicture(String picId) {
        PostPictureDAO dao = new PostPictureDAO();
        PostPicture picture = dao.findById(picId);

        if (picture == null) return;

        dao.delete(picture);

        //如果图片还没有与post关联,则从_tmp_目录删除
        String filename = rootPath + File.separator + mid_path + File.separator +
                tmp_dir + File.separator + picture.getPicUrl();

        //如果图片已经与post关联,则该图片已经转移到postId目录,从该目录删除
        if (picture.getPost() != null && picture.getPost().getId() != null) {
            filename = rootPath + File.separator + mid_path + File.separator +
                    picture.getPost().getId() + File.separator + picture.getPicUrl();
        }

        File file = new File(filename);

        if (!file.delete()) {
            System.out.println(" file delete error! ");
        }
    }

    public static void deleteFromFileSystem(PostPicture picture) {
        String filename = rootPath + File.separator + mid_path + File.separator +
                picture.getPost().getId() + File.separator + picture.getPicUrl();
        ImageUtil.deleteFromFileSystem(filename);
    }
}
