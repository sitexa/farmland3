package com.sitexa.user.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.service.SiteService;
import com.sitexa.sys.data.Func;
import com.sitexa.sys.data.Role;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-2
 * Time: 20:23:56
 */
public class UserAction extends BaseAction implements ModelDriven<Object> {
    protected String searchWord = "";
    protected Page page = new Page();

    protected String id;
    protected User user;
    protected Site site;
    protected List<User> userList = new ArrayList<User>();

    protected List<Role> roleList = new ArrayList<Role>();
    protected List<Func> funcList = new ArrayList<Func>();

    protected String oldPassword;
    protected String newPassword;

    protected Member member;
    protected String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Func> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<Func> funcList) {
        this.funcList = funcList;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Object getModel() {
        if (user != null) return user;
        else if (userList.size() > 0) return userList;
        else return null;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
