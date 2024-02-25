package com.zoc.furns.service.impl;

import com.zoc.furns.dao.MemberDAO;
import com.zoc.furns.dao.impl.MemberDAOImpl;
import com.zoc.furns.entity.Member;
import com.zoc.furns.service.IMemberService;

public class MemberServiceImpl implements IMemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean registerMember(Member member) {
        int res = memberDAO.saveMember(member);
        return res>0;
    }

    @Override
    public boolean isExistsUsername(String username) {
        Member member = memberDAO.queryMemberByUsername(username);
        return member==null ? false:true;
    }

    @Override
    public Member login(Member member) {
        return memberDAO.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());
    }
}
