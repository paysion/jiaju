package com.zoc.furns.service;

import com.zoc.furns.entity.Order;

import java.util.List;


public interface IOrderService {

    /**
     * 添加订单
     * @return
     */
    public boolean saveOrder(Order order);

    /**
     * 展示订单页面
     * @return
     */
    public List<Order> showOrder();

}
