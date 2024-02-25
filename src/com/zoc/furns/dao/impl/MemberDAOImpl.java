package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.MemberDAO;
import com.zoc.furns.entity.Member;

public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO{


    @Override
    public Member queryMemberByUsername(String username) {
        String sql = "SELECT * FROM member WHERE username=?";
        Member member = querySingle(sql, Member.class, username);
        return member;
    }

    @Override
    public int saveMember(Member member) {
        String sql = "INSERT INTO member (username,password,email) VALUES (?,MD5(?),?)";
        return update(sql,member.getUsername(),member.getPassword(),member.getEmail());
    }

    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM member WHERE username=? and password=md5(?)";
        Member member = querySingle(sql,Member.class,username,password);
        return member;
    }
}
