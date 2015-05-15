package com.sitexa.post.data;

import java.util.Date;

/**
 * Announce entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Announce implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private Date startDate;
    private Date endDate;

    // Constructors

    /**
     * default constructor
     */
    public Announce() {
    }

    /**
     * minimal constructor
     */
    public Announce(String id, Post post, Date startDate) {
        this.id = id;
        this.post = post;
        this.startDate = startDate;
    }

    /**
     * full constructor
     */
    public Announce(String id, Post post, Date startDate, Date endDate) {
        this.id = id;
        this.post = post;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

}