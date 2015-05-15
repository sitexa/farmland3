package com.sitexa.farm.service;

import com.sitexa.farm.data.FactionDAO;
import com.sitexa.farm.data.Service;
import com.sitexa.farm.data.ServiceDAO;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-4-22
 * Time: 10:50:59
 */
public class ServiceService {
    private static Log log = LogFactory.getLog(ServiceService.class);

    public static Service getById(String id) {
        ServiceDAO dao = new ServiceDAO();
        return dao.findById(id);
    }

    @SuppressWarnings("unchecked")
    public static List<Service> getServices() {
        ServiceDAO dao = new ServiceDAO();
        return dao.findAll();
    }

    public static void create(Service svc) {
        if (svc == null) return;
        svc.setSvcId(Sequence.getId());
        ServiceDAO dao = new ServiceDAO();
        dao.save(svc);
    }

    public static void update(Service svc) {
        if (svc == null) return;
        ServiceDAO dao = new ServiceDAO();
        Service service = dao.findById(svc.getSvcId());
        if (svc.getImg() != null)
            service.setImg(svc.getImg());
        service.setContents(svc.getContents());
        service.setExpense(svc.getExpense());
        service.setMaterials(svc.getMaterials());
        service.setSvcName(svc.getSvcName());
        dao.update(service);
    }

    public static void update(List<Service> svcs) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            for (int i = 0; i < svcs.size(); i++) {
                Service svc = svcs.get(i);
                Service s = (Service) session.get(Service.class, svc.getSvcId());

                s.setSvcName(svc.getSvcName());
                s.setContents(svc.getContents());
                s.setExpense(svc.getExpense());
                s.setMaterials(svc.getMaterials());

                //todo...

                session.saveOrUpdate(s);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public static void delete(String id) {
        if (id == null || "".equals(id)) return;
        FactionDAO da = new FactionDAO();
        List list = da.findByProperty("factionId", id);
        if (list.size() > 0) return;
        ServiceDAO dao = new ServiceDAO();
        Service svc = dao.findById(id);
        dao.delete(svc);
    }

}
