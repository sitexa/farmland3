package com.sitexa.post.data;

import com.sitexa.framework.KV;
import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.*;

/**
 * Post entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Post implements java.io.Serializable {


    /**
     * post分为以下种类/栏目(Category):
     * message(msg);
     * activity(type);
     * affair(type);
     * business(type);
     * announce;
     * poll;
     * house(style,type,resident);
     * scenery(landscape);
     * case;
     */
    // Fields
    public static final String STATUS_BAN = "1";
    public static final String STATUS_PUB = "0";
    public static List<KV> STATUSES = new ArrayList<KV>();

    static {
        STATUSES.add(new KV("1", "禁止"));
        STATUSES.add(new KV("0", "发布"));
    }

    private String id;
    private Member creator;
    private Post parent;
    private Site site;
    private Category category;
    private String name;
    private String status;
    //status:1:禁止(ban)
    private int vwCnt;
    private Date createDate;
    private Date modifyDate;
    private String content;
    private String subject;
    private String tags;
    private Boolean topTag;
    private Boolean eliteTag;
    private Boolean audit;
    private Member auditor;
    private Set properties = new HashSet(0);
    private Set pictures = new HashSet(0);
    private Set children = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Post() {
    }

    /**
     * minimal constructor
     */
    public Post(String id, Member creator, Site site, Category category,
                String name, String status, Date createDate) {
        this.id = id;
        this.creator = creator;
        this.site = site;
        this.category = category;
        this.name = name;
        this.status = status;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public String statusName() {
        if (status.equals("1"))
            return "禁发";
        else
            return "发布";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVwCnt() {
        return vwCnt;
    }

    public void setVwCnt(int vwCnt) {
        this.vwCnt = vwCnt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set getProperties() {
        return properties;
    }

    public void setProperties(Set properties) {
        this.properties = properties;
    }

    public Member getCreator() {
        return creator;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getTopTag() {
        return topTag;
    }

    public void setTopTag(Boolean topTag) {
        this.topTag = topTag;
    }

    public Boolean getEliteTag() {
        return eliteTag;
    }

    public void setEliteTag(Boolean eliteTag) {
        this.eliteTag = eliteTag;
    }

    public static List<KV> getSTATUSES() {
        return STATUSES;
    }

    public static void setSTATUSES(List<KV> STATUSES) {
        Post.STATUSES = STATUSES;
    }

    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    public Member getAuditor() {
        return auditor;
    }

    public void setAuditor(Member auditor) {
        this.auditor = auditor;
    }

    public Set getPictures() {
        return pictures;
    }

    public void setPictures(Set pictures) {
        this.pictures = pictures;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!id.equals(post.id)) return false;
        if (!name.equals(post.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "\nid='" + id + '\'' +
                ", \ncreator=" + creator +
                ", \nsite=" + site +
                ", \ncategory=" + category +
                ", \nname='" + name + '\'' +
                ", \nstatus='" + status + '\'' +
                ", \nvwCnt=" + vwCnt +
                ", \ncreateDate=" + createDate +
                ", \nmodifyDate=" + modifyDate +
                '}';
    }
}