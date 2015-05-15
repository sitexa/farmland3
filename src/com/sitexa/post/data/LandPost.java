package com.sitexa.post.data;

import com.sitexa.farm.data.Land;

/**
 * LandPost entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class LandPost implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private Land land;
    private PostType type;

    // Constructors

    /**
     * default constructor
     */
    public LandPost() {
    }

    public LandPost(Post post, Land land, PostType type) {
        this.post = post;
        this.land = land;
        this.type = type;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }
}