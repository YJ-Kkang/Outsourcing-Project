package com.example.outsourcingproject.auth.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final RedisTemplate<String, String> redisTemplate; // Redis 사용

    private static final long TOKEN_EXPIRATION_TIME = 86400; // 24시간 (초)

    // Blacklist에 토큰 추가
    public void addTokenToBlacklist(String token) {
        String resolvedToken = resolveToken(token);

        // Redis에 토큰 저장 (만료 시간은 토큰 자체의 만료 시간과 동일)
        redisTemplate.opsForValue().set(resolvedToken, "blacklisted", TOKEN_EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    // 토큰이 Blacklist에 있는지 확인
    public boolean isTokenBlacklisted(String token) {
        String resolvedToken = resolveToken(token);
        return redisTemplate.hasKey(resolvedToken);
    }

    private String resolveToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7); // "Bearer "를 잘라냄
        }
        return token; // "Bearer " 없이도 토큰을 반환
    }
}