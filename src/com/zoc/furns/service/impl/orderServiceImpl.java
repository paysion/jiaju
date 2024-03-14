package com.zoc.furns.service.impl;

import com.zoc.furns.dao.OrderDAO;
import com.zoc.furns.dao.impl.OrderDAOImpl;
import com.zoc.furns.entity.Order;
import com.zoc.furns.service.IOrderService;

import java.util.List;


public class orderServiceImpl implements IOrderService {
    // 注入orderDAO属性,参数还没确定
    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean saveOrder(Order order) {
        return orderDAO.saveOrder(order);
    }

    @Override
    public List<Order> showOrder() {
        return orderDAO.showOrder();
    }
}
