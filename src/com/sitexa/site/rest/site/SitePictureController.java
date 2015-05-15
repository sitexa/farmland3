package com.sitexa.site.rest.site;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SitePictureAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.service.SitePictureService;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 10:10:57
 */
public class SitePictureController extends SitePictureAction {

    private static Log log = LogFactory.getLog(SitePictureController.class);

    public HttpHeaders index() {
        site = getHome();
        if (site != null) {
            pictures.addAll(site.getPictures());
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("SitePictureController.show");
        site = SiteService.getSite(id);
        if (site != null) {
            pictures.addAll(site.getPictures());
        }
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders edit() {
        System.out.println("SitePictureController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            pictures.addAll(site.getPictures());
        }
        return new DefaultHttpHeaders("edit").disableCaching();
    }

    public HttpHeaders update() {
        System.out.println("SitePictureController.update");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            try {
                SitePictureService.updatePictures(pictures);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.toString());
            }
            createPictures();
        }
        pictures.clear();
        return edit();
    }

    public HttpHeaders destroy() {
        System.out.println("SitePictureController.destroy");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        String picId = ServletActionContext.getRequest().getParameter("picId");
        try {
            SitePictureService.deletePicture(picId);
        } catch (BaseException e) {
            log.error(e);
        }
        pictures.clear();
        return edit();
    }

    public void createPictures() {
        try {
            ArrayList<SitePicture> pics = gatherPictureInfo();
            SitePictureService.createPictures(pics, upload, uploadFileName, uploadContentType);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
    }

    private ArrayList<SitePicture> gatherPictureInfo() {
        ArrayList<SitePicture> pics = new ArrayList<SitePicture>();
        HttpServletRequest req = ServletActionContext.getRequest();
        String[] t = req.getParameterValues("picTitle");
        String[] d = req.getParameterValues("description");
        for (int i = 0; i < t.length; i++) {
            pics.add(new SitePicture(null, site, t[i], d[i]));
        }
        return pics;
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }
}
