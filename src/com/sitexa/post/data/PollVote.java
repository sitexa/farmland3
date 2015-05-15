package com.sitexa.post.data;

import java.util.Date;

/**
 * PollVote entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PollVote implements java.io.Serializable {

    // Fields

    private PollVoteId id;
    private String optionIndex;
    private Date voteDate;
    private String comment;

    // Constructors

    /**
     * default constructor
     */
    public PollVote() {
    }

    /**
     * minimal constructor
     */
    public PollVote(PollVoteId id, String optionIndex) {
        this.id = id;
        this.optionIndex = optionIndex;
    }

    /**
     * full constructor
     */
    public PollVote(PollVoteId id, String optionIndex, Date voteDate,
                    String comment) {
        this.id = id;
        this.optionIndex = optionIndex;
        this.voteDate = voteDate;
        this.comment = comment;
    }

    // Property accessors

    public PollVoteId getId() {
        return this.id;
    }

    public void setId(PollVoteId id) {
        this.id = id;
    }

    public String getOptionIndex() {
        return this.optionIndex;
    }

    public void setOptionIndex(String optionIndex) {
        this.optionIndex = optionIndex;
    }

    public Date getVoteDate() {
        return this.voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
	}

}