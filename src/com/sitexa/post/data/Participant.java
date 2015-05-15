package com.sitexa.post.data;

import com.sitexa.user.data.Member;

import java.util.Date;

/**
 * Participant entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Participant implements java.io.Serializable {

    // Fields

    private String id;
    private Activity activity;
    private Member member;
    private Date joinDate;
    private String comment;

    // Constructors

    /**
     * default constructor
     */
    public Participant() {
    }

    public Participant(String id, Activity activity, Member member) {
        this.id = id;
        this.activity = activity;
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}