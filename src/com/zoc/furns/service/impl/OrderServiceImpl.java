package com.zoc.furns.service.impl;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.OrderDAO;
import com.zoc.furns.dao.OrderItemDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;
import com.zoc.furns.dao.impl.OrderDAOImpl;
import com.zoc.furns.dao.impl.OrderItemDAOImpl;
import com.zoc.furns.entity.*;
import com.zoc.furns.service.IOrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class OrderServiceImpl implements IOrderService {
    // 注入orderDAO属性,参数还没确定
    private OrderDAO orderDAO = new OrderDAOImpl();
    private FurnDAO furnDAO = new FurnDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Override
    public String saveOrder(Cart cart,int memberId) {
        // 1.根据cart去拼凑订单order
        String orderId = System.currentTimeMillis() + "" + memberId;
        Order order = new Order(orderId,new Date(),cart.getCartTotalPrice(),0,memberId); // status应该怎么设值呢？
        // 2.将order保存到数据库
        orderDAO.saveOrder(order);
        // 3.根据cart拼凑orderItem，orderItem和cartItem其实是一一对应的
        HashMap<Integer, CartItem> items = cart.getItems();
        for (Integer id : items.keySet()) {
            // 这里的id就是furn的id,根据id去得到furn对象
            CartItem cartItem = items.get(id);
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getPrice(),cartItem.getCount(),cartItem.getTotalPrice(),orderId);
            orderItemDAO.saveOrderItem(orderItem);
            // 修改furn表的sales和stock属性
            Furn furn = furnDAO.queryFurn(id);
            furn.setSales(furn.getSales()+cartItem.getCount());
            furn.setStock(furn.getStock()-cartItem.getCount());
            furnDAO.updateFurn(furn);
        }
        // 4.清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showOrder() {
        return orderDAO.showOrder();
    }
}
