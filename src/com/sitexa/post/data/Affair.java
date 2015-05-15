package com.sitexa.post.data;


/**
 * Affair entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Affair implements java.io.Serializable {

    // Fields

    private String id;
    private PostType type;
    private Post post;

    // Constructors

    /**
     * default constructor
     */
    public Affair() {
    }

    /**
     * full constructor
     */
    public Affair(String id, PostType type, Post post) {
        this.id = id;
        this.type = type;
        this.post = post;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PostType getType() {
        return this.type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}