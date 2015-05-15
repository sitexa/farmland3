package com.sitexa.user.rest.user;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.action.UserAction;
import com.sitexa.user.data.User;
import com.sitexa.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import java.io.IOException;

/**
 * User: xnpeng
 * Date: 2009-4-2
 * Time: 20:29:05
 */
public class UserController extends UserAction {

    private static Log log = LogFactory.getLog(UserController.class);

    public void prepare() {
        super.prepare();
        site = getHome();
    }

    protected boolean haveRight() {
        User me = getMe();
        User user = UserService.getUser(id);
        return (me != null && user != null && me.getUserId().equals(user.getUserId()));
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders index() {
        user = getMe();
        if (user == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }
        return new DefaultHttpHeaders("index");
    }

    public HttpHeaders search() {
        userList = UserService.getAllUser();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    @SuppressWarnings("unchecked")
    public HttpHeaders show() {
        if (!haveRight()) return me();
        user = UserService.getUser(id);
        if (user != null) {
            return new DefaultHttpHeaders("show");
        } else
            return index();

    }

    public HttpHeaders me() {
        user = getMe();
        if (user != null) {
            return new DefaultHttpHeaders("show");
        } else {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    public HttpHeaders edit() {
        if (!haveRight()) return me();
        user = UserService.getUser(id);
        if (user != null) {
            return new DefaultHttpHeaders("edit").disableCaching();
        } else
            return show();
    }

    public HttpHeaders editNew() {
        return new DefaultHttpHeaders("editNew");
    }

    public HttpHeaders create() {
        System.out.println("UserController.create");
        try {
            if (!checkCode()) BaseException.threw("识别码错误.");
            String host = ServletActionContext.getRequest().getRemoteHost();
            user.setLoginAddress(host);
            if (site.getSiteId() != null && !"".equals(site.getSiteId())) {
                site = SiteService.getSite(site.getSiteId());
            }
            if (site == null) {
                addActionError("请选择所在社区");
                return editNew();
            }
            UserService.create(user, site);
            addActionMessage("新用户注册成功,确认信很快就会发送到您的Email收件箱," +
                    "请查收并确认,您的用户就可以生效了.在确认邮件之前,你的帐号有一周的使用期.");
            code = null;
            return new DefaultHttpHeaders("index");
        } catch (Exception e) {
            log.error(e);
            addActionError("注册用户出错!原因:" + e.getMessage());
            return editNew();
        }
    }

    public HttpHeaders update() {
        if (!haveRight()) return me();
        User user2 = UserService.getUser(id);
        if (user2 != null) {
            try {
                UserService.updateEmail(user2, user.getEmail());
                addActionMessage("用户信息修改成功,确认信很快就会发送到您的Email收件箱,请查收并确认,您的用户就可以生效了.");
            } catch (Exception e) {
                log.error(e);
                addActionError("修改用户出错!请检查您的输入信息,重新提交.");
            }
        }
        return edit();
    }

    public HttpHeaders updateEmail() {
        if (!haveRight()) return me();
        User user2 = UserService.getUser(id);
        if (user2 != null) {
            try {
                UserService.updateEmail(user2, user.getEmail());
                addActionMessage("用户信息修改成功,确认信很快就会发送到您的Email收件箱,请查收并确认,您的用户就可以生效了.");
            } catch (Exception e) {
                log.error(e);
                addActionError("修改用户出错!请检查您的输入信息,重新提交.");
            }
        }
        return show();
    }

    public HttpHeaders delete() {
        System.out.println("UserController.delete");
        if (!haveRight()) return me();
        return new DefaultHttpHeaders("delete");
    }

    public HttpHeaders destroy() {
        if (!haveRight()) return me();
        user = UserService.getUser(id);
        try {
            UserService.delete(user);
            addActionMessage("删除用户成功!");
        } catch (Exception e) {
            log.error(e);
            addActionError("删除用户出错!");
        }
        return index();
    }

    public HttpHeaders password() {
        if (!haveRight()) return me();
        user = getMe();
        site = getHome();
        return new DefaultHttpHeaders("password").disableCaching();
    }

    public HttpHeaders setPassword() {
        System.out.println("UserController.setPassword");
        if (!haveRight()) return me();
        user = UserService.getUser(id);
        if (user != null) {
            try {
                if (newPassword != null) {
                    UserService.changePassword(user, oldPassword, newPassword);
                    addActionMessage("密码修改成功!");
                }
            } catch (Exception e) {
                addActionError(e.getMessage());
            }
        }
        return password();
    }
  
    private boolean checkCode() {
        String cd = (String) getSession().get("code");
        return cd != null && code != null && cd.equalsIgnoreCase(code);
    }

}
