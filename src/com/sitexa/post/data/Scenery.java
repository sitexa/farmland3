package com.sitexa.post.data;

import java.util.HashSet;
import java.util.Set;

/**
 * Scenery entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Scenery implements java.io.Serializable {

    // Fields

    private String id;
    private Post post;
    private String address;
    private String charge;
    private String level;
    private Set evaluations = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Scenery() {
    }

    /**
     * minimal constructor
     */
    public Scenery(String id, Post post) {
        this.id = id;
        this.post = post;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(Set evaluations) {
        this.evaluations = evaluations;
	}

}