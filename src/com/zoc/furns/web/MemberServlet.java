package com.zoc.furns.web;

import com.zoc.furns.entity.Member;
import com.zoc.furns.service.IMemberService;
import com.zoc.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

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
        // 获取用户提交的验证码
        String code = req.getParameter("code");

        // 判断验证码是否正确
        if (!validateCode(req,code)) {
            req.setAttribute("msg","验证码错误");
            // 返回注册页面
            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
            return;
        }

        // 判断用户是否存在
        if (!memberService.isExistsUsername(username)) {
            // 用户不存在注册用户
            Member member = new Member(null, username, password, email);
            if (memberService.registerMember(member)) {
                // 请求跳转到注册成功页面
                req.getRequestDispatcher("/views/member/register_ok.jsp").forward(req,resp);
                System.out.println("注册成功");
            } else {
                req.getRequestDispatcher("/views/member/register_fail.html").forward(req,resp);
            }
        } else {
            // 返回注册页面
            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
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
        Member member = memberService.login(new Member(null, username, password, null));
        // 获取用户输入的验证码，暂时关闭验证码，每次登录太麻烦了
/*        String loginCode = req.getParameter("loginCode");

        if (!validateCode(req,loginCode)) {
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
            return;
        }*/

        if (member != null) {
            // 将member放入session
            req.getSession().setAttribute("member",member);
            // 跳转登录成功页面
            req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req,resp);
        } else {
            // 用户为空,登录失败，将错误信息放入到req域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁当前用户的session
        req.getSession().invalidate();
        // 重定向到网站首页
        resp.sendRedirect(req.getContextPath());
    }

    // 将校验验证码的方法提取出来
    private boolean validateCode(HttpServletRequest req, String code){
        // 从session中获取验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 立即删除验证码，防止验证码被重复使用
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 判断验证码是否正确
        return token!=null && token.equalsIgnoreCase(code);
    }
}
