package com.sitexa.farm.rest.admin;

import com.sitexa.farm.service.LandService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2010-5-13
 * Time: 15:39:33
 */
public class ManageController extends ManageAction {

    private static final String LANDLORD = "6";

    public HttpHeaders index() {
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException ignored) {
            }
        }
        if (!LANDLORD.equals(member.getType().getTypeId())) {
            try {
                ServletActionContext.getResponse().sendRedirect("/");
            } catch (IOException ignored) {
            }
        }

        lands = LandService.getLandsByMember(member);



        return null;
    }

    public HttpHeaders show() {
        return null;
    }


}
