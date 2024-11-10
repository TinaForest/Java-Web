package org.example.servlet.service.impl;


import org.example.servlet.dao.UserDao;
import org.example.servlet.dao.impl.UserDaoImpl;
import org.example.servlet.pojo.User;
import org.example.servlet.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }
}
