package com.sitexa.site.data;

/**
 * SiteType entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SiteType implements java.io.Serializable {

    // Fields

    private String typeId;
    private String typeName;
    private String governor;

    // Constructors

    /**
     * default constructor
     */
    public SiteType() {
    }

    public SiteType(String typeId) {
        this.typeId = typeId;
    }

    /**
     * minimal constructor
     */
    public SiteType(String typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        if (typeName == null && typeId != null)
            typeName = (new SiteTypeDAO()).findById(typeId).getTypeName();
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getGovernor() {
        return governor;
    }

    public void setGovernor(String governor) {
        this.governor = governor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteType siteType = (SiteType) o;

        if (!typeId.equals(siteType.typeId)) return false;
        if (!typeName.equals(siteType.typeName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId.hashCode();
        result = 31 * result + typeName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SiteType{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                "}\n";
    }
}