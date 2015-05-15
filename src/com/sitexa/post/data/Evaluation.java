package com.sitexa.post.data;


import java.util.Date;

/**
 * Evaluation entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Evaluation implements java.io.Serializable {

    // Fields

    private String evaId;
    private Scenery scenery;
    private String comment;
    private String score;
    private String userId;
    private Date addDate;

    // Constructors

    /**
     * default constructor
     */
    public Evaluation() {
    }

    /**
     * minimal constructor
     */
    public Evaluation(String evaId, Scenery scenery) {
        this.evaId = evaId;
        this.scenery = scenery;
    }

    public Evaluation(String evaId, Scenery scenery, String comment, String score, String userId, Date addDate) {
        this.evaId = evaId;
        this.scenery = scenery;
        this.comment = comment;
        this.score = score;
        this.userId = userId;
        this.addDate = addDate;
    }

    public String getEvaId() {
        return evaId;
    }

    public void setEvaId(String evaId) {
        this.evaId = evaId;
    }

    public Scenery getScenery() {
        return scenery;
    }

    public void setScenery(Scenery scenery) {
        this.scenery = scenery;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}