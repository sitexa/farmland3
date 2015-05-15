package com.sitexa.sys.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.data.*;
import com.sitexa.user.data.User;
import com.sitexa.user.service.UserService;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-17
 * Time: 20:34:11
 */
@SuppressWarnings("deprecation")
public class RoleService {

    public static void main(String[] args) {
        User user = UserService.getUser("1000038");
        Role role = getRole("1");
        RoleInUser ru = getRoleInUser(user,role);
        System.out.println("ru = " + ru.getId().getRole());
    }

    public static Role getRole(String roleId) {
        RoleDAO dao = new RoleDAO();
        return dao.findById(roleId);
    }

    public static RoleInUser getRoleInUser(User user, Role role) {
        RoleInUserId riu = new RoleInUserId(user, role);
        return (new RoleInUserDAO()).findById(riu);

    }

    @SuppressWarnings("unchecked")
    public static List<Role> getUserRoles(User user) {
        List<Role> roles = new ArrayList<Role>();

        Session session = HibernateSessionFactory.getSession();
        Connection conn = session.connection();

        String sql = "select roleId from t_role_in_user where userId = ?";
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUserId());
            rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String roleId = rs.getString(1);
                    roles.add((new RoleDAO()).findById(roleId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }

        return roles;
    }
}
