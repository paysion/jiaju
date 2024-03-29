package com.zoc.furns.dao;

import com.zoc.furns.entity.Order;

import java.util.List;

public interface OrderDAO {

    /**
     *
     * @param order
     */
    public boolean saveOrder(Order order);

    public List<Order> showOrder();
}
