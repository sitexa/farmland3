package com.sitexa.rest.admin.user;

import com.sitexa.sys.service.RoleService;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-5-4
 * Time: 14:20:10
 */
public class MemberPictureController extends com.sitexa.user.rest.user.MemberPictureController {
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
        return RoleService.getRoleInUser(getMe(), RoleService.getRole("2")) != null;
    }
}
