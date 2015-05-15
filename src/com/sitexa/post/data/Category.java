package com.sitexa.post.data;

import com.sitexa.site.data.Site;
import com.sitexa.user.data.Member;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

    //what types?
    //1,公共栏目;
    //2,社区栏目;
    //3,用户栏目;
    public static final String[] TYPES = {"1", "2", "3"};
    public static final String MESSAGE = "message";
    public static final String ACTIVITY = "activity";
    public static final String AFFAIR = "affair";
    public static final String BUSINESS = "business";
    public static final String ANNOUNCE = "announce";
    public static final String POLL = "poll";
    public static final String HOUSE = "house";
    public static final String SCENERY = "scenery";
    public static final String CASE = "case";
    public static final String CSA = "csa";
    public static final String LANDPOST = "landpost";
    public static final String FARMPOST = "farmpost";

    // Fields

    private String cateId;
    private Category parent;
    private String name;
    private String type;
    private String description;
    private int ord;
    private String status;
    private Site site;
    private Member member;
    private Set properties = new HashSet(0);
    private Set children = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Category() {
    }

    /**
     * minimal constructor
     */
    public Category(String cateId, String name) {
        this.cateId = cateId;
        this.name = name;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set getProperties() {
        return properties;
    }

    public void setProperties(Set properties) {
        this.properties = properties;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
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

        Category category = (Category) o;

        if (!cateId.equals(category.cateId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cateId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId='" + cateId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}