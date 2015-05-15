package com.sitexa.post.data;

/**
 * CategoryProperty entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class CategoryProperty implements java.io.Serializable {

    // Fields

    private String propId;
    private Category category;
    private String propName;
    private String propValue;

    // Constructors

    /**
     * default constructor
     */
    public CategoryProperty() {
    }

    /**
     * full constructor
     */
    public CategoryProperty(String propId, Category category, String propName,
                            String propValue) {
        this.propId = propId;
        this.category = category;
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

}