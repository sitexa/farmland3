package com.sitexa.user.data;

import com.sitexa.framework.KV;
import com.sitexa.site.data.Site;

import java.util.*;

/**
 * Member entity.
 *
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")
public class Member implements java.io.Serializable {

    /**
     * 1,照片;
     * 2,QQ号;
     * 3,MSN;
     * 4,个人网站;
     * 5,博客;
     * 6,个人说明;
     * 7,职业;
     * 8,学历/学位;
     * 9,兴趣爱好;
     * 10,签名档;
     * 11,...
     * <p/>
     * 小区官员:
     * 1,普通内容管理员;
     * 2,物业管理员;
     * 3,安全管理员;
     * 4,活动管理员;
     * 5,商务管理员;
     * 6,...
     */
    // Fields

    public static String GENDER_MALE = "1";
    public static String GENDER_FEMALE = "2";
    public static String GENDER_NONE = "0";
    public static Map genderBag = new HashMap();

    static {
        genderBag.put(GENDER_MALE, "男");
        genderBag.put(GENDER_FEMALE, "女");
        genderBag.put(GENDER_NONE, "保密");
    }

    public static List<KV> STARS = new ArrayList<KV>();

    static {
        STARS.add(new KV("0", "暗星"));
        STARS.add(new KV("1", "明星"));
    }

    private String memberId;
    private User user;
    private MemberType type;
    private Site site;
    private String nickname;
    private String realname;
    private String telephone;
    private String mobilephone;
    private String gender;
    private Date birthday;
    private Date updateDate;
    private String introduction;
    private String starTag;
    private String status;
    private Set rights = new HashSet(0);
    private Set properties = new HashSet(0);
    private Set pictures = new HashSet(0);
    private Set residents = new HashSet(0);
    private Set positions = new HashSet(0);
    private Set friends = new HashSet(0);
    private Set visitors = new HashSet(0);
    private Set posts = new HashSet(0);
    private Set farms = new HashSet(0);
    private Set ownFarms = new HashSet(0);
    // Constructors

    public Member() {
    }

    public Member(String memberId, Site site, String nickname,
                  Date updateDate) {
        this.memberId = memberId;
        this.site = site;
        this.nickname = nickname;
        this.updateDate = updateDate;
    }

    // Property accessors

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MemberType getType() {
        return this.type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilephone() {
        return this.mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getGender() {
        return this.gender;
    }

    public String getGenderName() {
        if (GENDER_MALE.equals(gender)) {
            return "男";
        } else if (GENDER_FEMALE.equals(gender)) {
            return "女";
        } else {
            return "保密";
        }
    }

    public void setGenderName(String name) {

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStarTag() {
        return starTag;
    }

    public void setStarTag(String starTag) {
        this.starTag = starTag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set getRights() {
        return this.rights;
    }

    public void setRights(Set rights) {
        this.rights = rights;
    }

    public Set getProperties() {
        return this.properties;
    }

    public void setProperties(Set properties) {
        this.properties = properties;
    }

    public Set getResidents() {
        return this.residents;
    }

    public void setResidents(Set residents) {
        this.residents = residents;
    }

    public Set getPositions() {
        return this.positions;
    }

    public void setPositions(Set positions) {
        this.positions = positions;
    }

    public Set getPictures() {
        return pictures;
    }

    public void setPictures(Set pictures) {
        this.pictures = pictures;
    }

    public Set getFriends() {
        return friends;
    }

    public void setFriends(Set friends) {
        this.friends = friends;
    }

    public Set getVisitors() {
        return visitors;
    }

    public void setVisitors(Set visitors) {
        this.visitors = visitors;
    }

    public Set getPosts() {
        return posts;
    }

    public void setPosts(Set posts) {
        this.posts = posts;
    }

    public Set getFarms() {
        return farms;
    }

    public void setFarms(Set farms) {
        this.farms = farms;
    }

    public Set getOwnFarms() {
        return ownFarms;
    }

    public void setOwnFarms(Set ownFarms) {
        this.ownFarms = ownFarms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberId.equals(member.memberId)) return true;
        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + nickname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", updateDate=" + updateDate +
                '}';
    }
}