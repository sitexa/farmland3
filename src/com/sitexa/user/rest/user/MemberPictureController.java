package com.sitexa.user.rest.user;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.user.action.MemberPictureAction;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberPicture;
import com.sitexa.user.service.MemberPictureService;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2009-4-6
 * Time: 10:03:10
 */
public class MemberPictureController extends MemberPictureAction {
    private static Log log = LogFactory.getLog(MemberPictureController.class);

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        member = getProfile();
        if (member != null) {
            user = member.getUser();
            pictures.addAll(member.getPictures());
        }
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            site = getHome();
            user = member.getUser();
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("edit");
        } else {
            return show();
        }
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        pictures.clear();
        member = MemberService.getMember(id);
        if (member != null) {
            site = getHome();
            user = member.getUser();
            pictures.addAll(member.getPictures());
            return new DefaultHttpHeaders("show");
        } else
            return index();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders update() {
        if (!haveRight()) return show();
        try {
            member = MemberService.getMember(id);
            user = member.getUser();
            MemberPictureService.updatePictures(pictures);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        createPictures();
        pictures.clear();
        return edit();
    }

    public HttpHeaders destroy() {
        if (!haveRight()) return show();
        String picId = ServletActionContext.getRequest().getParameter("picId");
        try {
            MemberPictureService.deletePicture(picId);
        } catch (BaseException e) {
            log.error(e);
        }
        pictures.clear();
        return index();
    }

    private void createPictures() {
        try {
            ArrayList<MemberPicture> pics = gatherPictureInfo();
            MemberPictureService.createPictures(pics, upload, uploadFileName, uploadContentType);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
    }

    private ArrayList<MemberPicture> gatherPictureInfo() {
        ArrayList<MemberPicture> pics = new ArrayList<MemberPicture>();
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] t = req.getParameterValues("picTitle");
        String[] d = req.getParameterValues("description");
        for (int i = 0; i < t.length; i++) {
            pics.add(new MemberPicture(null, member, t[i], d[i]));
        }
        return pics;
    }

    public void image() {
        String picId = ServletActionContext.getRequest().getParameter("picId");
        MemberPicture pic = MemberPictureService.getPicture(picId);
        if (pic != null) {
            byte[] imgData = pic.getAbbrev();
            if (imgData != null) {
                BufferedImage img = ImageUtil.getDecompressedImage(imgData);
                try {
                    ImageIO.write(img, "JPEG", ServletActionContext.getResponse().getOutputStream());
                } catch (IOException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Member member = MemberService.getMember(id);
        return profile != null && member != null && profile.getMemberId().equals(member.getMemberId());
    }

}
