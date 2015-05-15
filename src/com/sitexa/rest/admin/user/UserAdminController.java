package com.sitexa.rest.admin.user;

import com.sitexa.sys.service.RoleService;
import com.sitexa.user.rest.user.UserController;
import com.sitexa.user.data.User;
import com.sitexa.user.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-5-3
 * Time: 23:36:40
 */
public class UserAdminController extends UserController {
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

    public HttpHeaders update() {
        if (!haveRight()) return me();
        User user2 = UserService.getUser(id);
        if (user2 != null) {
            try {
                user2.setUsername(user.getUsername());
                user2.setEmail(user.getEmail());
                user2.setEmailConfirmed(user.isEmailConfirmed());
                user2.setStatus(user.getStatus());
                user2.setValidDate(user.getValidDate());
                user2.setExpiryDate(user.getExpiryDate());
                user2.setTryTimes(user.getTryTimes());
                UserService.update(user2);
                addActionMessage("用户信息修改成功,确认信很快就会发送到您的Email收件箱,请查收并确认,您的用户就可以生效了.");
            } catch (Exception e) {
                addActionError("修改用户出错!请检查您的输入信息,重新提交.");
            }
        }
        return edit();
    }

}
