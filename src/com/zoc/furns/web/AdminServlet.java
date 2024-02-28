package com.zoc.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends BasicServlet{



    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 这里直接转发，admin登录的service和dao代码暂且放置不管
        req.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(req,resp);
    }
}
