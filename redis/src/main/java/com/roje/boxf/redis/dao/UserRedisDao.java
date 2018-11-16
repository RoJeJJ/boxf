package com.roje.boxf.redis.dao;

import com.roje.boxf.model.User;

public interface UserRedisDao {

    User getUserByAccount(String account);

    void setUserAccount(User user);
}
