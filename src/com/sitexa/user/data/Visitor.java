package com.sitexa.user.data;

import java.util.Date;

/**
 * Visitor entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Visitor implements java.io.Serializable {

    // Fields

    private String id;
    private Member member;
    private Member guest;
    private Date visitDate;
    private Integer times;

    // Constructors

    /**
     * default constructor
     */
    public Visitor() {
    }

    public Visitor(String id, Member member, Member guest) {
        this.id = id;
        this.member = member;
        this.guest = guest;
    }

    public Visitor(String id, Member member, Member guest, Date visitDate, Integer times) {
        this.id = id;
        this.member = member;
        this.guest = guest;
        this.visitDate = visitDate;
        this.times = times;
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

    public Member getGuest() {
        return guest;
    }

    public void setGuest(Member guest) {
        this.guest = guest;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (!guest.equals(visitor.guest)) return false;
        if (!id.equals(visitor.id)) return false;
        if (!member.equals(visitor.member)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + member.hashCode();
        result = 31 * result + guest.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id='" + id + '\'' +
                ", member=" + member +
                ", guest=" + guest +
                ", visitDate=" + visitDate +
                ", times=" + times +
                '}';
    }
}