package com.sitexa.post.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Activity entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Activity implements java.io.Serializable {

    // Fields

    private String id;
    private PostType type;
    private Post post;
    private Date startDate;
    private Date endDate;
    private Date joinEndDate;
    private String contact;
    private String expense;
    private String address;
    private Set participants = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Activity() {
    }


    public Activity(String id, PostType type, Post post,
                    Date startDate) {
        this.id = id;
        this.type = type;
        this.post = post;
        this.startDate = startDate;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PostType getType() {
        return this.type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getJoinEndDate() {
        return this.joinEndDate;
    }

    public void setJoinEndDate(Date joinEndDate) {
        this.joinEndDate = joinEndDate;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExpense() {
        return this.expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set participants) {
        this.participants = participants;
    }

}