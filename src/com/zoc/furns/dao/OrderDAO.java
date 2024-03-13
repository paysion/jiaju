package com.zoc.furns.dao;

import com.zoc.furns.entity.Order;

public interface OrderDAO {

    /**
     *
     * @param order
     */
    public boolean saveOrder(Order order);
}
