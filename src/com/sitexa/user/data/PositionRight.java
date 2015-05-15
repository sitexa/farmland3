package com.sitexa.user.data;

/**
 * PositionRight entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PositionRight implements java.io.Serializable {

    // Fields

    private PositionRightId id;

    // Constructors

    /**
     * default constructor
     */
    public PositionRight() {
    }

    /**
     * full constructor
     */
    public PositionRight(PositionRightId id) {
        this.id = id;
    }

    // Property accessors

    public PositionRightId getId() {
        return this.id;
    }

    public void setId(PositionRightId id) {
        this.id = id;
	}

}