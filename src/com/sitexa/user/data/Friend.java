package com.sitexa.user.data;

/**
 * Friend entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Friend implements java.io.Serializable {

    // Fields

    private String id;
    private Member member;
    private Member fellow;
    private String comment;
    private String remark;
    private FriendGroup group;
    private Boolean status;

    // Constructors

    /**
     * default constructor
     */
    public Friend() {
    }

    public Friend(String id, Member member, Member fellow) {
        this.id = id;
        this.member = member;
        this.fellow = fellow;
    }

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

    public Member getFellow() {
        return fellow;
    }

    public void setFellow(Member fellow) {
        this.fellow = fellow;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public FriendGroup getGroup() {
        return group;
    }

    public void setGroup(FriendGroup group) {
        this.group = group;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", member=" + member +
                ", fellow=" + fellow +
                ", comment='" + comment + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}