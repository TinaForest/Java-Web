package org.example.servlet.dao;

import org.example.servlet.pojo.OrderItem;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
}
