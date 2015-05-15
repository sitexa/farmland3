package com.sitexa.user.data;

/**
 * MemberRightId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberRightId implements java.io.Serializable {

    // Fields

    private Member member;
    private Right right;

    // Constructors

    /**
     * default constructor
     */
    public MemberRightId() {
    }

    /**
     * full constructor
     */
    public MemberRightId(Member member, Right right) {
        this.member = member;
        this.right = right;
    }

    // Property accessors

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MemberRightId))
            return false;
        MemberRightId castOther = (MemberRightId) other;

        return ((this.getMember() == castOther.getMember()) || (this
                .getMember() != null
                && castOther.getMember() != null && this.getMember().equals(
                castOther.getMember())))
                && ((this.getRight() == castOther.getRight()) || (this
                .getRight() != null
                && castOther.getRight() != null && this.getRight()
                .equals(castOther.getRight())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getMember() == null ? 0 : this.getMember().hashCode());
        result = 37 * result
                + (getRight() == null ? 0 : this.getRight().hashCode());
        return result;
    }

}