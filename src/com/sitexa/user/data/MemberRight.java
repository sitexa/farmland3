package com.sitexa.user.data;

/**
 * MemberRight entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberRight implements java.io.Serializable {

    // Fields

    private MemberRightId id;

    // Constructors

    /**
     * default constructor
     */
    public MemberRight() {
    }

    /**
     * full constructor
     */
    public MemberRight(MemberRightId id) {
        this.id = id;
    }

    // Property accessors

    public MemberRightId getId() {
        return this.id;
    }

    public void setId(MemberRightId id) {
        this.id = id;
	}

}