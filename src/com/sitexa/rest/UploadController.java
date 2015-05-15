package com.sitexa.rest;

import com.sitexa.action.UploadAction;
import com.sitexa.framework.Constants;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-22
 * Time: 13:01:07
 */
public class UploadController extends UploadAction {

    private static Log log = LogFactory.getLog(UploadController.class);

    private boolean haveRight() {
        return getProfile() != null;
    }

    @SuppressWarnings("unchecked")
    public String index() {
        System.out.println("UploadController.index");
        if (!haveRight()) return null;
        //如果是编辑已经存在的post的图片,传入postId则从数据库获取图片.
        try {
            if (id != null) {
                Post post1 = PostService.getPost(id);
                pictures = PostService.getPicturesByPost(post1);
            } else if (post != null && post.getId() != null) {
                Post post1 = PostService.getPost(post.getId());
                List<PostPicture> pics = PostService.getPicturesByPost(post1);
                pictures.addAll(pics);
            }
            //如果编辑还没有跟post关联的图片,则从session中获取图片.
            List picIds = (List) getSession().get(Constants.PICS);
            if (picIds != null) {
                List<PostPicture> pics = PostService.getPictures(picIds);
                pictures.addAll(pics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    @SuppressWarnings("unchecked")
    public String show() {
        if (id != null) {
            post = PostService.getPost(id);
            pictures = PostService.getPicturesByPost(post);
        }
        //如果编辑还没有跟post关联的图片,则从session中获取图片.
        List picIds = (List) getSession().get(Constants.PICS);
        if (picIds != null) {
            List<PostPicture> pics = PostService.getPictures(picIds);
            pictures.addAll(pics);
        }

        return "";
    }

    public String delete() {
        if (!haveRight()) return null;
        String picId = ServletActionContext.getRequest().getParameter("picId");
        if (picId != null) {
            try {
                PostService.deletePicture(picId);
                removePicFromSession(picId);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
        }
        return index();
    }

    public String update() {
        System.out.println("UploadController.update");
        if (!haveRight()) return null;
        try {
            List<PostPicture> pics = gatherPictureInfo();
            PostService.createPictures(pics, upload, uploadFileName, uploadContentType);
            savePicsToSession(pics);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }

        return index();
    }

    /**
     * 如果图片还没有跟post关联,则将其放入session
     *
     * @param pics - pictures
     */
    @SuppressWarnings("unchecked")
    private void savePicsToSession(List<PostPicture> pics) {
        System.out.println("UploadController.savePicsToSession");
        List<String> picIds = (List<String>) getSession().get(Constants.PICS);

        if (picIds == null)
            picIds = new ArrayList<String>();

        for (PostPicture p : pics) {
            if (p.getPost() == null)
                picIds.add(p.getPicId());
        }

        getSession().put(Constants.PICS, picIds);
    }

    @SuppressWarnings("unchecked")
    private void removePicFromSession(String picId) {
        List<String> picIds = (List<String>) getSession().get(Constants.PICS);
        if (picIds != null && picIds.size() > 0) {
            picIds.remove(picId);
            getSession().put(Constants.PICS, picIds);
        }
    }

    private List<PostPicture> gatherPictureInfo() {
        System.out.println("UploadController.gatherPictureInfo");
        List<PostPicture> pics = new ArrayList<PostPicture>();
        HttpServletRequest req = ServletActionContext.getRequest();
        String t = req.getParameter("picTitle");
        String d = req.getParameter("description");
        if (post!=null && post.getId() != null && !"".equals(post.getId())) {
            post = PostService.getPost(post.getId());
        } else {
            post = null;
        }
        pics.add(new PostPicture(null, post, t, d));
        return pics;
    }

    public void image() {
        String picId = ServletActionContext.getRequest().getParameter("picId");
        PostPicture pic = PostService.getPicture(picId);
        if (pic != null) {
            byte[] imgData = pic.getAbbrev();
            if (imgData != null) {
                BufferedImage img = ImageUtil.getDecompressedImage(imgData);
                try {
                    ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e);
                }
            }
        }
    }

}
