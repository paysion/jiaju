package com.zoc.furns.test;

import com.zoc.furns.dao.OrderDAO;
import com.zoc.furns.dao.OrderItemDAO;
import com.zoc.furns.dao.impl.OrderDAOImpl;
import com.zoc.furns.dao.impl.OrderItemDAOImpl;
import com.zoc.furns.entity.Order;
import com.zoc.furns.entity.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDAOTest {

    private OrderDAO orderDAO = new OrderDAOImpl();
    protected OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void testShowOrder() {
        List<Order> orders = orderDAO.showOrder();
        if (orders!=null) {
            for (Order order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("没有订单信息");
        }
    }

    @Test
    public void testAddOrder() {
        Order order = new Order("sn002", new Date(), new BigDecimal(200.00), 0, 2);
        if (orderDAO.saveOrder(order)) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    public void testAddOrderItem() {
        OrderItem orderItem = new OrderItem(null,"无敌大茶几", new BigDecimal(300), 2, new BigDecimal(600), "sn000005");
        if (orderItemDAO.saveOrderItem(orderItem) > 0) {
            System.out.println("添加订单明细成功~");
        } else {
            System.out.println("添加订单明细失败~");
        }
    }

}
