package com.sitexa.post.service;

import com.sitexa.post.data.PostType;
import com.sitexa.post.data.PostTypeDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-1-8
 * Time: 20:35:23
 */
public class PostTypeService {
    public static void main(String[] args) {
        List postTypes = PostTypeService.getObjectTypes("farmpost");
        System.out.println("t.toString() = " + postTypes.size());
    }


    public static PostType getById(String typeId) {
        if (typeId == null) return null;
        PostTypeDAO dao = new PostTypeDAO();
        return dao.findById(typeId);
    }

    @SuppressWarnings("unchecked")
    public static List<PostType> getObjectTypes(String objectId) {
        List<PostType> result = new ArrayList<PostType>();
        if (objectId == null) return result;
        PostTypeDAO dao = new PostTypeDAO();
        return dao.findByObjectId(objectId);
    }

    public static PostType getObjectType(String objectId, String name) {
        PostTypeDAO dao = new PostTypeDAO();
        return dao.findByObjectAndName(objectId, name);
    }

}







