package com.sitexa.user.data;

/**
 * MemberPositionId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberPositionId implements java.io.Serializable {

    // Fields

    private Member member;
    private Position position;

    // Constructors

    /**
     * default constructor
     */
    public MemberPositionId() {
    }

    /**
     * full constructor
     */
    public MemberPositionId(Member member, Position position) {
        this.member = member;
        this.position = position;
    }

    // Property accessors

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MemberPositionId))
            return false;
        MemberPositionId castOther = (MemberPositionId) other;

        return ((this.getMember() == castOther.getMember()) || (this
                .getMember() != null
                && castOther.getMember() != null && this.getMember().equals(
                castOther.getMember())))
                && ((this.getPosition() == castOther.getPosition()) || (this
                .getPosition() != null
                && castOther.getPosition() != null && this
                .getPosition().equals(castOther.getPosition())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getMember() == null ? 0 : this.getMember().hashCode());
        result = 37 * result
                + (getPosition() == null ? 0 : this.getPosition().hashCode());
        return result;
    }

}