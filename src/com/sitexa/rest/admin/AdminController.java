package com.sitexa.rest.admin;

import com.sitexa.sys.service.RoleService;
import com.sitexa.action.admin.AdminAction;
import com.sitexa.user.data.User;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-5-3
 * Time: 23:34:36
 */
public class AdminController extends AdminAction {

    public void prepare() {
        super.prepare();
        User me = getMe();
        if (!haveRight(me, "1")) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/error");
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
        site = getHome();
    }

    private boolean haveRight(User user, String roleId) {
        return RoleService.getRoleInUser(user, RoleService.getRole(roleId)) != null;
    }

    public HttpHeaders index() {
        System.out.println("AdminController.index");
        return new DefaultHttpHeaders("");
    }

}
