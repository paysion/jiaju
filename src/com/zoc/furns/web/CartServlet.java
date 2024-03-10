package com.zoc.furns.web;

import com.zoc.furns.entity.Cart;
import com.zoc.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CartServlet extends BasicServlet {

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从前端接收传递过来的id
        int id = DataUtils.parseInt(req.getParameter("id"),0);
        // 2.注入CartItem属性？Servlet层写实体类？
        // cart.addItem(id);

        // 3.从session中获取cart对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        // 4.判断cart1是否为空,为空则代表session中没有cart1对象
        if (null == cart) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(id);
        System.out.println(cart); // 请求http://localhost:8080/jiaju_mall/cartServlet?action=addItem&id=1测试添加是否成功

        // 4.添加完毕，返回添加家居的页面
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }
}
