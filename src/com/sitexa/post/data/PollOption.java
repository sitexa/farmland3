package com.sitexa.post.data;

/**
 * PollOption entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PollOption implements java.io.Serializable {

    // Fields

    private PollOptionId id;
    private String optionText;

    // Constructors

    /**
     * default constructor
     */
    public PollOption() {
    }

    /**
     * full constructor
     */
    public PollOption(PollOptionId id, String optionText) {
        this.id = id;
        this.optionText = optionText;
    }

    // Property accessors

    public PollOptionId getId() {
        return this.id;
    }

    public void setId(PollOptionId id) {
        this.id = id;
    }

    public String getOptionText() {
        return this.optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
	}

}