/*
package com.zoc.furns.web;

import com.zoc.furns.entity.Member;
import com.zoc.furns.service.IMemberService;
import com.zoc.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private IMemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Member member = new Member(null, username, password, email);
        if (memberService.login(member) != null) {
            // 跳转登录成功页面
            req.getRequestDispatcher("/views/member/login_ok.html").forward(req,resp);
        } else {
            req.getRequestDispatcher("/views/member/login.html").forward(req,resp);
        }
    }
}
*/
