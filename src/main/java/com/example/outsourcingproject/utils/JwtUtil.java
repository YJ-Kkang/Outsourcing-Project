package com.example.outsourcingproject.utils;

import com.example.outsourcingproject.common.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    // JWT 토큰 값 앞에 붙는 접두사
    public static final String BEARER_PREFIX = "Bearer ";
    // JWT 토큰 만료 시간 (밀리초 단위)
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분
    // JWT 서명 알고리즘
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    // 애플리케이션 설정 파일에서 주입받은 비밀 키
    @Value("${jwt.secret.key}")
    private String secretKey;
    // 실제 서명에 사용되는 키 객체
    private Key key;

    /**
     * Bean 초기화 메서드
     * - 애플리케이션 시작 시 비밀 키를 Base64로 디코딩하여 Key 객체를 초기화
     */
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성
    public String createToken(String email, Authority authority) {

        Date date = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .setSubject(email) // 사용자 식별자
                .claim("auth", authority) // 사용자 역할
                .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 토큰의 만료시간
                .setIssuedAt(date) // 토큰 발급 시점
                .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                .compact();
    }

    // 토큰에서 클레임 객체를 추출하는 메서드
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .setSigningKey(key) // 비밀 키를 사용하여 서명 검증
            .parseClaimsJws(token)
            .getBody();
    }



}
