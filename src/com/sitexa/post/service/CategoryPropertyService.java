package com.sitexa.post.service;

import com.sitexa.post.data.Category;
import com.sitexa.post.data.CategoryProperty;
import com.sitexa.post.data.CategoryPropertyDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 19:52:46
 */
public class CategoryPropertyService {
    private static Log log = LogFactory.getLog(CategoryPropertyService.class);

    public static CategoryProperty getCategoryProperty(String propId) {
        try {
            return (new CategoryPropertyDAO()).findById(propId);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<CategoryProperty> getCategoryProperties(Category category) {
        try {
            return (new CategoryPropertyDAO()).findByProperty("category", category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void save(CategoryProperty category) {
        try {
            (new CategoryPropertyDAO()).save(category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void update(CategoryProperty property) {
        try {
            (new CategoryPropertyDAO()).update(property);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void delete(CategoryProperty property) {
        try {
            (new CategoryPropertyDAO()).delete(property);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

}
