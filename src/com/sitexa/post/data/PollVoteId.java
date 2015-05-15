package com.sitexa.post.data;

import com.sitexa.user.data.Member;

/**
 * PollVoteId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PollVoteId implements java.io.Serializable {

    // Fields

    private Poll poll;
    private Member member;

    // Constructors

    /**
     * default constructor
     */
    public PollVoteId() {
    }

    /**
     * full constructor
     */
    public PollVoteId(Poll poll, Member user) {
        this.poll = poll;
        this.member = user;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PollVoteId))
            return false;
        PollVoteId castOther = (PollVoteId) other;

        return ((this.getPoll() == castOther.getPoll()) || (this.getPoll() != null
                && castOther.getPoll() != null && this.getPoll().equals(
                castOther.getPoll())))
                && ((this.getMember() == castOther.getMember()) || (this
                .getMember() != null
                && castOther.getMember() != null && this.getMember()
                .equals(castOther.getMember())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getPoll() == null ? 0 : this.getPoll().hashCode());
        result = 37 * result
                + (getMember() == null ? 0 : this.getMember().hashCode());
        return result;
    }

}