package com.sitexa.user.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.framework.util.StringUtils;
import com.sitexa.user.data.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * User: xnpeng
 * Date: 2010-1-25
 * Time: 15:44:01
 */
public class MemberCreditBean {
    private static Log log = LogFactory.getLog(MemberCreditBean.class);

    public final String OBJECT_NULL_ERROR = "10";
    public final String DAO_ERROR = "11";
    public final String CREDIT_PASSWORD_ERROR = "12";
    public final String NO_ENOUGH_CREDIT_ERROR = "13";
    public final String TRANSACTION_ERROR = "14";

    private Member member;
    private MemberCredit credit;

    private String error_string = "";
    private String succes_string = "";

    public String getErrorInfo() {
        return error_string;
    }

    public String getSuccesInfo() {
        return succes_string;
    }

    public MemberCreditBean(Member member) {
        this.member = member;
        init();
    }

    private void init() {
        if (member == null || member.getMemberId() == null || "".equals(member.getMemberId())) {
            throw new CreditException(OBJECT_NULL_ERROR);
        }
        try {
            MemberDAO dao = new MemberDAO();
            member = dao.findById(member.getMemberId());
        } catch (Exception e) {
            error_string += "数据装载错误";
            throw new CreditException(DAO_ERROR);
        }
        try {
            MemberCreditDAO dao2 = new MemberCreditDAO();
            credit = dao2.findById(member.getMemberId());
            if (credit == null) {
                credit = new MemberCredit();
                credit.setMember(member);
                credit.setMemberId(member.getMemberId());
                credit.setPassword(member.getUser().getPassword());
                credit.setCredit(0);
                dao2.save(credit);
            }
        } catch (Exception e) {
            error_string += "数据装载错误";
            throw new CreditException(DAO_ERROR);
        }

    }

    public boolean checkPassword(String pwd) {
        try {
            String p = member.getUser().getPassword();
            String p2 = StringUtils.encrypt(pwd);
            if (!p.equals(p2)) {
                error_string += "支付密码错误##";
                throw new CreditException(CREDIT_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            throw new CreditException(e.getMessage());
        }
        return true;
    }

    /**
     * 通过密码验证支付。
     * @param seller;
     * @param point;
     * @param pwd;
     * @param reason;
     */
    public void pay(Member seller, int point, String pwd, String reason) {
        System.out.println("MemberCreditBean.pay");
        System.out.println("reason = " + reason);

        try {
            String p = member.getUser().getPassword();
            String p2 = StringUtils.encrypt(pwd);
            if (!p.equals(p2)) {
                error_string += "支付密码错误##";
                throw new CreditException(CREDIT_PASSWORD_ERROR);
            }
        } catch (Exception e) {
            throw new CreditException(e.getMessage());
        }
        //todo...
/*
        if (credit.getCredit() < point) {
            error_string += "点卡余额不足##";
            throw new CreditException(NO_ENOUGH_CREDIT_ERROR);
        }
*/

        try {
            MemberCreditBean credit2 = new MemberCreditBean(seller);
            if (credit2 == null) {
                error_string += "收款方点卡错误##";
                throw new CreditException(OBJECT_NULL_ERROR);
            }
        } catch (Exception e) {
            error_string += "收款方点卡错误##";
            throw new CreditException(OBJECT_NULL_ERROR);
        }

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            MemberCredit mc;
            MemberCredit mc2;

            mc = (MemberCredit) session.get(MemberCredit.class, credit.getMemberId());
            mc2 = (MemberCredit) session.get(MemberCredit.class, seller.getMemberId());
            mc.setCredit(mc.getCredit() - point);
            mc2.setCredit(mc2.getCredit() + point);
            session.save(mc);
            session.save(mc2);

            MemberCreditLog log = new MemberCreditLog();
            log.setMember(member);
            log.setMember2(seller);
            log.setAmount(-point);
            log.setContents("pay:" + reason);
            log.setEventDate(new Date());
            log.setIncDec(0);

            MemberCreditLog log2 = new MemberCreditLog();
            log2.setMember(seller);
            log2.setMember2(member);
            log2.setAmount(point);
            log2.setContents("receive:" + reason);
            log2.setEventDate(new Date());
            log2.setIncDec(1);

            session.save(log);
            session.save(log2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CreditException(TRANSACTION_ERROR);
        }
        System.out.println("MemberCreditBean.pay end");
    }

    /**
     * 无密码付款,允许透支.
     * @param seller;
     * @param point;
     * @param reason;
     */
    public void pay(Member seller, int point, String reason) {
        System.out.println("MemberCreditBean.pay");
        System.out.println("reason = " + reason);
/*
        //
        if (credit.getCredit() < point) {
            error_string += "点卡余额不足##";
            throw new CreditException(NO_ENOUGH_CREDIT_ERROR);
        }
*/

        try {
            MemberCreditBean credit2 = new MemberCreditBean(seller);
            if (credit2 == null) {
                error_string += "收款方点卡错误##";
                throw new CreditException(OBJECT_NULL_ERROR);
            }
        } catch (Exception e) {
            error_string += "收款方点卡错误##";
            throw new CreditException(OBJECT_NULL_ERROR);
        }

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            MemberCredit mc;
            MemberCredit mc2;

            mc = (MemberCredit) session.get(MemberCredit.class, credit.getMemberId());
            mc2 = (MemberCredit) session.get(MemberCredit.class, seller.getMemberId());
            mc.setCredit(mc.getCredit() - point);
            mc2.setCredit(mc2.getCredit() + point);
            session.save(mc);
            session.save(mc2);

            MemberCreditLog log = new MemberCreditLog();
            log.setMember(member);
            log.setMember2(seller);
            log.setAmount(-point);
            log.setContents("pay:" + reason);
            log.setEventDate(new Date());
            log.setIncDec(0);

            MemberCreditLog log2 = new MemberCreditLog();
            log2.setMember(seller);
            log2.setMember2(member);
            log2.setAmount(point);
            log2.setContents("receive:" + reason);
            log2.setEventDate(new Date());
            log2.setIncDec(1);

            session.save(log);
            session.save(log2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CreditException(TRANSACTION_ERROR);
        }
        System.out.println("MemberCreditBean.pay end");
    }
    
    public void topup(int point, String orderNo) {
        System.out.println("MemberCreditBean.topup");
        //充值时,判断交易是否已经完成,以免重复充值.
        if (isFinished(orderNo)) return;

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            credit.setCredit(credit.getCredit() + point);

            MemberCreditDAO dao = new MemberCreditDAO();
            dao.update(credit);

            MemberCreditLog log = new MemberCreditLog();
            log.setMember(member);
            log.setMember2(null);
            log.setAmount(point);
            log.setContents("topup:" + point);
            log.setEventDate(new Date());
            log.setIncDec(1);
            log.setOrderNo(orderNo);
            tx.commit();

            MemberCreditLogDAO dao2 = new MemberCreditLogDAO();
            dao2.save(log);
            System.out.println("充值完成。");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new CreditException(TRANSACTION_ERROR);
        }

    }

    private boolean isFinished(String orderNo) {
        MemberCreditLogDAO dao = new MemberCreditLogDAO();
        try {
            MemberCreditLog log = dao.findByMemberAndOrderNo(member, orderNo);
            return log != null;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
/*
    public static void main(String[] args) {
        Member m = MemberService.getMember("1000038");
        MemberCreditBean mb = new MemberCreditBean(m);
        mb.topup(100);
    }
*/
}
