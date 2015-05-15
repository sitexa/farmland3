package com.sitexa.sys.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.hibernate.Page;
import com.sitexa.sys.data.ViewStats;
import com.sitexa.sys.data.ViewStatsAddr;
import com.sitexa.sys.data.ViewStatsDAO;
import com.sitexa.sys.data.ViewStatsUri;
import com.sitexa.framework.util.Ip2City;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-6-24
 * Time: 17:26:37
 */
public class ViewStatsService {

    private static Long count = 0l;

    public static void visit(HttpServletRequest request) {
        try {
            String addr = request.getRemoteAddr();
            String uri = request.getRequestURI();
            String hdr = request.getHeader("X-Forwarded-For");
            System.out.println("hdr = " + hdr);
            Date date = new Date();

            ViewStats view = new ViewStats(addr, uri, date);
            ViewStatsDAO dao = new ViewStatsDAO();
            dao.save(view);
            count = view.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getVisitCity(HttpServletRequest request) {
        String cityname = null;
        try {
            String addr = request.getRemoteAddr();
            cityname = Ip2City.getCity(addr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityname;
    }

    @SuppressWarnings("unchecked")
    public static List<ViewStats> views(Page page) {
        List<ViewStats> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from ViewStats order by visitTime desc");

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

    public static int totalView() {
        return count.intValue();
    }

    @SuppressWarnings("unchecked")
    public static List<ViewStatsAddr> viewAddr(Page page) {
        List<ViewStatsAddr> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from ViewStatsAddr order by visitTime desc ");

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
    public static List<ViewStatsAddr> viewAddr() {
        List<ViewStatsAddr> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from ViewStatsAddr order by visitTime desc ");

            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<ViewStatsUri> viewUri(Page page) {
        List<ViewStatsUri> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from ViewStatsUri order by visitTime desc");

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
    public static List<ViewStatsUri> viewUri() {
        List<ViewStatsUri> list;
        try {
            Session session = HibernateSessionFactory.getSession();
            Query query = session.createQuery(" from ViewStatsUri order by visitTime desc");
            list = query.list();
        } catch (RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
