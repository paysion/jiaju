package com.zoc.furns.web;

import com.zoc.furns.entity.Member;
import com.zoc.furns.service.IMemberService;
import com.zoc.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberServlet extends BasicServlet{
    //定义一个MemberService属性
    private IMemberService memberService = new MemberServiceImpl();


    /**
     * 处理会员登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户注册信息 -> 一定要去前端页面查看字段..
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // 判断用户是否存在
        if (!memberService.isExistsUsername(username)) {
            // 用户不存在注册用户
            Member member = new Member(null, username, password, email);
            if (memberService.registerMember(member)) {
                // 请求跳转到注册成功页面
                req.getRequestDispatcher("/views/member/register_ok.html").forward(req,resp);
                System.out.println("注册成功");
            } else {
                req.getRequestDispatcher("/views/member/register_fail.html").forward(req,resp);
            }
        } else {
            // 返回注册页面
            req.getRequestDispatcher("/views/member/login.html").forward(req,resp);
        }

        System.out.println("RegisterServlet 被调用了...");
    }


    /**
     * 会员登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
