package com.sitexa.farm.rest.join;

import com.sitexa.farm.service.LandService;
import com.sitexa.user.data.MemberType;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

/**
 * User: xnpeng
 * Date: 2010-4-7
 * Time: 8:47:20
 */
public class JoinlandController extends JoinlandAction {
    protected static final String LordType = "6";

    public HttpHeaders index() {
        member = getProfile();
        if (member != null) {
            lands = LandService.getLandsByMember(member);
            if (lands.size() > 0) land = lands.get(0);
            MemberType mtype = member.getType();
            if (LordType.equals(mtype.getTypeId())) {
                step = "3";
            } else {
                step = "2";
            }
        } else {
            step = "1";
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders show() {
        land = LandService.getById(id);
        member = getProfile();
        if (!haveRight()) return index();

        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders editNew() {
        member = getProfile();
        if (member == null || LordType.equals(member.getType().getTypeId())) return index();
        site = getDefaultSite();
        return new DefaultHttpHeaders("new");
    }

    private boolean haveRight() {
        if (land == null) return false;
        if (member == null) return false;
        if (land.getLord().getMemberId().equals(member.getMemberId())) return true;
        return false;
    }
}
