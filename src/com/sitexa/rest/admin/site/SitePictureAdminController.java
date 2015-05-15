package com.sitexa.rest.admin.site;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.sys.service.RoleService;
import com.sitexa.site.action.SitePictureAction;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.service.SitePictureService;
import com.sitexa.site.service.SiteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2009-5-4
 * Time: 10:03:42
 */
public class SitePictureAdminController extends SitePictureAction {

    private static Log log = LogFactory.getLog(SitePictureAdminController.class);

    public void prepare() {
        super.prepare();
        if (!haveRight())
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "error");
            } catch (IOException ignored) {

            }
    }

    protected boolean haveRight() {
        return RoleService.getRoleInUser(getMe(), RoleService.getRole("1")) != null;
    }

    public HttpHeaders index() {
        System.out.println("SitePictureAdminController.index");
        site = getHome();
        if (site != null) {
            pictures.addAll(site.getPictures());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        System.out.println("SitePictureAdminController.show");
        try {
            site = SiteService.getSite(id);
            if (site == null) site = getHome();
            if (site != null) {
                pictures.addAll(site.getPictures());
            }
        } catch (Exception e) {
            log.error(e);
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders edit() {
        System.out.println("SitePictureAdminController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            pictures.addAll(site.getPictures());
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders update() {
        System.out.println("SitePictureAdminController.update");
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
        System.out.println("SitePictureAdminController.destroy");
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

}
