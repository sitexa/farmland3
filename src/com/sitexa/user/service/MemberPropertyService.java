package com.sitexa.user.service;

import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberProperty;
import com.sitexa.user.data.MemberPropertyDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-4-4
 * Time: 8:20:41
 */
public class MemberPropertyService {

    private static Log log = LogFactory.getLog(MemberPropertyService.class);

    public static MemberProperty getMemberProperty(String id) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        return dao.findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<MemberProperty> getMemberProperties() {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        return dao.findAll();
    }

    @SuppressWarnings("unchecked")
    public static List<MemberProperty> getMemberProperties(Member member) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        return dao.findByProperty("member", member);
    }

    public static void create(MemberProperty property) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.save(property);
    }

    public static void delete(MemberProperty property) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.delete(property);
    }

    public static void update(MemberProperty property) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.save(property);
    }

    public static void updateProperties(List<MemberProperty> properties) {
        for (MemberProperty property : properties) {
            if (null != property.getMember().getMemberId())
                property.setMember(MemberService.getMember(property.getMember().getMemberId()));
        }
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.updateProperties(properties);
    }

    /**
     * Reference to SitePropertyService for another implementation, I think there do not have to
     * create an independent session to access database.
     *
     * @param properties list of property
     */
    public static void createProperties(ArrayList<MemberProperty> properties) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.createProperties(properties);
    }

    public static void deleteProperty(String propId) {
        MemberPropertyDAO dao = new MemberPropertyDAO();
        dao.delete(dao.findById(propId));
    }

    public static void main(String[] args) {
        List<MemberProperty> properties = new ArrayList<MemberProperty>();
        properties.addAll(MemberService.getMember("1000038").getProperties());
        for (int i = 0; i < properties.size(); i++) {
            MemberProperty memberProperty = properties.get(i);
            memberProperty.setPublish(true);
            System.out.println("memberProperty = " + memberProperty);
        }
        updateProperties(properties);

    }
}
