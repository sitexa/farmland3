package com.sitexa.post.data;

/**
 * PostProperty entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class PostProperty implements java.io.Serializable {

    // Fields

    private String propId;
    private Post post;
    private String propName;
    private String propValue;

    // Constructors

    /**
     * default constructor
     */
    public PostProperty() {
    }

    /**
     * full constructor
     */
    public PostProperty(String propId, Post post, String propName,
                        String propValue) {
        this.propId = propId;
        this.post = post;
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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostProperty that = (PostProperty) o;

        if (!propId.equals(that.propId)) return false;
        if (!propName.equals(that.propName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propId.hashCode();
        result = 31 * result + propName.hashCode();
        return result;
    }
}