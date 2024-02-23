package com.zoc.furns.test;

import com.zoc.furns.dao.MemberDAO;
import com.zoc.furns.dao.impl.MemberDAOImpl;
import com.zoc.furns.entity.Member;
import org.junit.jupiter.api.Test;

public class MemberDAOTest {

    private MemberDAO memberDAO = new MemberDAOImpl();

    @Test
    public void queryMemberByUsername(){
        Member member = memberDAO.queryMemberByUsername("admin");
        System.out.println(member);
    }


    @Test
    public void saveMember(){
        Member member = new Member(null,"Asheng","123456","133xxxx@163.com");
        int rs = memberDAO.saveMember(member);
        System.out.println(rs);
    }

}
