package com.sitexa.rest.admin.site;

import com.sitexa.sys.Sequence;
import com.sitexa.sys.service.RoleService;
import com.sitexa.site.action.SitePropertyAction;
import com.sitexa.site.data.SiteProperty;
import com.sitexa.site.service.SitePropertyService;
import com.sitexa.site.service.SiteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2009-5-4
 * Time: 10:02:20
 */
public class SitePropertyAdminController extends SitePropertyAction {
    private static Log log = LogFactory.getLog(SitePropertyAdminController.class);

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
        System.out.println("SitePropertyAdminController.index");
        site = getHome();
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("");
    }

    public HttpHeaders show() {
        System.out.println("SitePropertyAdminController.show");
        site = SiteService.getSite(id);
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders edit() {
        System.out.println("SitePropertyAdminController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders update() {
        System.out.println("SitePropertyAdminController.update");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            try {
                SitePropertyService.updateProperties(properties);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.getMessage());
            }
            createProperties();
        }
        properties.clear();
        return edit();
    }

    public HttpHeaders destroy() {
        System.out.println("SitePropertyAdminController.destroy");
        if (!haveRight()) return show();
        String propId = ServletActionContext.getRequest().getParameter("propId");
        try {
            SitePropertyService.deleteProperty(propId);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        properties.clear();
        return edit();
    }

    private void createProperties() {
        try {
            ArrayList<SiteProperty> props = gatherProperties();
            SitePropertyService.createProperties(props);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
    }

    private ArrayList<SiteProperty> gatherProperties() {
        ArrayList<SiteProperty> props = new ArrayList<SiteProperty>();
        String[] names = ServletActionContext.getRequest().getParameterValues("propName");
        String[] values = ServletActionContext.getRequest().getParameterValues("propValue");
        for (int i = 0; i < names.length; i++) {
            if (!values[i].trim().equals(""))
                props.add(new SiteProperty(Sequence.getId(), site, names[i], values[i]));
        }
        return props;
    }
}
