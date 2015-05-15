package com.sitexa.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.post.data.Category;
import com.sitexa.post.data.Post;
import com.sitexa.site.data.Site;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-12
 * Time: 18:31:57
 */
public class SearchService {

    @SuppressWarnings("unchecked")
    public static List<Post> search(Page page, String words, Site site, Category category) {

        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }

        String hql = "from Post where 1=1 ";
        if (site != null) hql += " and site=?";
        if (category != null) hql += " and category=?";
        for (int i = 0; i < wordList.size(); i++) {
            String w = wordList.get(i);
            if (i == 0) {
                hql += " and (content like '%" + w + "%'";
            } else {
                hql += " or content like '%" + w + "%'";
            }
        }
        if (wordList.size() > 0)
            hql += ")";
        hql += " order by createDate desc";

        Session session = HibernateSessionFactory.getSession();
        try {
            Query query = session.createQuery(hql);

            if (site != null && category != null) {
                query.setParameter(0, site).setParameter(1, category);
            } else if (site != null) {
                query.setParameter(0, site);
            } else if (category != null) {
                query.setParameter(0, category);
            }

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            return query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Post> search(Page page) {
        System.out.println("SearchService.search");
        List<Post> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from Post where  parent is null order by createDate desc");

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());
            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<Post> search(String words, Page page) {
        System.out.println("SearchService.search");
        List<Post> list;
        String[] strArr = words.split("[,+;+.+。+]|(\\s)+|(\\p{Punct})+");
        List<String> wordList = new ArrayList<String>();
        for (String s : strArr) {
            if (s != null && !s.equals(""))
                wordList.add(s);
        }
        try {
            Session sess = HibernateSessionFactory.getSession();

            String hql = " from Post ";
            for (int i = 0; i < wordList.size(); i++) {
                String w = wordList.get(i);
                if (i == 0) {
                    hql += " where content like '%" + w + "%'";
                } else {
                    hql += " or content like '%" + w + "%'";
                }
            }
            hql += "order by createDate desc";

            Query query = sess.createQuery(hql);

            int t = query.list().size();
            int tot = t / page.getPageSize() + ((t % page.getPageSize()) == 0 ? 0 : 1);
            page.setTotal(tot);

            query.setFirstResult((page.getCurrent() - 1) * (page.getPageSize()));
            query.setMaxResults(page.getPageSize());

            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<Post> search(String words, int num) {
        List<Post> list = new ArrayList<Post>();
        String sql = " from Post where name like '%" + words + "%' order by createDate desc ";

        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(sql).setMaxResults(num);
            list = query.list();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
