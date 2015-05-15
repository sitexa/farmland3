package com.sitexa.post.data;

import java.util.Date;

/**
 * Case entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Case implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private Date caseTime;
    private String location;

    // Constructors

    /**
     * default constructor
     */
    public Case() {
    }

    /**
     * minimal constructor
     */
    public Case(String id, Post post, Date caseTime) {
        this.id = id;
        this.post = post;
        this.caseTime = caseTime;
    }

    /**
     * full constructor
     */
    public Case(String id, Post post, Date caseTime, String location) {
        this.id = id;
        this.post = post;
        this.caseTime = caseTime;
        this.location = location;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getCaseTime() {
        return this.caseTime;
    }

    public void setCaseTime(Date caseTime) {
        this.caseTime = caseTime;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}