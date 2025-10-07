package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
    public class RedisCacheService {

        private final RedisTemplate<String, String> redisTemplate;

        public RedisCacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

        public void saveArticle(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        System.out.println("✅ Redis 캐시에 저장됨: " + key + " = " + value);
    }

        public String getArticle(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // ✅ 캐시 조회용 메서드
        public String getLastArticle() {
        return redisTemplate.opsForValue().get("lastArticle");
    }
}
