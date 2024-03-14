package com.zoc.furns.service;

import com.zoc.furns.entity.Cart;
import com.zoc.furns.entity.Order;

import java.util.List;


public interface IOrderService {

    /**
     * 添加订单
     * 接收的参数应该是cart而不是order，订单是根据点击购物车结账的时候生成的，将购物车对象cart传递过来
     * 同时，订单和用户Id是绑定的
     * @param cart,memberId
     * @return String返回订单号
     */
    public String saveOrder(Cart cart, int memberId);

    /**
     * 展示订单页面
     * @return
     */
    public List<Order> showOrder();

}
