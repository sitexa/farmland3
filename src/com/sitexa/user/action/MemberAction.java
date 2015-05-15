package com.sitexa.user.action;

import com.opensymphony.xwork2.ModelDriven;
import com.sitexa.framework.Constants;
import com.sitexa.framework.KV;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: xnpeng
 * Date: 2009-4-4
 * Time: 21:59:40
 */
public class MemberAction extends BaseAction implements ModelDriven<Object> {
    protected String id;
    protected Member member;
    protected User user;
    protected MemberType type;
    protected List<MemberType> memberTypeList = new ArrayList<MemberType>();

    protected MemberProperty property;
    protected List<MemberProperty> properties = new ArrayList<MemberProperty>();

    protected MemberPicture picture;
    protected List<MemberPicture> pictures = new ArrayList<MemberPicture>();
    protected List<Member> members = new ArrayList<Member>();
    protected Page page = new Page();
    protected Site site;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public MemberProperty getProperty() {
        return property;
    }

    public void setProperty(MemberProperty property) {
        this.property = property;
    }

    public List<MemberType> getMemberTypeList() {
        return memberTypeList;
    }

    public void setMemberTypeList(List<MemberType> memberTypeList) {
        this.memberTypeList = memberTypeList;
    }

    public List<MemberProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<MemberProperty> properties) {
        this.properties = properties;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MemberPicture getPicture() {
        return picture;
    }

    public void setPicture(MemberPicture picture) {
        this.picture = picture;
    }

    public List<MemberPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<MemberPicture> pictures) {
        this.pictures = pictures;
    }

    public String[] getMemberPropertyItems() {
        return MemberProperty.MEMBER_PROPERTY_ITEMS;
    }

    public Map getGenderBag() {
        return Member.genderBag;
    }

    public List<KV> getStarBag() {
        return Member.STARS;
    }

    public Object getModel() {
        return member;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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
