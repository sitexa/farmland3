package com.sitexa.post.data;

/**
 * Business entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Business implements java.io.Serializable {

    // Fields

    private String id;
    private PostType type;
    private Post post;

    // Constructors

    /**
     * default constructor
     */
    public Business() {
    }

    /**
     * minimal constructor
     */
    public Business(String id, PostType type, Post post) {
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
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}