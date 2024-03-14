package com.zoc.furns.test;

import com.zoc.furns.dao.impl.OrderItemDAOImpl;
import com.zoc.furns.entity.Cart;
import com.zoc.furns.service.IOrderService;
import com.zoc.furns.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

public class orderServiceImplTest {
    // 注入属性
    private IOrderService orderService = new OrderServiceImpl();

    @Test
    public void saveOrder(){
        // 测试orderService功能
        Cart cart = new Cart();
        cart.addItem(1);
        String orderId = orderService.saveOrder(cart, 1);
        System.out.println(orderId);
    }
}
