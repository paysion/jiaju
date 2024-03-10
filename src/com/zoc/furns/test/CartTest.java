package com.zoc.furns.test;

import com.zoc.furns.entity.Cart;
import org.junit.jupiter.api.Test;

public class CartTest {

    @Test
    public void addItem(){
        Cart cart = new Cart();
        cart.addItem(1);
        System.out.println(cart.getItems());
    }

}
