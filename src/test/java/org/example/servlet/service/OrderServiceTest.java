package org.example.servlet.service;

import org.example.servlet.pojo.Cart;
import org.example.servlet.pojo.CartItem;
import org.example.servlet.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "测试书名1", 1, new BigDecimal(99), new BigDecimal(99)));
        cart.addItem(new CartItem(1, "测试书名1", 1, new BigDecimal(99), new BigDecimal(99)));
        cart.addItem(new CartItem(2, "测试书名2", 1, new BigDecimal(100), new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        String res = orderService.createOrder(cart, 1);
        System.out.println("订单号是： " + res);
    }
}