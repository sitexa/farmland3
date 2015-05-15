package com.sitexa.post.service;

import com.sitexa.post.data.Filter;
import com.sitexa.post.data.FilterDAO;
import com.sitexa.post.data.Post;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-28
 * Time: 11:42:27
 */
public class FilterService {
    private static Log log = LogFactory.getLog(FilterService.class);

    public static Filter getFilter(int id) {
        return (new FilterDAO()).findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<Filter> getAll() {
        return (new FilterDAO()).findAll();
    }

    public static void create(Filter filter) {
        FilterDAO dao = new FilterDAO();
        dao.save(filter);
    }

    public static void update(Filter filter) {
        FilterDAO dao = new FilterDAO();
        Filter f = getFilter(filter.getId());
        f.setWords(filter.getWords());
        f.setStatus(filter.getStatus());
        dao.update(f);
    }

    public static void update(List<Filter> filters) {
        FilterDAO dao = new FilterDAO();
        for (Filter filter : filters) {
            Filter f = getFilter(filter.getId());
            f.setWords(filter.getWords());
            f.setStatus(filter.getStatus());
            dao.update(f);
            dao.update(f);
        }
    }

    public static void filter(Post post) {
        System.out.println("FilterService.filter");
        String regex = getRegex();
        String name = post.getName();
        String subject = post.getSubject();
        String content = post.getContent();
        name = name.replaceAll(regex, "*");
        subject = subject.replaceAll(regex, "*");
        content = content.replaceAll(regex, "*");
        post.setName(name);
        post.setSubject(subject);
        post.setContent(content);
    }

    private static String getRegex() {
        String regex = "";
        FilterDAO dao = new FilterDAO();
        List list = dao.findKeywords();
        for (Object aList : list) {
            Filter filter = (Filter) aList;
            regex += "|(" + filter.getWords() + ")";
        }
        regex = regex.substring(1, regex.length());
        return regex;
    }

    public static void main(String[] args) {
        String s = getRegex();
        System.out.println("s = " + s);
    }
}
