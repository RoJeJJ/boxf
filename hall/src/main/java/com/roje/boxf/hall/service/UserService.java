package com.roje.boxf.hall.service;

import com.roje.boxf.hall.dao.UserDao;
import com.roje.boxf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserByAccount(String account){
        return userDao.getUserByAccount(account);
    }

    public void save(User user){
        User newUser = userDao.save(user);
        user.setId(newUser.getId());
    }
}
