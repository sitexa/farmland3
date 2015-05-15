package com.sitexa.post.data;

import com.sitexa.farm.data.Farm;

/**
 * FarmPost entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class FarmPost implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private Farm farm;
    private PostType type;

    /**
     * default constructor
     */
    public FarmPost() {
    }

    public FarmPost(Post post, Farm farm,PostType type) {
        this.post = post;
        this.farm = farm;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }
}