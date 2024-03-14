package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.OrderItemDAO;
import com.zoc.furns.entity.OrderItem;

public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {


    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO `order_item`(`id`,`name`,`price`,`count`,`total_price`,`order_id`) " +
                "VALUES(?,?,?,?,?,?) ";
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getPrice(),orderItem.getCount(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
