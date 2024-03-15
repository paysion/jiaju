package com.zoc.furns.web;

import com.zoc.furns.entity.Cart;
import com.zoc.furns.entity.Member;
import com.zoc.furns.entity.Order;
import com.zoc.furns.service.IOrderService;
import com.zoc.furns.service.impl.OrderServiceImpl;
import com.zoc.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BasicServlet{

    private IOrderService orderService = new OrderServiceImpl();

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从前端获取oder订单对象, req.getParameterMap();得到的是Map<String,String[]>类型 xxx
        // Order order = DataUtils.copyParamToBean(req.getParameterMap(), new Order());
        // 1.从session去取cart对象和member对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        // 这里member.getId()为什么是空的？登录时候传递的member并不是数据库的member，而是前端传的username和password
        Member member = (Member)req.getSession().getAttribute("member");
        // 2.判断是否登录
        if (null == member) {
            req.setAttribute("msg","用户未登录~");
            System.out.println("用户未登录");
            return;
        }
        // 3.调用orderService进行业务处理
        String orderId = orderService.saveOrder(cart, member.getId());
        // 这里是post请求，使用重定向，所以并不能把orderId放入req域中，而是应该放入session
        // req.setAttribute("orderId",orderId);
        req.getSession().setAttribute("orderId",orderId);
        // 4.重定向
        resp.sendRedirect("views/order/checkout.jsp");
    }

    protected void showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.数据库读取数据
        List<Order> orders = orderService.showOrder();
        // 2.将orders装进req域中
        req.setAttribute("orders",orders);
        // 3.请求转发
        req.getRequestDispatcher("views/order/order.jsp").forward(req,resp);
    }
}
