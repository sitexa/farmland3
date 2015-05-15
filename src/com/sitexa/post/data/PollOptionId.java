package com.sitexa.post.data;

/**
 * PollOptionId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PollOptionId implements java.io.Serializable {

    // Fields

    private Poll poll;
    private String optionIndex;

    // Constructors

    /**
     * default constructor
     */
    public PollOptionId() {
    }

    /**
     * full constructor
     */
    public PollOptionId(Poll poll, String optionIndex) {
        this.poll = poll;
        this.optionIndex = optionIndex;
    }

    // Property accessors

    public Poll getPoll() {
        return this.poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getOptionIndex() {
        return this.optionIndex;
    }

    public void setOptionIndex(String optionIndex) {
        this.optionIndex = optionIndex;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PollOptionId))
            return false;
        PollOptionId castOther = (PollOptionId) other;

        return ((this.getPoll() == castOther.getPoll()) || (this.getPoll() != null
                && castOther.getPoll() != null && this.getPoll().equals(
                castOther.getPoll())))
                && ((this.getOptionIndex() == castOther.getOptionIndex()) || (this
                .getOptionIndex() != null
                && castOther.getOptionIndex() != null && this
                .getOptionIndex().equals(castOther.getOptionIndex())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getPoll() == null ? 0 : this.getPoll().hashCode());
        result = 37
                * result
                + (getOptionIndex() == null ? 0 : this.getOptionIndex()
                .hashCode());
        return result;
	}

}