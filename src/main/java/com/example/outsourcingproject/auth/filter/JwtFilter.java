package com.example.outsourcingproject.auth.filter;

import com.example.outsourcingproject.utils.JwtUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j(topic = "Jwtfilter")
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    private static final String[] WHITE_LIST = {
        "/auth/sign-in/customers",
        "/auth/sign-in/owners",
        "/auth/sign-up/customers",
        "/auth/sign-up/owners"
    };



    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain filterChain
    )
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();
        String email = null;
        String jwtToken = null;

        String authorizationHeader = httpRequest.getHeader("Authorization");

        /**
         * todo
         * 로그인 상태에서 (토큰 유효) 회원가입, 로그인 요청 안되게
         * 로그인 상태가 아니면 가게, 주문, 리뷰 URL 접근 X
         * 로그인 상태 O but 손님과 사장님이 접근 할 수 있는 URL 들이 있다.
         */
        log.info("Jwt Filter 로직 실행");
        log.info("request URI: {}", requestURI);

        boolean isWhiteList = isWhiteList(requestURI);
        if(isWhiteList) {
            log.info("WHITE_LIST에 포함되어 필터 통과");
            filterChain.doFilter(servletRequest, servletResponse); // 통과
            return;
        }

        /**
         * 회원가입, 로그인 URI 아닐 때, JWT 토큰 있는지 검사
         */

        // 토큰이 없음
        log.info("authorizaionHeader : {}", authorizationHeader);
        boolean isInvalidAuthorizationHeader = authorizationHeader == null || !authorizationHeader.startsWith("Bearer ");
        if(isInvalidAuthorizationHeader) {
            log.info("JWT 토큰 필요");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요합니다.");
            return;
        }

        //토큰이 있음 -> 유효성 검사 (secret key가 내가 만든 거랑 동일한지, Jwt 시간 만료된게 아닌지
        jwtToken = authorizationHeader.substring(7);
        log.info("jwtToken : {}", jwtToken);

        // 유효하지 않은 case
        boolean isValidateToken = jwtUtil.validateToken(jwtToken);
        if(!isValidateToken) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("{\"error\": \"Unauthoizated\"}");
        }

        // 통과
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 화이트리스트에 requestURI 포함되어 있는지 확인
    public boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }

}
