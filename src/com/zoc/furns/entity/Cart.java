package com.zoc.furns.entity;

import com.zoc.furns.dao.FurnDAO;
import com.zoc.furns.dao.impl.FurnDAOImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

public class Cart {

    private HashMap<Integer,CartItem> items = new HashMap<>();

    private FurnDAO furnDAO = new FurnDAOImpl();

    /**
     * addItem中参数应该是id，还是cartItem对象呢？
     * 前端传递的肯定是id，根据id获取furn对象这个操作应该放在哪里合适呢？Servlet层？Service层？Dao层？
     * 如果就按照我这个想法，根据id来添加
     * @param id
     */
    public void addItem(int id){
        // 1.根据id查询到对应的Furn
        Furn furn = furnDAO.queryFurn(id);
        // 2.new CartItem并根据furn填满属性
        CartItem cartItem = new CartItem(id, furn.getName(), 1, furn.getPrice(), furn.getPrice());
        // 3.将CartItem放进items
        if (!items.containsKey(id)) {
            items.put(id,cartItem);
        } else {
            // 如果购物车已经有id对应的cartItem了，那么只需要更新cartItem的count和totalPrice就行
            items.get(id).setCount(items.get(id).getCount() + 1);
            // 这就是shi代码？
            items.get(id).setTotalPrice(items.get(id).getPrice().multiply(new BigDecimal(items.get(id).getCount())));
        }

    }

    /**
     *
     * @return
     */
    public int getTotalCount() {
        int totalCount = 0;
        // keySet()是Map接口的一个方法，用于返回map中所有键（key）的集合
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            totalCount += items.get(id).getCount();
        }
        return totalCount;
    }

    /**
     * 获取购物车总价
     * @return
     */
    public BigDecimal getCartTotalPrice(){
        return new BigDecimal(0);
    }

    /**
     * 修改购物车商品数量
     * @return
     */
    public void updateCount(int id, int count){
        String s1 = new StringBuilder("Ja").append("va").toString();
        System.out.println(s1.intern()==s1);
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern()==s2);
        String s3 = new StringBuilder("JA").append("VA").toString();
        System.out.println(s3.intern()==s3);
        String s4 = new StringBuilder("go").append("od").toString();
        System.out.println(s4.intern()==s4);
    }

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    /** set方法没必要？ */
    // public void setItems(HashMap<Integer, CartItem> items) {
    //     this.items = items;
    // }


    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
