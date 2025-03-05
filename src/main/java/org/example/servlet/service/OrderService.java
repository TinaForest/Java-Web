package org.example.servlet.service;

import org.example.servlet.pojo.Cart;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);
}
