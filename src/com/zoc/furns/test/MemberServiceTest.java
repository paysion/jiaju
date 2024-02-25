package com.zoc.furns.test;

import com.zoc.furns.entity.Member;
import com.zoc.furns.service.IMemberService;
import com.zoc.furns.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    private IMemberService memberService = new MemberServiceImpl();

    @Test
    public void registerMember(){
        Member member = new Member(null, "zzz", "123456", "123456@163.com");
        boolean b = memberService.registerMember(member);
        if (b == true) {
            System.out.println("用户" + member.getUsername() + "注册成功");
        } else {
            System.out.println("注册失败");
        }
    }

}
