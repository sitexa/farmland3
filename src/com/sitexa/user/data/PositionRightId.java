package com.sitexa.user.data;

/**
 * PositionRightId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PositionRightId implements java.io.Serializable {

    // Fields

    private Position position;
    private Right right;

    // Constructors

    /**
     * default constructor
     */
    public PositionRightId() {
    }

    /**
     * full constructor
     */
    public PositionRightId(Position position, Right right) {
        this.position = position;
        this.right = right;
    }

    // Property accessors

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Right getRight() {
        return this.right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof PositionRightId))
            return false;
        PositionRightId castOther = (PositionRightId) other;

        return ((this.getPosition() == castOther.getPosition()) || (this
                .getPosition() != null
                && castOther.getPosition() != null && this.getPosition()
                .equals(castOther.getPosition())))
                && ((this.getRight() == castOther.getRight()) || (this
                .getRight() != null
                && castOther.getRight() != null && this.getRight()
                .equals(castOther.getRight())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getPosition() == null ? 0 : this.getPosition().hashCode());
        result = 37 * result
                + (getRight() == null ? 0 : this.getRight().hashCode());
        return result;
    }

}