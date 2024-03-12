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
        // 4.判断cart是否为空,为空则代表session中没有cart1对象
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


    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从前端获取id和count
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        int count = DataUtils.parseInt(req.getParameter("count"), 0);
        // 2.从session中取cart对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        // cart自己本身就是数据模型，自带方法
        if (null != cart) {
            cart.updateCount(id,count);
        }
        // 更新完毕，返回添加家居的页面，因为要涉及到更新数据，所以要使用重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.前端获取id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 2.从session中取cart对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        // 3.这里的cart指的是购物车，将购物车看作一个对象
        if ( null!= cart) {
            cart.deleteItem(id);
        }
        // 重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取session中的cart购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
