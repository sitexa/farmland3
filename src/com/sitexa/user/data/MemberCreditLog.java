package com.sitexa.user.data;

import java.util.Date;

/**
 * MemberCreditLog entity. @author MyEclipse Persistence Tools
 */

public class MemberCreditLog implements java.io.Serializable {

    // Fields

    private Integer logId;
    private Member member;
    private Date eventDate;
    private Member member2;
    private String contents;
    private Integer amount;
    private Integer incDec;
    private String orderNo;

    // Constructors

    /**
     * default constructor
     */
    public MemberCreditLog() {
    }

    // Property accessors

    public Integer getLogId() {
        return this.logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getIncDec() {
        return this.incDec;
    }

    public void setIncDec(Integer incDec) {
        this.incDec = incDec;
    }

    public Member getMember2() {
        return member2;
    }

    public void setMember2(Member member2) {
        this.member2 = member2;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}