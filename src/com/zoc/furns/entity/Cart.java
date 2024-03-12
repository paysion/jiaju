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
     * 获取购物车商品总数
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
        BigDecimal cartTotalPrice = new BigDecimal(0);
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            cartTotalPrice = items.get(id).getPrice().add(items.get(id).getTotalPrice());
        }
        return cartTotalPrice;
    }

    /**
     * 修改购物车商品数量和总价，count的值是从前端传递过来的
     * @return
     */
    public void updateCount(int id, int count){
        // 1.根据id找到要修改的cartItem对象
        CartItem cartItem = items.get(id);
        // 老是忘了非空判断，不过这个非空判断有什么用呢？
        if (null != cartItem) {
            cartItem.setCount(count);
            // 这里要乘的数量最好是用getCount()的方法，保持事务的一致性
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public void deleteItem(int id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
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
