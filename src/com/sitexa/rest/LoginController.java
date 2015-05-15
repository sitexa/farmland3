package com.sitexa.rest;

import com.sitexa.action.LoginAction;
import com.sitexa.framework.AppContext;
import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.util.StringUtils;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.service.MemberService;
import com.sitexa.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.Cookie;
import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-4-16
 * Time: 17:44:06
 */
public class LoginController extends LoginAction {

    private static Log log = LogFactory.getLog(LoginController.class);

    public void prepare() {

    }

    public HttpHeaders index() {
        System.out.println("LoginController.index");
        AppContext ac = (AppContext) getSession().get(Constants.APP_CONTEXT);
        //1,如果已经登录,直接进欢迎页面;
        if (ac != null && ac.getUserId() != null) {
            return show();
        }
        //2,进入登录页面.
        return new DefaultHttpHeaders("").disableCaching();
    }

    public HttpHeaders show() {
        user = getMe();
        if (user == null) return index();
        try {
            if (user.getLastLoginDate() != null) {
                Long l = (new Date().getTime() - user.getLastLoginDate().getTime()) / 1000 / 60 / 60 / 24;
                days = l.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DefaultHttpHeaders("show").disableCaching();
    }

    public HttpHeaders confirm() {
        System.out.println("LoginController.confirm");
        String userId = ServletActionContext.getRequest().getParameter("userId");
        String confirmcode = ServletActionContext.getRequest().getParameter("confirmcode");
        if (userId != null && confirmcode != null) {
            try {
                if (UserService.confirmEmail(userId, confirmcode)) {
                    addActionMessage("邮件已经确认,你可以使用网站的服务了.Good Luck!");
                } else {
                    addActionError("邮箱确认有错!请跟管理员联系.");
                }
            } catch (Exception e) {
                addActionError("邮箱确认有错!请跟管理员联系.");
            }
        }
        return index();
    }

    //todo...用多种输和登录:1,username,2,realname;3,email;4,userid;
    @SuppressWarnings("unchecked")
    public HttpHeaders login() {
        System.out.println("LoginController.login");
        if (!checkCode()) {
            addActionError("识别码错误!");
            return index();
        }
        if (user.getPassword() != null && user.getUsername() != null) {
            try {
                String _username = user.getUsername();
                String _password = user.getPassword();

                String host = ServletActionContext.getRequest().getRemoteHost();

                user.setLoginAddress(host);
                user = UserService.login(user);

                if (user == null) {
                    return index();
                } else {
                    member = MemberService.getMember(user.getUserId());
                    AppContext ac;
                    if (member != null && member.getSite() != null) {
                        site = SiteService.getSite(member.getSite().getSiteId());
                        ac = new AppContext(user.getUserId(), site.getSiteId());
                    } else {
                        ac = new AppContext(user.getUserId(), null);
                    }
                    getSession().put(Constants.APP_CONTEXT, ac);
                    if (remember) {
                        writeCookie(_username, _password);
                    }
//                    return show();
                    ServletActionContext.getResponse().sendRedirect("/");
                }
            } catch (Exception e) {
                addActionError(e.toString());
                log.error(e);
            }
        }
        return index();
    }

    public HttpHeaders logout() {
        clearCookie();
        getSession().remove(Constants.APP_CONTEXT);
        getSession().remove(Constants.SITE_KEY);
        getSession().remove(Constants.CATEGORY_KEY);
        user = null;
        member = null;
        site = null;
        return index();
    }

    public HttpHeaders findpwd() {
        return new DefaultHttpHeaders("findpwd");
    }

    public HttpHeaders rewind() {
        System.out.println("LoginController.rewind");
        try {
            String result = UserService.rewind(user, member);
            if ("0".equals(result)) {
                BaseException.threw("找回密码失败!");
            } else if ("1".equals(result)) {
                addActionMessage("密码已经发送到您的邮箱中,请查收后登录!");
            } else {
                addActionMessage("密码已经重置为:" + result);
            }
        } catch (Exception e) {
            log.error(e);
            addActionError(e.getMessage());
            return findpwd();
        }
        return index();
    }

    private void writeCookie(String _username, String _password) {
        try {
            System.out.println("LoginController.writeCookie");
            _username = StringUtils.encodeByteString(_username);
            _password = StringUtils.encodeByteString(_password);

            Cookie cookie = new Cookie("u", _username);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            ServletActionContext.getResponse().addCookie(cookie);

            cookie = new Cookie("p", _password);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            ServletActionContext.getResponse().addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clearCookie() {
        Cookie[] cs = ServletActionContext.getRequest().getCookies();
        for (Cookie c : cs) {
            if (c.getName().equalsIgnoreCase("p") || c.getName().equalsIgnoreCase("u")) {
                c.setValue(null);
                c.setMaxAge(-1);
                ServletActionContext.getResponse().addCookie(c);
            }
        }
    }

    private boolean checkCode() {
        String cd = (String) getSession().get("code");
        return cd != null && code != null && cd.equalsIgnoreCase(code);
    }

}
