package com.sitexa.user.service;

import com.sitexa.framework.exception.BaseException;
import com.sitexa.framework.exception.BaseRuntime;
import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.util.StringUtils;
import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteMember;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.service.SiteTypeService;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * User: xnpeng
 * Date: 2009-4-2
 * Time: 21:01:21
 */
public class UserService {
    private static Log log = LogFactory.getLog(UserService.class);
    private static String confirm_subject = "花木兰(farmlander.com)注册确认信";
    private static String activate_subject = "花木兰(farmlander.com)帐号激活信";
    private static String findpwd_subject = "找回花木兰(farmlander.com)密码信";
    private static String reg_email = "^\\w+[\\+\\.\\w-]*@([\\w-]+\\.)*\\w+[\\w-]*\\.([a-z]{2,4}|\\d+)";

    public static void main(String[] args) {
        String uid = "1000038";
        User u = getUser(uid);
        System.out.println("u.getUsername() = " + u.getUsername());
    }

    public static User getUser(String userId) {
        UserDAO dao = new UserDAO();
        return dao.findById(userId);
    }

    public static User getUserByUsername(String username) {
        UserDAO dao = new UserDAO();
        List list = dao.findByUsername(username);
        if (list.size() > 0) {
            return (User) list.get(0);
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        String e = email.replaceAll(reg_email, "");
        if (e.length() > 0) return null;
        UserDAO dao = new UserDAO();
        List list = dao.findByProperty("email", email);
        if (list.size() > 0)
            return (User) list.get(0);
        else
            return null;
    }

    public static List<User> getUserByRealname(String realname) {
        List<User> l = new ArrayList<User>();
        MemberDAO dao = new MemberDAO();
        List list = dao.findByRealname(realname);
        for (Object aList : list) {
            Member member = (Member) aList;
            l.add(member.getUser());
        }
        return l;
    }

    public static User login(User user) throws BaseException {
        //todo...用多种输和登录:1,username,2,realname;3,email;4,userid;
        return login(user.getUsername(), user.getPassword(), user.getLoginAddress());
    }

    public static boolean checkPassword(String userId, String password) {
        if (userId == null || "".equals(userId)) return false;
        try {
            User user = getUser(userId);
            return validateUser(user, password);
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    private static User login(String username, String password, String address) throws BaseException {
        try {
            if (username != null && password != null) {

                UserDAO dao = new UserDAO();
                //todo...用多种输和登录:1,username,2,realname;3,email;4,userid;
                List list = dao.findByUsername(username);
                if (list.size() > 0) {
                    User user = (User) list.get(0);

                    //if (!user.getStatus()) BaseException.threw("用户被禁用!");

                    if (user.getTryTimes() > 5) {
                        if (user.getTryDate() != null &&
                                ((new Date()).getTime() - user.getTryDate().getTime() < 30 * 60 * 1000)) {
                            BaseException.threw("由于尝试次数超过5次,用户被暂时锁定.请在半个小时之后再试,或者联系管理员.代码1005.");
                        }
                    }

//                    if (user.isEmailConfirmed() || inTryPeriod(user)) {
                    if (user.isEmailConfirmed()) {
//                    if (user.getValidDate() != null && user.getValidDate().getTime() < (new Date()).getTime()) {
//                        if (user.getExpiryDate() != null && user.getExpiryDate().getTime() > (new Date()).getTime()) {
                        if (validateUser(user, password)) {
                            user.setLastLoginDate(user.getLoginDate());
                            user.setLoginDate(new Date());
                            user.setLoginTimes(user.getLoginTimes() + 1);
                            user.setTryTimes(0);
                            user.setLastLoginAddress(user.getLoginAddress());
                            user.setLoginAddress(address);
                            dao.update(user);
                            return user;
                        } else {
                            user.setTryDate(new Date());
                            user.setTryTimes(user.getTryTimes() + 1);
                            user.setLoginAddress(address);
                            dao.update(user);
                            BaseException.threw("密码错误,请重试!尝试次数超过5次将锁定用户30分钟.代码1004.");
                        }
//                        } else {
//                           BaseException.threw("用户已经过期.请跟管理员联系.");
//                        }
//                    } else {
//                        BaseException.threw("用户还未生效.请跟管理员联系.");
//                    }

                    } else {
                        BaseException.threw("邮箱还未通过验证!必须验证邮箱才能激活用户帐号.\n" +
                                "请登录你的邮箱,收取验证信,并按险证方式进行验证.\n" +
                                "如果忘记所用的邮箱,请跟管理员联系.\n代码1003.");
                    }

                } else {
                    BaseException.threw("用户不存在!代码1002.");
                }

            } else {
                BaseException.threw("用户名或密码为空!代码1001.");
            }
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error(re);
            throw re;
        } catch (BaseException e) {
            log.error(e);
            throw e;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static List<User> getAllUser() {
        UserDAO dao = new UserDAO();
        return dao.findAll();
    }

    public static void create(User user, Site site) throws BaseException {
        if (user.getUserId() == null || user.getUserId().equals(""))
            user.setUserId(Sequence.getId());
        String pwd = user.getPassword();
        if (!checkUser(user)) {
            BaseException.threw("注册信息有误,请正确输入!");
        }
        if (!checkSite(site)) {
            BaseException.threw("没有选择社区，或者所选择社区不正确。只能选择街道，乡镇及更小的社区。");
        }

        user.setPassword(StringUtils.encrypt(pwd));
        user.setRegisterDate(new Date());
        //todo...用户默认为启用状态.如果违规,管理员有权禁用该用户.
        //todo...设计一个配置项,根据配置项决定用户的初始状态.
        user.setStatus(true);

        String code = UUID.randomUUID().toString();
        user.setConfirmCode(code);

        //todo...同时生成member.
        Member member = new Member();
        member.setUser(user);
        member.setNickname(user.getUsername());
        member.setRealname(user.getUsername());
        member.setGender("0");
        member.setUpdateDate(new Date());
        MemberType type = MemberService.getMemberType("1");
        member.setType(type);
        member.setSite(site);

        MemberService.checkLength(member);

        //todo...还要加入相应社区会员
        SiteMember siteMember2 = new SiteMember();
        siteMember2.setType(SiteMember.TYPE_INNER);
        siteMember2.setSite(site);
        siteMember2.setMember(member);
        siteMember2.setJoinDate(new Date());
        siteMember2.setRemark("注册用户自动加入!");
        siteMember2.setStatus(SiteMember.STATUS_ACT);
        siteMember2.setId(Sequence.getId());

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.save(user);
            session.save(member);
            session.save(siteMember2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            log.error(e.getMessage());
            BaseException.threw("注册用户发生错误。");
        }

        sendConfirmEmail(user, code);

    }

    public static void create(User user) throws BaseException {
        if (user.getUserId() == null || user.getUserId().equals(""))
            user.setUserId(Sequence.getId());
        String pwd = user.getPassword();
        if (!checkUser(user)) {
            BaseException.threw("注册信息有误,请正确输入!");
        }
        user.setPassword(StringUtils.encrypt(pwd));
        user.setRegisterDate(new Date());
        //todo...用户默认为启用状态.如果违规,管理员有权禁用该用户.
        //todo...设计一个配置项,根据配置项决定用户的初始状态.
        user.setStatus(true);

        String code = UUID.randomUUID().toString();
        user.setConfirmCode(code);
        user.setConfirmCode(code);

        UserDAO dao = new UserDAO();
        dao.save(user);

        //todo...同时生成member.
        Member member = new Member();
        member.setUser(user);
        member.setNickname(user.getUsername());
        member.setRealname(user.getUsername());
        member.setGender("0");
        member.setUpdateDate(new Date());
        MemberType type = MemberService.getMemberType("1");
        member.setType(type);
        MemberService.create(member);

        sendConfirmEmail(user, code);
    }

    //todo....ban some host.rules.
    //todo...设计一张表,维护禁用的IP地址

    private static boolean isBannedHost(String host) {
        System.out.println("host = " + host);
        return false;
    }

    private static boolean checkSite(Site site) {
        if (site == null || site.getType() == null || site.getType().getTypeId() == null) return false;
        SiteType type = SiteTypeService.getSiteType(site.getType().getTypeId());
        return ("5".equals(type.getTypeId())
                || "6".equals(type.getTypeId())
                || "9".equals(type.getTypeId()));
    }

    private static boolean checkUser(User user) {
        String username = user.getUsername();
        String pwd = user.getPassword();
        String email = user.getEmail();
        String host = user.getLoginAddress();
        String msg = "";
        try {
            User u = getUserByUsername(username);
            User u2 = getUserByEmail(email);
            if (u != null) msg += "用户名无效.";
            if (pwd.length() < 6) msg += "密码位数太少.";
            if (!isValidEmail(email)) msg += "Email无效.";
            if (u2 != null) msg += "email已经注册.";
            if (isBannedHost(host)) msg += "IP地址无效.";
            if (msg.length() > 0) throw new RuntimeException(msg);
            return true;
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    private static boolean isValidEmail(String email) {
        return email.replaceAll(reg_email, "").length() <= 0;
    }

    private static boolean inTryPeriod(User user) {
        Date regDate = user.getRegisterDate();
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(regDate);
        cal.roll(Calendar.DATE, 7);
        return cal.getTime().after(now);
    }

    private static void sendConfirmEmail(User user, String code) {
        System.out.println("UserService.sendConfirmEmail");
        String mail_body = "<html><body>";
        mail_body += user.getUsername() + ":";
        mail_body += "您好!<br>您收到这封信是因为您注册了<a href='www.farmlander.com'>www.farmlander.com</a>用户.<br>";
        mail_body += "或者修改了用户的注册邮箱.这是发送给您的注册确认邮件.<br>";
        mail_body += "如果您没有在farmlander.com注册用户,那么可能是别人使用了您的邮箱.请您删除这封邮件.<br>";
        mail_body += "<br>如果这是您在注册farmlander.com用户时使用的邮箱,请您点击下面的链接,<br>";
        mail_body += "或者把链接地址copy到浏览器地址栏里,访问指定页面.<br><br>";
        mail_body += "如果不能直接点击链接,请您把链接地址copy到浏览器地址栏里访问.<br>";
        mail_body += "如果您没有在一周内确认,您的帐号将被锁定.<br>";
        mail_body += "<a href='http://www.farmlander.com/logon!confirm?userId=" + user.getUserId() + "&confirmcode=" + code;
        mail_body += "'>http://www.farmlander.com/logon!confirm?userId=" + user.getUserId() + "&confirmcode=" + code + "</a><br>";
        mail_body += "<br><br>请不要直接回复这封邮件.<br><br>";
        mail_body += "farmlander.com";

        EmailThread et = new EmailThread();

        et.setMail_body(mail_body);
        et.setMail_subject(confirm_subject);
        et.setMail_to(user.getEmail());
        et.start();
    }

    //todo....删除用户.

    public static void delete(User user) {
        UserDAO dao = new UserDAO();
        dao.delete(user);
    }

    //todo...如果用户email发生改变,需要重新确认才能生效.

    public static void update(User user) {
        System.out.println("UserService.update");

        UserDAO dao = new UserDAO();

        User user2 = dao.findById(user.getUserId());
        String email = user.getEmail();
        String email2 = user2.getEmail();

        user2.setUsername(user.getUsername());
        user2.setEmail(user.getEmail());
        user2.setEmailConfirmed(user.isEmailConfirmed());
        user2.setStatus(user.getStatus());
        user2.setValidDate(user.getValidDate());
        user2.setExpiryDate(user.getExpiryDate());
        user2.setTryTimes(user.getTryTimes());

        dao.update(user2);
        if (!email.equalsIgnoreCase(email2)) {
            String code = UUID.randomUUID().toString();
            user2.setConfirmCode(code);
            sendConfirmEmail(user2, code);
        }
        //todo...用户修改后要通知用户确认;管理员修改后,通知用户帐户管理结果.
    }

    public static void updateEmail(User user, String newMail) {
        System.out.println("UserService.updateEmail");
        UserDAO dao = new UserDAO();
        User ou = getUser(user.getUserId());
        String email = ou.getEmail();
        if (!email.equalsIgnoreCase(newMail)) {
            String code = UUID.randomUUID().toString();
            user.setEmail(newMail);
            user.setConfirmCode(code);
            dao.update(user);
            sendConfirmEmail(user, code);
        }
    }

    public static void changePassword(User user, String oldPassword, String newPassword) {
        System.out.println("UserService.changePassword");
        UserDAO dao = new UserDAO();
        user = dao.findById(user.getUserId());
        if (!StringUtils.encrypt(oldPassword).equals(user.getPassword())) {
            BaseRuntime.threw(UserService.class, "旧密码不正确!");
        }
        user.setPassword(StringUtils.encrypt(newPassword));
        dao.update(user);
    }

    public static boolean validateUser(User user, String password) {
        return (StringUtils.encrypt(password).equals(user.getPassword()));
    }

    public static String rewind(User user, Member member) throws BaseException {
        System.out.println("UserService.rewind");
        User u = null, u2 = null;
        List<User> us = new ArrayList<User>();

        String newPassword = null;
        if (user.getEmail() != null) {
            String m = user.getEmail();
            if (!isValidEmail(m)) {
                BaseException.threw("email输入错误");
            } else {
                u = getUserByEmail(m);
            }
        }
        if (user.getUsername() != null) {
            u2 = getUserByUsername(user.getUsername());
        }
        if (member.getRealname() != null) {
            us = getUserByRealname(member.getRealname());
        }

        if (u != null) newPassword = setPassword(u);

        if (u != null && u2 != null && u.getUserId().equals(u2.getUserId())) {
            for (User user1 : us) {
                if (user1.getUserId().equals(u.getUserId())) {
                    sendPwdByEmail(newPassword, u);
                    return newPassword;
                }
            }
        } else if (u != null) {
            sendPwdByEmail(newPassword, u);
            return "1";
        }
        return "0";
    }

    private static void sendPwdByEmail(String password, User user) {
        String msg = user.getUsername() + ":你好!<br>";
        msg += "您的<a href='www.farmlander.com'>www.farmlander.com</a>登录密码已经重新设定为:" + password + "<br>";
        msg += "请保管好密码,以防泄密.<br>";
        EmailThread et = new EmailThread();
        et.setMail_body(msg);
        et.setMail_subject(findpwd_subject);
        et.setMail_to(user.getEmail());
        System.out.println("not implemented");
    }

    private static String setPassword(User user) throws BaseException {
        if (user == null) {
            BaseException.threw("用户为空.");
        } else {
            //test user:aa will never change password.
            String pwd = UUID.randomUUID().toString().substring(0, 6);

            UserDAO dao = new UserDAO();
            user = dao.findById(user.getUserId());
            user.setPassword(StringUtils.encrypt(pwd));

            dao.update(user);
            return pwd;
        }
        return null;
    }

    public static boolean confirmEmail(String userId, String code) {
        UserDAO dao = new UserDAO();
        User user = dao.findById(userId);
        if (code.equalsIgnoreCase(user.getConfirmCode())) {
            user.setEmailConfirmed(true);
            dao.update(user);
            return true;
        } else {
            return false;
        }
    }

    public static void sendActivateEmail(User user) {
        System.out.println("UserService.activate");
        try {
            String email = user.getEmail();
            if (!isValidEmail(email)) {
                BaseException.threw("email地址格式错误，请重新输入");
            }
            UserDAO dao = new UserDAO();
            String code = UUID.randomUUID().toString();
            user = dao.findById(user.getUserId());
            user.setEmail(email);
            user.setConfirmCode(code);
            dao.update(user);

            String mail_body = "<html><body>";
            mail_body += user.getUsername() + ":";
            mail_body += "您好!<br>您收到这封信是因为您激活了<a href='www.farmlander.com'>www.farmlander.com</a>用户.<br>";
            mail_body += "这是发送给您的激活邮件.<br>";
            mail_body += "如果您没有在farmlander.com注册用户,那么可能是别人使用了您的邮箱.请您删除这封邮件.<br>";
            mail_body += "<br>如果这是您在注册farmlander.com用户时使用的邮箱,请您点击下面的链接激活帐号,<br>";
            mail_body += "或者把链接地址copy到浏览器地址栏里,访问指定页面.<br><br>";
            mail_body += "如果不能直接点击链接,请您把链接地址copy到浏览器地址栏里访问.<br>";
            mail_body += "<a href='http://www.farmlander.com/logon!activate?userId=" + user.getUserId() + "&code=" + code;
            mail_body += "'>http://www.farmlander.com/logon!activate?userId=" + user.getUserId() + "&code=" + code + "</a><br>";
            mail_body += "<br><br>请不要直接回复这封邮件.<br><br>";
            mail_body += "farmlander.com";

            EmailThread et = new EmailThread();

            et.setMail_body(mail_body);
            et.setMail_subject(activate_subject);
            et.setMail_to(user.getEmail());
            et.start();
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    public static User activate(String userId, String code) {
        UserDAO dao = new UserDAO();
        User user = dao.findById(userId);
        if (user != null) {
            if (user.getConfirmCode().equals(code)) {
                user.setStatus(true);
                user.setEmailConfirmed(true);
                dao.update(user);
            }
        }
        return user;
    }
}

class EmailThread extends Thread {

    private static final String host = "smtp.gmail.com";
    private static final int port = 465;
    private static final String username = "motional.net";
    private static final String password = "tswcbyy8";

    private String mail_from = "admin@sitexa.com";
    private String mail_to = "xnpeng@163.com";

    private String mail_subject = "花木兰(farmlander.com)注册确认信";
    private String mail_body = "";

    public String getMail_from() {
        return mail_from;
    }

    public void setMail_from(String mail_from) {
        this.mail_from = mail_from;
    }

    public String getMail_to() {
        return mail_to;
    }

    public void setMail_to(String mail_to) {
        this.mail_to = mail_to;
    }

    public String getMail_subject() {
        return mail_subject;
    }

    public void setMail_subject(String mail_subject) {
        this.mail_subject = mail_subject;
    }

    public String getMail_body() {
        return mail_body;
    }

    public void setMail_body(String mail_body) {
        this.mail_body = mail_body;
    }

    EmailThread() {
    }

    public void run() {
        sendMail();
    }

    private void sendMail() {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setSSL(true);
            email.setAuthentication(username, password);
            email.setFrom(mail_from);
            email.addTo(mail_to);
            email.setCharset("UTF-8");
            email.setSubject(mail_subject);
            email.setHtmlMsg(mail_body);
            email.setMsg(mail_body);
            email.buildMimeMessage();
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}