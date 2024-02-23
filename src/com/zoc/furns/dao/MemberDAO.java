package com.zoc.furns.dao;

import com.zoc.furns.entity.Member;

public interface MemberDAO {

    // 提供一个通过用户名返回对应的Member
    public Member queryMemberByUsername(String username);

    // 提供一个保存Member对象到数据库/表member表
    public int saveMember(Member member);

    /**
     * 根据用户名和密码返回Member
     * @param username 用户名
     * @param password 密码
     * @return 返回的对象，如果不存在，返回null
     *
     */
    public Member queryMemberByUsernameAndPassword(String username,String password);
}
