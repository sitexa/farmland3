package com.sitexa.rest;

/**
 * User: Administrator
 * Date: 2010-11-1
 * Time: 11:45:40
 */
public class NewsObject {
    private String id;
    private String title;
    private String createDate;
    private String content;
    private String author;
    private String url;
    private String type;

    public NewsObject() {
    }

    public NewsObject(String id, String title, String createDate, String content,
                      String author, String url, String type) {
        this.id = id;
        this.title = title;
        this.createDate = createDate;
        this.content = content;
        this.author = author;
        this.url = url;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
