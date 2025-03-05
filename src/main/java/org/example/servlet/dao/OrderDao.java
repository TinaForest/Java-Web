package org.example.servlet.dao;

import org.example.servlet.pojo.Order;

public interface OrderDao {
    int saveOrder(Order order);
}
