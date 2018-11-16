package com.roje.boxf.redis.service;

import com.roje.boxf.model.User;
import com.roje.boxf.redis.config.RedisConfig;
import com.roje.boxf.redis.dao.UserRedisDao;
import org.springframework.data.redis.core.RedisTemplate;

public class UserRedisService implements UserRedisDao, RedisConfig {

    private final RedisTemplate<Object,Object> redisTemplate;

    public UserRedisService(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User getUserByAccount(String account) {
        return (User) redisTemplate.opsForHash().get(REDIS_USER,account);
    }

    @Override
    public void setUserAccount(User user) {
        redisTemplate.opsForHash().put(REDIS_USER,user.getAccount(),user);
    }
}
