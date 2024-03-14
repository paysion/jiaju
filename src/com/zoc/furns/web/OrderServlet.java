package com.zoc.furns.web;

import com.zoc.furns.entity.Order;
import com.zoc.furns.service.IOrderService;
import com.zoc.furns.service.impl.orderServiceImpl;
import com.zoc.furns.utils.DataUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BasicServlet{

    private IOrderService orderService = new orderServiceImpl();

    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.从前端获取oder订单对象, req.getParameterMap();得到的是Map<String,String[]>类型
        Order order = DataUtils.copyParamToBean(req.getParameterMap(), new Order());
        // 2.将order写入数据库,写入成功请求转发到order.jsp页面，失败输出失败信息
        if (orderService.saveOrder(order)) {
            req.getRequestDispatcher("views/order/order.jsp").forward(req,resp);
        } else {
            System.out.println("添加订单失败~");
        }
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
