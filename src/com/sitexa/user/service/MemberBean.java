package com.sitexa.user.service;

import com.sitexa.framework.hibernate.HibernateSessionFactory;
import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.Member;
import com.sitexa.user.data.MemberDAO;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: xnpeng
 * Date: 2010-2-8
 * Time: 9:05:05
 */
public class MemberBean {
    private Member member;

    public MemberBean(Member member) {
        this.member = member;
        init();
    }

    private void init() {
        if (member == null || member.getMemberId() == null || "".equals(member.getMemberId())) {
            throw new MemberException(MemberException.OBJECT_ERROR);
        }

        try {
            MemberDAO dao = new MemberDAO();
            member = dao.findById(member.getMemberId());
        } catch (Exception e) {
            throw new MemberException(MemberException.DAO_ERROR);
        }
    }

    public List<Member> friends() {
        List<Member> friends = new ArrayList<Member>();
        String hql = "from Friend as model where model.member=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member);
        List list = query.list();
        for (Object aList : list) {
            Friend friend = (Friend) aList;
            friends.add(friend.getFellow());
        }
        return friends;
    }

    public List<Member> friends(boolean flag) {
        List<Member> friends = new ArrayList<Member>();
        String hql = "from Friend as model where model.member=? and status=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member).setParameter(1, flag);
        List list = query.list();
        for (Object aList : list) {
            Friend friend = (Friend) aList;
            friends.add(friend.getFellow());
        }
        return friends;
    }

    public List<Member> fellows() {
        List<Member> friends = new ArrayList<Member>();
        String hql = "from Friend where fellow=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member);
        List list = query.list();
        for (Object aList : list) {
            Friend friend = (Friend) aList;
            friends.add(friend.getMember());
        }
        return friends;
    }

    public List<Member> fellows(boolean flag) {
        List<Member> friends = new ArrayList<Member>();
        String hql = "from Friend where fellow=? and status=?";
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql).setParameter(0, member).setParameter(1, flag);
        List list = query.list();
        for (Object aList : list) {
            Friend friend = (Friend) aList;
            friends.add(friend.getMember());
        }
        return friends;
    }

    public void applyFriend(Member fellow) {
        if (fellow == null) return;

        Session session = HibernateSessionFactory.getSession();
        String hql = "from Friend as model where model.member=? and fellow=?";

        Friend f = (Friend) session.createQuery(hql).setParameter(0, member).setParameter(1, fellow).uniqueResult();
        if (f == null) {
            f = new Friend();
            f.setMember(member);
            f.setFellow(fellow);
            f.setRemark(new Date().toString());
            f.setId(Sequence.getId());
            session.save(f);
        }
    }

    /**
     * 认证朋友:true,通过请求;false,拒绝请求;
     *
     * @param friend;
     * @param flag;
     */
    public void verifyFriend(Member friend, boolean flag) {
        if (friend == null) return;
        String hql = "from Friend as model where model.member=? and model.fellow=?";
        Session session = HibernateSessionFactory.getSession();
        Friend f = (Friend) session.createQuery(hql).setParameter(0, friend).setParameter(1, member).uniqueResult();
        if (f != null) {
            f.setComment(new Date().toString());
            f.setStatus(flag);
            session.update(f);
        }
    }

    public static void main(String[] args) {
        Member me = MemberService.getMember("1000038");
        MemberBean mb = new MemberBean(me);
        List l = mb.friends();
        System.out.println("l.size() = " + l.size());
        l = mb.fellows();
        System.out.println("l.size() = " + l.size());

    }
}
