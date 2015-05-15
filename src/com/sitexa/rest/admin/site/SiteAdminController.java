package com.sitexa.rest.admin.site;

import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.sys.service.RoleService;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.site.action.SiteAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.service.SitePictureService;
import com.sitexa.site.service.SiteService;
import com.sitexa.site.service.SiteTypeService;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-5-3
 * Time: 23:36:28
 */
public class SiteAdminController extends SiteAction {

    private static Log log = LogFactory.getLog(SiteAdminController.class);

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

    public HttpHeaders search() {
        System.out.println("SiteAdminController.search");
        System.out.println("page.getCurrent() = " + page.getCurrent());
        try {
            if (withChildren)
                siteList = SiteService.search(page, searchWord, siteType, withChildren);
            else
                siteList = SiteService.search(page, searchWord, siteType);
            if (siteList.size() > 0)
                site = siteList.get(0);
            else
                site = getHome();
        } catch (BaseException e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders index() {
        System.out.println("SiteAdminController.index");
        site = getHome();
        if (site != null) {
            properties.addAll(site.getProperties());
            pictures.addAll(site.getPictures());
            governor = site.getGovernor();
        } else if (getMe() != null) {
            try {
                //fuck!why need to remove this fucking session???
                System.out.println("@@@@@why need to remove this fucking session???@@@@@");
                getSession().remove(Constants.SITE_KEY);
                site = SiteService.getRoot();
            } catch (Exception ignored) {
            }
        } else {
            try {
                //fuck!why need to remove this fucking session???
                System.out.println("@@@@@why need to remove this fucking session???@@@@@");
                getSession().remove(Constants.SITE_KEY);
                site = SiteService.getRoot();
            } catch (Exception ignored) {
            }
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("SiteAdminController.show");
        site = SiteService.getSite(id);
        if (site != null) {
            governor = site.getGovernor();
            properties.clear();
            pictures.clear();
            properties.addAll(site.getProperties());
            pictures.addAll(site.getPictures());
            governor = site.getGovernor();
            return new DefaultHttpHeaders("show");
        } else {
            return index();
        }
    }

    public HttpHeaders edit() {
        System.out.println("SiteAdminController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            governor = site.getGovernor();
            return new DefaultHttpHeaders("edit").disableCaching();
        } else {
            return show();
        }
    }

    public HttpHeaders editNew() {
        System.out.println("SiteAdminController.editNew");
        try {
            if (getMe() == null) {
                BaseException.threw("您还没有登录.");
            }
            if (getProfile() == null) {
                BaseException.threw("您还没有填写会员资料.");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders delete() {
        System.out.println("SiteAdminController.delete");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        return new DefaultHttpHeaders("delete");
    }

    public HttpHeaders destroy() {
        System.out.println("SiteAdminController.destroy");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        try {
            SiteService.delete(site);
            addActionMessage("删除成功!");
            return index();
        } catch (Exception e) {
            log.error(e);
            addActionError("删除出错!");
            return edit();
        }
    }

    public HttpHeaders create() {
        System.out.println("SiteAdminController.create");
        try {
            if ((site.getName() != null &&
                    !site.getName().equalsIgnoreCase(""))
                    && (site.getType().getTypeId() != null
                    && !site.getType().getTypeId().equalsIgnoreCase(""))) {
                siteType = SiteTypeService.getSiteType(site.getType().getTypeId());
                site.setType(siteType);

                if (site.getParent() != null && site.getParent().getSiteId() != null) {
                    Site p = SiteService.getSite(site.getParent().getSiteId());
                    site.setParent(p);
                }

                SiteService.create(site);

                addActionMessage("成功创建新社区!");
                id = site.getSiteId();
                return edit();
            } else {
                BaseException.threw("新建社区出错!");
            }
        } catch (Exception e) {
            addActionError("新建社区出错!" + e.getMessage());
            log.error(e);
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders update() {
        System.out.println("SiteAdminController.update");
        if (!haveRight()) return show();
        if (site != null) {
            try {
                Site site2 = SiteService.getSite(site.getSiteId());
                site2.setName(site.getName());
                if (site.getParent() != null && site.getParent().getSiteId() != null) {
                    Site p = SiteService.getSite(site.getParent().getSiteId());
                    site2.setParent(p);
                }
                if (site.getType() != null && site.getType().getTypeId() != null) {
                    SiteType t = SiteTypeService.getSiteType(site.getType().getTypeId());
                    site2.setType(t);
                }
                site2.setAddress(site.getAddress());
                site2.setStatus(site.getStatus());
                if (site.getGovernor() != null && site.getGovernor().getMemberId() != null) {
                    Member m = MemberService.getMember(site.getGovernor().getMemberId());
                    site2.setGovernor(m);
                }
                site2.setIntroduction(site.getIntroduction());
                site2.setLatitude(site.getLatitude());
                site2.setLongitude(site.getLongitude());
                site2.setZoom(site.getZoom());

                SiteService.adminUpdate(site2);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e);
                addActionError(e.toString());
            }
        }
        return edit();
    }

    public void siteTree() throws IOException {
        String siteId = ServletActionContext.getRequest().getParameter("siteId");
        String xml = SiteService.getSiteTree(siteId);
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        ServletActionContext.getResponse().getWriter().write(xml);
    }

    public void image() {
        String picId = ServletActionContext.getRequest().getParameter("picId");
        SitePicture pic = SitePictureService.getPicture(picId);
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
}
