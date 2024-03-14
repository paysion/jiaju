package com.zoc.furns.dao.impl;

import com.zoc.furns.dao.BasicDAO;
import com.zoc.furns.dao.OrderDAO;
import com.zoc.furns.entity.Order;

import java.util.List;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {


    @Override
    public boolean saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(?,?,?,?,?) ";
        int update = update(sql, order.getId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
        return update > 0;
    }

    @Override
    public List<Order> showOrder() {
        // 这里sql中的crete_time和member_time不用别名的话，会导致查询结果的字段名和实体类的属性名不一致，导致反射失败
        String sql = "SELECT `id`,`create_time` createTime,`price`,`status`,`member_id` memberId FROM `order`";
        List<Order> orders = queryMulti(sql, Order.class);
        return orders;
    }
}
