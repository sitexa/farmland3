package com.sitexa.user.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.User;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.Friend;
import com.sitexa.site.data.Site;

import java.util.List;
import java.util.ArrayList;

/**
 * User: xnpeng
 * Date: 2009-5-6
 * Time: 20:33:24
 */
public class FriendAction extends BaseAction {
    protected String id;
    protected User user;
    protected Member member;
    protected Site site;
    protected Friend friend;
    protected List<Friend> friends = new ArrayList<Friend>();
    protected List<Friend> fellows = new ArrayList<Friend>();

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFellows() {
        return fellows;
    }

    public void setFellows(List<Friend> fellows) {
        this.fellows = fellows;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
