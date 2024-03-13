package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.OrderDAO;
import com.zoc.furns.entity.Order;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {


    @Override
    public boolean saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(NULL,NOW(),?,?,?) ";
        int update = update(sql, order.getPrice(), order.getStatus(), order.getMemberId());
        return update > 0;
    }
}
