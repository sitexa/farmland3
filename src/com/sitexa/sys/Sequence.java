package com.sitexa.sys;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-23
 * Time: 13:48:57
 */
@SuppressWarnings("deprecation")
public class Sequence {

    public static void main(String[] args) throws SQLException {
        String id = newId("user");
        System.out.println("id = " + id);
    }

    public static String getId() {
        return newSeqn("") + "";
    }

    public static String newId(String name) {
        return newSeqn(name) + "";
    }

    public static int newSeqn(String name) {
        String sp_name;
        if (name == null || name.equals("")) {
            sp_name = "SP_GetSeqn";
        } else if (name.equalsIgnoreCase("post")) {
            sp_name = "SP_PostSeqn";
        } else if (name.equalsIgnoreCase("user")) {
            sp_name = "SP_UserSeqn";
        } else {
            sp_name = "SP_GetSeqn";
        }

        int seqn = -1;
        Session session = null;
        Transaction tx = null;
        Connection conn = null;
        try {
            session = HibernateSessionFactory.getIndependentSession();
            tx = session.beginTransaction();
            conn = session.connection();
            CallableStatement stmt = conn.prepareCall(" exec " + sp_name + " ?");
            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            stmt.execute();
            seqn = stmt.getInt(1);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (session != null) {
                HibernateSessionFactory.closeSession(session);
            }
        }
        return seqn;
    }

}
