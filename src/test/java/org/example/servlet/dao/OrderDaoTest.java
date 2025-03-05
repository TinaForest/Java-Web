package org.example.servlet.dao;

import org.example.servlet.dao.impl.OrderDaoImpl;
import org.example.servlet.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567892", new Date(), new BigDecimal(100), 0, 1));
    }
}