package com.sitexa.rest.admin.user;

import com.sitexa.sys.service.RoleService;
import com.sitexa.user.rest.user.MemberController;
import com.sitexa.user.service.MemberService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-5-4
 * Time: 14:26:22
 */
public class MemberAdminController extends MemberController {
    private static Log log = LogFactory.getLog(MemberAdminController.class);

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

    @SuppressWarnings("unchecked")
    public HttpHeaders update() {
        System.out.println("MemberController.update");
        if (!haveRight()) return show();
        if (member != null) {
            try {
                MemberService.adminUpdate(member);
                addActionMessage("修改成功");
            } catch (Exception e) {
                addActionError("修改会员出错!原因:" + e.getMessage());
                log.error(e);
            }
        } else {
            return show();
        }
        return edit();
    }
}
