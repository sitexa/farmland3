package com.sitexa.post.action;

import com.sitexa.framework.Constants;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.post.data.PostPicture;
import com.sitexa.post.data.PostType;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 9:10:58
 */
public class PostAction extends BaseAction {

    protected static final String regEx_wh = "(height|HEIGHT)\\b[^>]*|(WIDTH|width)\\b[^>]*";
    protected static final String regEx_div = "<(DIV|div)\\b[^>]*>|</(DIV|div)>";
    protected static final String regEx_tbody = "<(tbody|TBODY)\\b[^>]*>|</(tbody|TBODY)>";
    protected static final String regEx_table = "<(TABLE|table)\\b[^>]*>|</(TABLE|table)>";
    protected static final String regEx_trtd = "<(TR|tr)\\b[^>]*>|</(TR|tr)>|<(TD|td)\\b[^>]*>|</(TD|td)>|<(TH|th)\\b[^>]*>|</(TH|th)>";
    protected static final String regEx_trn = "[\\t\\r\\n]";
    protected static final String regEx_space = "(&nbsp;)*";
    protected static final String regEx_v32 = "";
    protected static final String regEx_link = "<([A|a][ ]*href)[^>]*>|</([A|a])[^>]*>";
    protected static final String regEx_html = "<(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))\\b[^>]*>|</(([A-Z][A-Z0-9]*)|([a-z][a-z0-9]*))>";
    protected static final String regEx_subject = regEx_html + "|" + regEx_trn + "|" + regEx_space;
    protected static final String regEx_content = regEx_link + "|" + regEx_trn + "|" + regEx_space;

    protected String words = "";
    protected Page page = new Page(15);

    protected String id;
    protected Post post;
    protected List<Post> posts = new ArrayList<Post>();

    protected Member creator;

    protected Site site;
    protected Site city;

    protected Category category;
    protected List<Category> categories = new ArrayList<Category>();
    protected PostType postType;
    protected List<PostType> postTypes = new ArrayList<PostType>();

    protected Post parent;
    protected List<Post> children = new ArrayList<Post>();

//    protected String typeId;
//    protected String styleId;
    protected String comment;

    protected List<File> upload = new ArrayList<File>();
    protected List<String> uploadFileName = new ArrayList<String>();
    protected List<String> uploadContentType = new ArrayList<String>();

    protected List<PostPicture> pictures = new ArrayList<PostPicture>();

    protected boolean haveRight = false;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getId() {
        return id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Member getCreator() {
        return creator;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    public List<Post> getChildren() {
        return children;
    }

    public void setChildren(List<Post> children) {
        this.children = children;
    }

/*
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }
*/

    public List<File> getUpload() {
        return upload;
    }

    public void setUpload(List<File> upload) {
        this.upload = upload;
    }

    public List<String> getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(List<String> uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public List<String> getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(List<String> uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public List<PostPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<PostPicture> pictures) {
        this.pictures = pictures;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public boolean getHaveRight() {
        return haveRight;
    }

    public List<PostType> getPostTypes() {
        return postTypes;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
