package com.sitexa.site.rest.site;

import com.sitexa.sys.Sequence;
import com.sitexa.service.RightService;
import com.sitexa.site.action.SitePropertyAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteProperty;
import com.sitexa.site.service.SitePropertyService;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 10:09:52
 */
public class SitePropertyController extends SitePropertyAction {

    private static Log log = LogFactory.getLog(SitePropertyController.class);

    public HttpHeaders index() {
        site = getHome();
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        System.out.println("SitePropertyController.show");
        site = SiteService.getSite(id);
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders edit() {
        System.out.println("SitePropertyController.edit");
        if (!haveRight()) return show();
        site = SiteService.getSite(id);
        if (site != null) {
            properties.addAll(site.getProperties());
        }
        return new DefaultHttpHeaders("edit").disableCaching();
    }

    public HttpHeaders update() {
        System.out.println("SitePropertyController.update");
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
        System.out.println("SitePropertyController.destroy");
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

    protected boolean haveRight() {
        Member profile = getProfile();
        Site site = SiteService.getSite(id);
        haveRight = RightService.checkRight(profile, site);
        return haveRight;
    }

}
