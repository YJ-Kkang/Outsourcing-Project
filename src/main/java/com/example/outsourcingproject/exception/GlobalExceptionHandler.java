package com.example.outsourcingproject.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handlerCustomException(CustomException ex) {
        Map<String, String> response = new LinkedHashMap<>();

        response.put("errorCode", ex.getErrorCode().getCode());
        response.put("errorMessage", ex.getErrorCode().getMessage());

        log.info("에러 발생 >>> 코드: {}, 메시지: {}",
            response.get("errorCode"), response.get("errorMessage"));
        return new ResponseEntity<>(response, ex.getErrorCode().getStatus());
    }

}
