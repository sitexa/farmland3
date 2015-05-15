package com.sitexa.post.data;

/**
 * Message entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

    // Fields

    private String id;
    private PostType msgType;
    private Post post;

    // Constructors

    /**
     * default constructor
     */
    public Message() {
    }

    /**
     * minimal constructor
     */
    public Message(String id, PostType msgType, Post post) {
        this.id = id;
        this.msgType = msgType;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PostType getMsgType() {
        return msgType;
    }

    public void setMsgType(PostType msgType) {
        this.msgType = msgType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}