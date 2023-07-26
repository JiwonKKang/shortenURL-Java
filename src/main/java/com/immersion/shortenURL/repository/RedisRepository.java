package com.immersion.shortenURL.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void set(String shortToken, String originalUrl) {
        redisTemplate.opsForValue().set(shortToken, originalUrl);
    }

    public String get(String shortToken) {
        return redisTemplate.opsForValue().get(shortToken);
    }
}
