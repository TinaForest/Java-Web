package org.example.servlet.test;


import org.example.servlet.dao.UserDao;
import org.example.servlet.dao.impl.UserDaoImpl;
import org.example.servlet.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可用！");
        } else {
            System.out.println(userDao.queryUserByUsername("admin"));
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserByUsernameAndPassword("admin", "admin") == null) {
            System.out.println("用户名或密码错误，登录失败！");
        } else {
            System.out.println("查询成功！");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(2, "tyf", "tyftyf", "tyf@gmail.com")));

    }
}