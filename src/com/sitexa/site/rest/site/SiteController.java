package com.sitexa.site.rest.site;

import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.util.ImageUtil;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SiteAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SitePicture;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.service.SitePictureService;
import com.sitexa.site.service.SitePropertyService;
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
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-22
 * Time: 22:50:23
 */
public class SiteController extends SiteAction {
    private Log logger = LogFactory.getLog(this.getClass());
    private String slogan = "";

    public void prepare() {
        super.prepare();
        siteTypeList = SiteService.getAllSiteTypes();
    }

    public String getSlogan() {
        return slogan;
    }

    public HttpHeaders hotList() {
        hotSiteList = SiteService.getHotSites();
        return new DefaultHttpHeaders("hotlist");
    }

    public void calcSlogan() {
        String s = "";
        s = SitePropertyService.getSlogan(site);
        if (s == null) s = SitePropertyService.getSlogan(SiteService.getRoot());
        if (s == null) s = "告别虚拟,回归真实,回到社的家!";
        slogan = s.length() <= 20 ? s : s.substring(0, 20);
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        System.out.println("SiteController.index");
        site = getHome();
        calcSlogan();
        Member profile = getProfile();
        haveRight = RightService.checkRight(profile, site);
        if (site != null) {
            //2009.6.27,当前社区取帖子所属社区。
            getSession().put(Constants.SITE_KEY, site.getSiteId());

            properties.addAll(site.getProperties());
            pictures.addAll(site.getPictures());
            governor = site.getGovernor();
        } else if (getMe() != null) {
            try {
                //fuck!why need to remove this fucking session???
                System.out.println("@@@@@why need to remove this fucking session???@@@@@");
                getSession().remove(Constants.SITE_KEY);
                site = getMyCity();
                if (site == null)
                    site = SiteService.getRoot();
            } catch (Exception ignored) {
            }
        } else {
            try {
                //fuck!why need to remove this fucking session???
                System.out.println("@@@@@why need to remove this fucking session???@@@@@");
                getSession().remove(Constants.SITE_KEY);
                site = getMyCity();
                if (site == null)
                    site = SiteService.getRoot();
            } catch (Exception ignored) {
            }
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        if (reject()) {
            site = SiteService.getRoot();
        } else {
            site = SiteService.getSite(id);
        }
        calcSlogan();
        haveRight = RightService.checkRight(getProfile(), site);
        if (site != null) {
            //2009.6.27,当前社区取帖子所属社区。
            getSession().put(Constants.SITE_KEY, site.getSiteId());
            governor = site.getGovernor();
            properties.clear();
            pictures.clear();
            properties.addAll(site.getProperties());
            pictures.addAll(site.getPictures());
            governor = site.getGovernor();
            //todo...add vwcnt;
            SiteService.incVwCnt(site);
            return new DefaultHttpHeaders("show").disableCaching();
        } else {
            return index();
        }
    }

    private boolean reject() {
        String host = ServletActionContext.getRequest().getRemoteAddr();
        return SiteService.isBannedHost(host);
    }

    public HttpHeaders edit() {
        System.out.println("SiteController.edit");
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
        try {
            if (getMe() == null) {
                BaseException.threw("您还没有登录.");
            }
            if (getProfile() == null) {
                BaseException.threw("您还没有填写会员资料.");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
//            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders delete() {
        System.out.println("SiteController.delete");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        return new DefaultHttpHeaders("delete");
    }

    public HttpHeaders destroy() {
        System.out.println("SiteController.destroy");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        try {
            SiteService.delete(site);
            addActionMessage("删除成功!");
            return index();
        } catch (Exception e) {
            logger.error(e);
            addActionError("删除出错!");
            return edit();
        }
    }

    public HttpHeaders create() {
        System.out.println("SiteController.create");
        try {
            if ((site.getName() != null &&
                    !site.getName().equalsIgnoreCase(""))
                    && (site.getType().getTypeId() != null
                    && !site.getType().getTypeId().equalsIgnoreCase(""))) {
                siteType = SiteTypeService.getSiteType(site.getType().getTypeId());
                site.setType(siteType);

                if ("1".equals(siteType.getTypeId())
                        || "2".equals(siteType.getTypeId())
                        || "3".equals(siteType.getTypeId())
                        || "4".equals(siteType.getTypeId())) {
                    BaseException.threw("您不能创建县级及以上级别的社区!!");
                }

                if (site.getParent() != null && site.getParent().getSiteId() != null) {
                    Site p = SiteService.getSite(site.getParent().getSiteId());
                    site.setParent(p);
                }

                //1,设置governor为当前用户;
                //2,更改当前用户的社区;
                //3,更改当前用户以前社区的会员属性.
                SiteService.createByMember(site, getProfile());

                addActionMessage("成功创建新社区!");
                id = site.getSiteId();
                return edit();
            } else {
                BaseException.threw("新建社区出错!");
            }
        } catch (Exception e) {
            addActionError("新建社区出错!" + e.getMessage());
            logger.error(e);
            return index();
        }
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders update() {
        System.out.println("SiteController.update");
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
                    if ("1".equals(site.getType().getTypeId())
                            || "2".equals(site.getType().getTypeId())) {
                        BaseException.threw("您不能修改省级及以上级别的社区!!");
                    }
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

                SiteService.update(site2);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                addActionError(e.toString());
            }
        }
        return edit();
    }

    public HttpHeaders search() {
        System.out.println("SiteController.search");
        try {
            if (withChildren)
                siteList = SiteService.search(page, searchWord, siteType, withChildren);
            else
                siteList = SiteService.search(page, searchWord, siteType);
//                siteList = SiteService.search(page, words);
        } catch (Exception e) {
            logger.error(e);
        }
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public void siteTree() throws IOException {
        String siteId = ServletActionContext.getRequest().getParameter("siteId");
        String xml = SiteService.getSiteTree(siteId);
        ServletActionContext.getResponse().setContentType("text/xml");
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
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    protected boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }

    public void siteXml() {
        System.out.println("SiteController.siteXml");
        String siteId = ServletActionContext.getRequest().getParameter("siteId");
        String result = "<?xml version='1.0' encoding='UTF-8'?>";
        result += "<site>";
        if (siteId != null || !"".equals("")) {
            Site site = SiteService.getSite(siteId);
            if (site != null) {
                result += "<siteId>" + site.getSiteId() + "</siteId>";
                result += "<name>" + toIso(site.getName()) + "</name>";
                result += "<parentId>" + ((site.getParent() == null) ? "" : site.getParent().getSiteId()) + "</parentId>";
                result += "<typeId>" + site.getType().getTypeId() + "</typeId>";
                result += "<governorId>" + (site.getGovernor() == null ? "" : site.getGovernor().getMemberId()) + "</governorId>";
                result += "<latitude>" + (site.getLatitude() == null ? "" : site.getLatitude().toString()) + "</latitude>";
                result += "<longitude>" + (site.getLongitude() == null ? "" : site.getLongitude().toString()) + "</longitude>";
            }
        }
        result += "</site>";
        ServletActionContext.getResponse().addHeader("Content-type", "text/xml");
        try {
            ServletActionContext.getResponse().getOutputStream().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String toIso(String s) {
        String r = null;
        try {
            byte[] b = s.getBytes("utf-8");
            r = new String(b, "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r;
    }
}
