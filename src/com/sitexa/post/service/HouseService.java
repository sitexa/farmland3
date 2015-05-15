package com.sitexa.post.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.post.data.House;
import com.sitexa.post.data.HouseDAO;
import com.sitexa.post.data.Post;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2009-6-20
 * Time: 10:24:38
 */
public class HouseService extends PostService {
    private static Log log = LogFactory.getLog(HouseService.class);

    public void create(Object obj) {
        System.out.println("HouseService.create");
        House house = (House) obj;
        Post post = house.getPost();
        try {
            String id = Sequence.newId("post");

            post.setId(id);
            house.setId(id);
            post.setCreateDate(new Date());

            FilterService.filter(post);

            Session session = HibernateSessionFactory.getSession();
            Transaction tx = session.beginTransaction();
            try {
                tx.begin();
                session.save(post);
                session.save(house);
                tx.commit();
            } catch (RuntimeException re) {
                tx.rollback();
                log.error(re);
                throw re;
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        }

    }

    public static void update(Object obj) {
        House house = (House) obj;
        Post post = house.getPost();

        FilterService.filter(post);

        Post post2 = PostService.getPost(post.getId());
        post2.setName(post.getName());
        post2.setSubject(post.getSubject());
        post2.setContent(post.getContent());

        House house2 = getHouse(house.getId());

        house2.setBuilding(house.getBuilding());
        house2.setRoom(house.getRoom());
        house2.setArea(house.getArea());
        house2.setPrice(house.getPrice());
        house2.setPost(post2);

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.update(post2);
            session.update(house2);
            tx.commit();
            session.close();
        } catch (RuntimeException re) {
            tx.rollback();
            log.error(re);
            throw re;
        }
    }

    public static House getHouse(String id) {
        HouseDAO dao = new HouseDAO();
        return dao.findById(id);
    }
}
