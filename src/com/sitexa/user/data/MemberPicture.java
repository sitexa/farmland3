package com.sitexa.user.data;

/**
 * MemberPicture entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberPicture implements java.io.Serializable {

    // Fields

    private String picId;
    private Member member;
    private String picTitle;
    private byte[] abbrev;
    private String picUrl;
    private String description;

    // Constructors

    /**
     * default constructor
     */
    public MemberPicture() {
    }

    /**
     * minimal constructor
     */
    public MemberPicture(String picId, Member member, String picTitle,String description) {
        this.picId = picId;
        this.member = member;
        this.picTitle = picTitle;
        this.description = description;
    }

    /**
     * full constructor
     */
    public MemberPicture(String picId, Member member, String picTitle,
                         String picUrl, String description) {
        this.picId = picId;
        this.member = member;
        this.picTitle = picTitle;
        this.picUrl = picUrl;
        this.description = description;
    }

    // Property accessors

    public String getPicId() {
        return this.picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getPicTitle() {
        return this.picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public byte[] getAbbrev() {
        return this.abbrev;
    }

    public void setAbbrev(byte[] abbrev) {
        this.abbrev = abbrev;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberPicture that = (MemberPicture) o;

        if (!member.equals(that.member)) return false;
        if (!picId.equals(that.picId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = picId.hashCode();
        result = 31 * result + member.hashCode();
        return result;
    }
}