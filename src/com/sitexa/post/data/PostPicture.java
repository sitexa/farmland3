package com.sitexa.post.data;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 20:10:44
 */
public class PostPicture implements java.io.Serializable {

    // Fields

    private String picId;
    private Post post;
    private String picTitle;
    private byte[] abbrev;
    private String picUrl;
    private String description;

    // Constructors

    /**
     * default constructor
     */
    public PostPicture() {
    }

    public PostPicture(String picId,String picTitle, String description) {
        this.picId = picId;
        this.picTitle = picTitle;
        this.description = description;
    }

    /**
     * minimal constructor
     */
    public PostPicture(String picId, Post post,String picTitle, String description) {
        this.picId = picId;
        this.post = post;
        this.picTitle = picTitle;
        this.description = description;
    }

    /**
     * full constructor
     */
    public PostPicture(String picId, Post post, String picTitle,
                       String picUrl, String description) {
        this.picId = picId;
        this.post = post;
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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
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
}
