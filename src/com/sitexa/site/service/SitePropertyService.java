package com.sitexa.site.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteProperty;
import com.sitexa.site.data.SitePropertyDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-26
 * Time: 13:03:19
 */
public class SitePropertyService {

    private static Log log = LogFactory.getLog(SitePropertyService.class);

    public static void main(String[] args) {
        String s = "告别虚拟,回归真实,回到社的回到社的家!";
        System.out.println("s.length() = " + s.length());
    }

    @SuppressWarnings("unchecked")
    public static List<SiteProperty> getProperties(Site site) {
        SitePropertyDAO dao = new SitePropertyDAO();
        return dao.findByProperty("site", site);
    }

    public static String getProperty(Site site, String propName) {
        String propValue = null;
        try {
            SitePropertyDAO dao = new SitePropertyDAO();
            propValue = dao.getProperty(site, propName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return propValue;
    }

    public static String getSlogan(Site site) {
        return getProperty(site, SiteProperty.SITE_SLOGAN);
    }

    public static void updateProperties(List<SiteProperty> properties) {
        SitePropertyDAO dao = new SitePropertyDAO();
        dao.saveOrUpdate(properties);
    }

    public static void createProperties(ArrayList<SiteProperty> properties) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            for (int i = 0; i < properties.size(); i++) {
                SiteProperty property = properties.get(i);
                if (property.getPropName().length() > 50)
                    property.setPropName(property.getPropName().substring(0, 50));
                if (property.getPropValue().length() > 250)
                    property.setPropValue(property.getPropValue().substring(0, 250));
                session.save(property);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            log.error(e);
            throw e;
        }
    }

    public static void deleteProperty(String propId) {
        SitePropertyDAO dao = new SitePropertyDAO();
        dao.delete(propId);

    }
}
