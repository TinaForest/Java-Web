package org.example.servlet.dao;

import org.example.servlet.dao.impl.OrderItemDaoImpl;
import org.example.servlet.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "Java从入门到精通", new BigDecimal(1), 1, new BigDecimal(1), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "JavaScript从入门到精通", new BigDecimal(1), 1, new BigDecimal(1), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Python从入门到精通", new BigDecimal(1), 1, new BigDecimal(1), "1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null, "C++从入门到精通", new BigDecimal(1), 1, new BigDecimal(1), "1234567890"));
    }
}