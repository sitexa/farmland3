package com.sitexa.user.service;

import com.sitexa.sys.Sequence;
import com.sitexa.user.data.Friend;
import com.sitexa.user.data.FriendDAO;
import com.sitexa.user.data.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: xnpeng
 * Date: 2009-5-12
 * Time: 11:56:29
 */
public class FriendService {
    private static Log log = LogFactory.getLog(FriendService.class);

    public static Friend getFriend(String id) {
        try {
            return (new FriendDAO()).findById(id);
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public static void create(Friend friend) {
        System.out.println("FriendService.create");
        Friend f = findByMemberAndFellow(friend.getMember(), friend.getFellow());
        if (f != null) return;
        if (null == friend.getId() || "".equals(friend.getId()))
            friend.setId(Sequence.getId());
        FriendDAO dao = new FriendDAO();
        try {
            dao.save(friend);
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    public static Friend findByMemberAndFellow(Member member, Member fellow) {
        if (member == null || fellow == null) return null;
        return (new FriendDAO()).findByMemberAndFellow(member, fellow);
    }

    public static void update(Friend friend) {
        FriendDAO dao = new FriendDAO();
        Friend f = dao.findById(friend.getId());
        f.setComment(friend.getComment());
        dao.update(f);
    }

    public static void acceptApply(Friend friend) {
        FriendDAO dao = new FriendDAO();
        Friend f = dao.findById(friend.getId());
        f.setStatus(true);
        dao.update(f);
        Friend f2;
        f2 = dao.findByMemberAndFellow(f.getFellow(), f.getMember());
        if (f2 == null) {
            f2 = new Friend();
            f2.setId(Sequence.getId());
            f2.setMember(f.getFellow());
            f2.setFellow(f.getMember());
            f2.setComment("回应对方申请");
            f2.setStatus(true);
            dao.save(f2);
        }
    }

    public static void delete(Friend friend) {
        (new FriendDAO()).delete(friend);
    }

    public static boolean isFriends(Member member, Member fellow) {
        return findByMemberAndFellow(member, fellow) != null;
    }

    public static void main(String[] args) {
        Friend f = getFriend("1000250");
        System.out.println("f = " + f);
        delete(f);
    }

    @SuppressWarnings("unchecked")
    public static List<Friend> findApply(Member member) {
        FriendDAO dao = new FriendDAO();
        return dao.findApplyByMember(member);
    }
}
