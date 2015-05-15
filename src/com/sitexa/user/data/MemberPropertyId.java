package com.sitexa.user.data;

/**
 * MemberPropertyId entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberPropertyId implements java.io.Serializable {

    // Fields

    private String propId;
    private Member member;

    // Constructors

    /**
     * default constructor
     */
    public MemberPropertyId() {
    }

    /**
     * full constructor
     */
    public MemberPropertyId(String propId, Member member) {
        this.propId = propId;
        this.member = member;
    }

    // Property accessors

    public String getPropId() {
        return this.propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MemberPropertyId))
            return false;
        MemberPropertyId castOther = (MemberPropertyId) other;

        return ((this.getPropId() == castOther.getPropId()) || (this
                .getPropId() != null
                && castOther.getPropId() != null && this.getPropId().equals(
                castOther.getPropId())))
                && ((this.getMember() == castOther.getMember()) || (this
                .getMember() != null
                && castOther.getMember() != null && this.getMember()
                .equals(castOther.getMember())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getPropId() == null ? 0 : this.getPropId().hashCode());
        result = 37 * result
                + (getMember() == null ? 0 : this.getMember().hashCode());
        return result;
	}

    @Override
    public String toString() {
        return "MemberPropertyId{" +
                "propId='" + propId + '\'' +
                ", member=" + member +
                '}';
    }
}