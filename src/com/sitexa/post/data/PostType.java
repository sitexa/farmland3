package com.sitexa.post.data;

/**
 * PostType entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PostType implements java.io.Serializable {

    // Fields

    private String typeId;
    private String name;
    private String objectId;
    // Constructors

    /**
     * default constructor
     */
    public PostType() {
    }

    /**
     * minimal constructor
     */
    public PostType(String typeId, String name, String objectId) {
        this.typeId = typeId;
        this.name = name;
        this.objectId = objectId;
    }
    // Property accessors

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
	}

    @Override
    public String toString() {
        return "PostType{" +
                "typeId='" + typeId + '\'' +
                ", name='" + name + '\'' +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}