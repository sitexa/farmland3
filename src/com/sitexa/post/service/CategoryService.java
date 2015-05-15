package com.sitexa.post.service;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.CategoryDAO;
import com.sitexa.site.data.Site;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-8
 * Time: 19:52:25
 */
public class CategoryService {
    private static Log log = LogFactory.getLog(CategoryService.class);

    public static Category getCategory(String cateId) {
        if (cateId == null) return null;
        return (new CategoryDAO()).findById(cateId);
    }

    public static void create(Category category) {
        System.out.println("CategoryService.create");
        if (category.getCateId() == null || category.getCateId().equals("")) {
            category.setCateId(Sequence.getId());
        }
        if (category.getParent().getCateId() == null ||
                category.getParent().getCateId().equals("")) {
            category.setParent(null);
        } else {
            Category p = getCategory(category.getParent().getCateId());
            category.setParent(p);
        }
        try {
            if (category.getName().length() > 100) {
                category.setName(category.getName().substring(0, 100));
            }
            if (category.getDescription().length() > 50) {
                category.setDescription(category.getDescription().substring(0, 50));
            }
            (new CategoryDAO()).save(category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void update(Category category) {
        System.out.println("CategoryService.update");
        if (category.getParent() == null
                || category.getParent().getCateId() == null
                || category.getParent().getCateId().equals("")) {
            category.setParent(null);
        }
        try {
            (new CategoryDAO()).update(category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void update(List<Category> categories) {
        System.out.println("CategoryService.update");
        CategoryDAO dao = new CategoryDAO();
        try {
            dao.update(categories);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void delete(Category category) {
        try {
            (new CategoryDAO()).delete(category);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Category> getAllCategory() {
        try {
            return (new CategoryDAO()).findAll();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Category> getCategoryInUse() {
        return (new CategoryDAO()).findAllInUse();
    }

    @SuppressWarnings("unchecked")
    public static List<Category> getCategory2(String type) {
        CategoryDAO dao = new CategoryDAO();
        try {
            return dao.findByProperty("type", type);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    public static void main(String[] args) throws BaseException {

    }

    @SuppressWarnings("unchecked")
    public static List<Category> getCategoryByCommon() {
        CategoryDAO dao = new CategoryDAO();
        try {
            return dao.findAllforCommon();
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Category> getCategoryByUser(User user) {
        CategoryDAO dao = new CategoryDAO();
        try {
            return dao.findByUser(user);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Category> getCategoryBySite(Site site) {
        CategoryDAO dao = new CategoryDAO();
        try {
            return dao.findBySite(site);
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }
    }
}
