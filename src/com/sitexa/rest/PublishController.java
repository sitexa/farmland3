package com.sitexa.rest;

import com.sitexa.framework.Constants;
import com.sitexa.action.PublishAction;
import com.sitexa.post.data.Category;
import com.sitexa.post.service.CategoryService;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-4-21
 * Time: 20:02:50
 */
public class PublishController extends PublishAction {
    private static String message_url = "/post/message/";
    private static String affair_url = "/post/affair/";
    private static String business_url = "/post/business/";
    private static String activity_url = "/post/activity/";
    private static String announce_url = "/post/announce/";
    private static String house_url = "/post/house/";
    private static String case_url = "/post/case/";
    private static String scenery_url = "/post/scenery/";
    private static String poll_url = "/post/poll/";
    private static String login_url = "/login";
    private static String member_url = "/user/member";

    public void prepare() {
        super.prepare();
        String siteId = (String) getSession().get(Constants.SITE_KEY);
        if (siteId != null)
            site = SiteService.getSite(siteId);
        else
            site = getHome();
        String cateId = (String) getSession().get(Constants.CATEGORY_KEY);
        if (cateId != null)
            category = CategoryService.getCategory(cateId);
    }

    public HttpHeaders index() {
        System.out.println("PublishController.index");
        if (getProfile() == null) {
            addActionError("请登录后再发布信息!");
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + login_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (getHome() == null) {
            addActionError("请在加入社区后发布信息!");
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + member_url + "/" + getProfile().getMemberId() + "/edit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String cateId = ServletActionContext.getRequest().getParameter("cateId");
        if (cateId != null && !cateId.equals(""))
            category = CategoryService.getCategory(cateId);

        //第一次进入时,cateId=null,category=null;
        //点击确定后,从页面提交了cateId,因此category!=null,执行以下选择跳转动作.
        //由于不能实现重定向:forward,request中的参数无法传递过去.
        try {
            if (category != null) {
                if (category.getCateId().equalsIgnoreCase(Category.MESSAGE)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + message_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.AFFAIR)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + affair_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.BUSINESS)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + business_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.ACTIVITY)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + activity_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.ANNOUNCE)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + announce_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.HOUSE)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + house_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.CASE)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + case_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.SCENERY)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + scenery_url + "new");
                } else if (category.getCateId().equalsIgnoreCase(Category.POLL)) {
                    ServletActionContext.getResponse()
                            .sendRedirect(ServletActionContext.getRequest().getContextPath() + poll_url + "new");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("");
    }

}
