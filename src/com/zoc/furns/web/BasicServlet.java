package com.zoc.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 模板设计模式+动态绑定+反射
        String action = req.getParameter("action");
        // 这里可以使用if-else来判断action是Login还是Register；但是这样其实还是很臃肿。
        // 考虑用反射来获取，所谓反射，就是在运行过程中获取对象的方法，可以将方法也理解为一个对象，即方法对象。
        try {
            // this关键字：哪个对象调用的就指向哪个对象
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
