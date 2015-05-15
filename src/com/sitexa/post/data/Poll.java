package com.sitexa.post.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Poll entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Poll implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private Date startDate;
    private Date endDate;
    private Set pollOptions = new HashSet(0);
    private Set pollVotes = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Poll() {
    }

    /**
     * minimal constructor
     */
    public Poll(String id, Post post, Date startDate) {
        this.id = id;
        this.post = post;
        this.startDate = startDate;
    }

    public Poll(String id, Post post, Date startDate, Date endDate, Set pollOptions, Set pollVotes) {
        this.id = id;
        this.post = post;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pollOptions = pollOptions;
        this.pollVotes = pollVotes;
    }

    public String getId() {
        return id;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set getPollOptions() {
        return pollOptions;
    }

    public void setPollOptions(Set pollOptions) {
        this.pollOptions = pollOptions;
    }

    public Set getPollVotes() {
        return pollVotes;
    }

    public void setPollVotes(Set pollVotes) {
        this.pollVotes = pollVotes;
    }
}