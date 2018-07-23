package com.vip.first.repository;

import com.vip.first.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户数据仓储
 */
@Repository
public class UserRepository {
    private final Map<Long, User> repository = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong();

    public Boolean save(User user) {
        // id从1开始
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        return null == repository.put(id, user);
    }

    // Effective Java II
    public Collection<User> findAll(){
        return repository.values();
    }
}
