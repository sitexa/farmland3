package com.sitexa.user.data;

/**
 * MemberCredit entity. @author MyEclipse Persistence Tools
 */

public class MemberCredit implements java.io.Serializable {

    // Fields

    private String memberId;
    private Member member;
    private Integer credit;
    private String password;

    // Constructors

    /**
     * default constructor
     */
    public MemberCredit() {
    }

    // Property accessors

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getCredit() {
        return this.credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}