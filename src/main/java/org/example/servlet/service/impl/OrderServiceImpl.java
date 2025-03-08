package org.example.servlet.service.impl;

import com.google.protobuf.MapEntry;
import org.example.servlet.dao.BookDao;
import org.example.servlet.dao.OrderDao;
import org.example.servlet.dao.OrderItemDao;
import org.example.servlet.dao.impl.BookDaoImpl;
import org.example.servlet.dao.impl.OrderDaoImpl;
import org.example.servlet.dao.impl.OrderItemDaoImpl;
import org.example.servlet.pojo.*;
import org.example.servlet.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //唯一
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);
//        int a = 12 / 0;
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            orderItemDao.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), cartItem.getTotalPrice(), orderId));
            //更新库存和销量
            Book book = bookDao.queryBookById(entry.getKey());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
