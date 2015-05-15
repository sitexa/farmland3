package com.sitexa.framework.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.sitexa.framework.AppContext;
import com.sitexa.framework.Constants;
import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.util.Ip2City;
import com.sitexa.framework.util.StringUtils;
import com.sitexa.post.data.Category;
import com.sitexa.post.service.CategoryService;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.service.ViewStatsService;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;
import com.sitexa.user.service.MemberService;
import com.sitexa.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: xnpeng
 * Date: 2009-2-20
 * Time: 14:57:56
 */
@SuppressWarnings("unchecked")
public class BaseAction extends ActionSupport implements SessionAware, ApplicationAware, Preparable {
    private static Log log = LogFactory.getLog(BaseAction.class);
    private Map application;
    private Map session;
    //session user,member,home
    private User me;
    private Member profile;
    private Site home = null;
    //global id
    protected String id;
    //mn for navigation id;
    protected String mn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSession(Map map) {
        this.session = map;
    }

    public Map getSession() {
        return session;
    }

    public Map getApplication() {
        return application;
    }

    public void setApplication(Map map) {
        this.application = map;
    }

    public void setContext(String name, Object value) {
        session.put(name, value);
    }

    public Object getContext(String name) {
        return session.get(name);
    }

    public String getMn() {
        mn = (String) getContext("mn");
        return mn;
    }

    public void setMn(String mn) {
        setContext("mn", mn);
        this.mn = mn;
    }

    public void prepare() {
        try {
            String loginTried = (String) getSession().get(Constants.LOGIN_TRIED);
            if (loginTried == null) {
                getSession().put(Constants.LOGIN_TRIED, "1");
                boolean b = loginByCookie();
                //如果cookie登录成功,则获取用户所在社区,否则,获取用用户IP地址所在城市;
                if (!b) {
                    //todo...
                    //由于Ip2City程序所使用的ikaka.com屏蔽了服务器地址,程序获取不到IP地址对应的城市;
                    //所以,这段程序只会降低响应速度.故注释掉.
                    String ip = ServletActionContext.getRequest().getRemoteAddr();
                    String cityname = Ip2City.getCity(ip);
                    if (cityname != null) {
                        Site city = SiteService.searchSiteByCityName(cityname);
                        if (city != null) {
                            getSession().put(Constants.CITY_KEY, city.getSiteId());
                        }
                    }
                } else {
                    //在登录过程中已经在session中保存了AppContext;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            ViewStatsService.visit(ServletActionContext.getRequest());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public User getMe() {
        AppContext ac = (AppContext) session.get(Constants.APP_CONTEXT);
        if (ac != null && ac.getUserId() != null) {
            me = UserService.getUser(ac.getUserId());
        }
        return me;
    }

    public Member getProfile() {
        AppContext ac = (AppContext) session.get(Constants.APP_CONTEXT);
        if (ac != null && ac.getUserId() != null) {
            profile = MemberService.getMember(ac.getUserId());
        }
        return profile;
    }

    public Site getHome() {
        AppContext ac = (AppContext) session.get(Constants.APP_CONTEXT);
        if (ac != null && ac.getSiteId() != null) {
            home = SiteService.getSite(ac.getSiteId());
        }
        return home;
    }

    public List<Category> getCategories() {
        return CategoryService.getCategoryByCommon();
    }

    public List<Site> getHotSites() {
        profile = getProfile();
        return SiteService.getHotSites(getHome());
    }

    public List<Site> getFriendSites() {
        profile = getProfile();
        return MemberService.getFriendSite(profile);
    }

    private boolean loginByCookie() {
        String u = getCookie("u");
        String p = getCookie("p");
        String host = getIpAddress();

        if (u == null || "".equals(u) || p == null || "".equals(p)) {
            return false;
        } else {
            u = StringUtils.decodeByteString(u);
            p = StringUtils.decodeByteString(p);

            User user = new User();
            user.setUsername(u);
            user.setPassword(p);
            user.setLoginAddress(host);

            try {
                user = UserService.login(user);
            } catch (BaseException e) {
                addActionError(e.toString());
                return false;
            }

            if (user == null) {
                return false;
            }

            Member member = MemberService.getMember(user.getUserId());

            AppContext ac = null;
            if (member != null) {
                Site site = member.getSite();
                if (site != null) {
                    ac = new AppContext(user.getUserId(), site.getSiteId());
                } else {
                    ac = new AppContext(user.getUserId(), null);
                }
            } else {
                ac = new AppContext(user.getUserId(), null);
            }
            getSession().put(Constants.APP_CONTEXT, ac);
            return true;
        }
    }

    public void checkLogin() {
        if (getMe() == null) {
            try {
                ServletActionContext.getResponse()
                        .sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public int getCount() {
        int count = 0;
        try {
            count = ViewStatsService.totalView();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return count;
    }

    public void internalRedirects(String s) {
        try {
            ServletActionContext.getResponse().sendRedirect(s);
        } catch (IOException e) {
        }
    }

    public String getParameter(String s) {
        return ServletActionContext.getRequest().getParameter(s);
    }

    public String getIpAddress() {
        String ip = ServletActionContext.getRequest().getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || (isLocalIP(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = ServletActionContext.getRequest().getHeader("Proxy-Client-IP");
        }

        if ((ip == null) || (ip.length() == 0) || (isLocalIP(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");
        }

        if ((ip == null) || (ip.length() == 0) || (isLocalIP(ip)) || "unknown".equalsIgnoreCase(ip)) {
            ip = ServletActionContext.getRequest().getRemoteAddr();
        }
        System.out.println("BaseAction.getIpAddress ip = " + ip);
        return ip;
    }

    private static boolean isLocalIP(String ip) {
        return ip.startsWith("192.168.") || ip.startsWith("172.16.") || ip.startsWith("10.");
    }

    public String getCookie(String name) {
        String value = null;
        Cookie[] cs = ServletActionContext.getRequest().getCookies();
        try {
            for (Cookie c : cs) {
                if (c.getName().equalsIgnoreCase(name)) {
                    value = c.getValue();
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        return value;
    }
}
