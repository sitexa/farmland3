package com.sitexa.user.data;

/**
 * MemberProperty entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class MemberProperty implements java.io.Serializable {

    // Fields
    /**
     * 1,照片;
     * 2,QQ号;
     * 3,MSN;
     * 4,个人网站;
     * 5,博客;
     * 6,个人说明;
     * 7,职业;
     * 8,学历/学位;
     * 9,兴趣爱好;
     * 10,签名档;
     * 11,...
     */

    private String propId;
    private Member member;
    private String propName;
    private String propValue;
    private Boolean publish;

    public static final String[] MEMBER_PROPERTY_ITEMS = {
            "QQ号",
            "MSN",
            "个人网站",
            "博客",
            "个人说明",
            "职业",
            "学历/学位",
            "兴趣爱好",
            "签名档"
    };

    // Constructors

    /**
     * default constructor
     */
    public MemberProperty() {
    }

    /**
     * full constructor
     */
    public MemberProperty(String propId, Member member, String propName,
                          String propValue) {
        this.propId = propId;
        this.member = member;
        this.propName = propName;
        this.propValue = propValue;
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

    public String getPropName() {
        return this.propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return this.propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberProperty that = (MemberProperty) o;

        if (!member.equals(that.member)) return false;
        if (!propId.equals(that.propId)) return false;
        if (!propName.equals(that.propName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propId.hashCode();
        result = 31 * result + member.hashCode();
        result = 31 * result + propName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MemberProperty{" +
                "propId='" + propId + '\'' +
                ", member=" + member.getMemberId() +
                ", propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                ", publish=" + publish +
                '}';
    }
}