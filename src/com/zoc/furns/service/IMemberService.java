package com.zoc.furns.service;

import com.zoc.furns.entity.Member;

public interface IMemberService {

    // 注册用户
    public  boolean registerMember(Member member);

    // 判断用户名是否存在
    public boolean isExistsUsername(String username);

    // 用户登录
    public Member login(Member member);
}
