package com.example.outsourcingproject.aspect;

import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    @Pointcut("execution(* com.example.outsourcingproject.order.service.OrderServiceImpl.updateOrderStatus(..))")
    public void trackOrderServiceMethods() {
    }

    @Around("trackOrderServiceMethods()")
    public Object trackOrderStatusUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println(methodName + " method starts");

        Object result = joinPoint.proceed();

        UpdateOrderResponseDto responseDto = (UpdateOrderResponseDto) result;
        UpdateOrderRequestDto requestDto = (UpdateOrderRequestDto) args[0];

        System.out.println("Order ID: " + requestDto.getId());
        System.out.println("Updated Order State: " + responseDto.getUpdatedOrderState());

        return result; // todo 주문 요청 시각이랑 가게 id 출력도 같이 해야 함
    }
}