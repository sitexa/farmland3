package com.sitexa.user.data;

/**
 * MemberPosition entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberPosition implements java.io.Serializable {

    // Fields

    private MemberPositionId id;

    // Constructors

    /**
     * default constructor
     */
    public MemberPosition() {
    }

    /**
     * full constructor
     */
    public MemberPosition(MemberPositionId id) {
        this.id = id;
    }

    // Property accessors

    public MemberPositionId getId() {
        return this.id;
    }

    public void setId(MemberPositionId id) {
        this.id = id;
	}

}