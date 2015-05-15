package com.sitexa.post.data;

/**
 * Csa entity. @author MyEclipse Persistence Tools
 */

public class Csa implements java.io.Serializable {
    
    // Fields

    private String id;
    private PostType csaType;
    private Post post;

    // Constructors

    /**
     * default constructor
     */
    public Csa() {
    }

    /**
     * minimal constructor
     */
    public Csa(String id, PostType csaType) {
        this.id = id;
        this.csaType = csaType;
    }

    public Csa(String id, PostType csaType, Post post) {
        this.id = id;
        this.csaType = csaType;
        this.post = post;
    }
// Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PostType getCsaType() {
        return csaType;
    }

    public void setCsaType(PostType csaType) {
        this.csaType = csaType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}