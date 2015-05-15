package com.sitexa.user.rest.user;

import com.sitexa.sys.Sequence;
import com.sitexa.user.action.MemberPropertyAction;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberProperty;
import com.sitexa.user.service.MemberPropertyService;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2009-4-6
 * Time: 10:03:51
 */
public class MemberPropertyController extends MemberPropertyAction {
    private static Log log = LogFactory.getLog(MemberPropertyController.class);

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        System.out.println("MemberPropertyController.index");
        member = getProfile();
        if (member != null) {
            user = member.getUser();
            properties.addAll(member.getProperties());
        }
        return new DefaultHttpHeaders("index");
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders edit() {
        System.out.println("MemberPropertyController.edit");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            site=getHome();
            user = member.getUser();
            properties.addAll(member.getProperties());
            return new DefaultHttpHeaders("edit").disableCaching();
        } else
            return index();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        System.out.println("MemberPropertyController.show");
        member = MemberService.getMember(id);
        if (member != null) {
            user = member.getUser();
            properties.addAll(member.getProperties());
            return new DefaultHttpHeaders("show").disableCaching();
        } else
            return index();
    }

    public HttpHeaders update() {
        System.out.println("MemberPropertyController.update");
        if (!haveRight()) return show();
        member = MemberService.getMember(id);
        if (member != null) {
            try {
                System.out.println("in");
                MemberPropertyService.updateProperties(properties);
            } catch (Exception e) {
                log.error(e);
                addActionError(e.toString());
            }
            createProperties();
        }
        properties.clear();
        return edit();
    }

    public HttpHeaders create() {
        System.out.println("MemberPropertyController.create");
        return index();
    }

    public HttpHeaders destroy() {
        System.out.println("MemberPropertyController.destroy");
        if (!haveRight()) return show();
        String propId = ServletActionContext.getRequest().getParameter("propId");
        try {
            MemberPropertyService.deleteProperty(propId);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
        properties.clear();
        return edit();
    }

    private void createProperties() {
        try {
            ArrayList<MemberProperty> props = gatherProperties();
            MemberPropertyService.createProperties(props);
        } catch (Exception e) {
            log.error(e);
            addActionError(e.toString());
        }
    }

    private ArrayList<MemberProperty> gatherProperties() {
        ArrayList<MemberProperty> props = new ArrayList<MemberProperty>();
        String[] names = ServletActionContext.getRequest().getParameterValues("propName");
        String[] values = ServletActionContext.getRequest().getParameterValues("propValue");
        for (int i = 0; i < names.length; i++) {
            if (!values[i].trim().equals(""))
                props.add(new MemberProperty(Sequence.getId(), member, names[i], values[i]));
        }
        return props;
    }

    private boolean haveRight() {
        Member profile = getProfile();
        Member member = MemberService.getMember(id);
        return profile != null && member != null && profile.getMemberId().equals(member.getMemberId());
    }

}
