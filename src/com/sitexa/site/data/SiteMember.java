package com.sitexa.site.data;

import com.sitexa.framework.KV;
import com.sitexa.user.data.Member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SiteMember entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SiteMember implements java.io.Serializable {

    // Fields

    private String id;
    private Member member;
    private Site site;
    //type:null/0:inside member;1:friend member
    private String type;
    private String remark;
    private Date joinDate;
    private String status;

    public static final String TYPE_INNER = "0";
    public static final String TYPE_OUTTER = "1";

    public static final String STATUS_BAN = "0";
    public static final String STATUS_ACT = "1";

    public static List<KV> STATUS = new ArrayList<KV>();

    static {
        STATUS.add(new KV("0", "只读"));
        STATUS.add(new KV("1", "可写"));
    }

    public static List<KV> TYPES = new ArrayList<KV>();

    static {
        TYPES.add(new KV("0", "内部"));
        TYPES.add(new KV("1", "外部"));
    }

    // Constructors

    /**
     * default constructor
     */
    public SiteMember() {
    }

    /**
     * minimal constructor
     */
    public SiteMember(String id, Member member, Site site) {
        this.id = id;
        this.member = member;
        this.site = site;
    }

    /**
     * full constructor
     */
    public SiteMember(String id, Member member, Site site, String type,
                      String remark, Date joinDate, String status) {
        this.id = id;
        this.member = member;
        this.site = site;
        this.type = type;
        this.remark = remark;
        this.joinDate = joinDate;
        this.status = status;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Site getSite() {
        return this.site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getType() {
        return this.type;
    }

    public String getTypeName() {
        if ("1".equals(type)) {
            return "外部";
        } else {
            return "内部";
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getStatusName() {
        if (!"0".equals(status)) {
            return "可写";
        } else {
            return "只读";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SiteMember{" +
                "id='" + id + '\'' +
                ", member=" + member +
                ", site=" + site +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", joinDate=" + joinDate +
                ", status='" + status + '\'' +
                '}';
    }
}