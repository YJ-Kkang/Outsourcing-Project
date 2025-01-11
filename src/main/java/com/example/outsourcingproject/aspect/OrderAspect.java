package com.example.outsourcingproject.aspect;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.exception.notfound.OrderNotFoundException;
import com.example.outsourcingproject.order.OrderState;
import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import com.example.outsourcingproject.order.repository.OrderRepository;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    // todo 리팩토링 할 때는 의존하지 말고 써보자 (+) 쓰레드 로컬)
    private final OrderRepository orderRepository;

    public OrderAspect(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 어노테이션을 하나 만들어서 해당 포인트 컷에 어노테이션을 달아주는 방법도 있다. (현업에서는 이렇게 쓴다, 현재는 패키지 기반이다.)
    @Pointcut("execution(* com.example.outsourcingproject.order.service.OrderServiceImpl.updateOrderStatus(..))")
    public void trackOrderServiceMethods() {
    }

    @Around("trackOrderServiceMethods()")
    public Object trackOrderStatusUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        System.out.println(methodName + " method starts");
        UpdateOrderRequestDto requestDto = (UpdateOrderRequestDto) args[0];
        Long orderId  = requestDto.getId();

        // todo try catch 문으로 잡아주자 + 예외 던지는 건 서비스에서 하니까 서비스에서 하게 두자
        Order foundOrder = orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);

        Long storeId = foundOrder.getStore().getId();
        OrderState currentOrderState = foundOrder.getOrderState();
        LocalDateTime orderRequestTime = foundOrder.getCreatedAt();

        System.out.println("Order ID: " + orderId);
        System.out.println("Store ID: " + storeId);
        System.out.println("Current Order State: " + currentOrderState);

        Object result = joinPoint.proceed();

        UpdateOrderResponseDto responseDto = (UpdateOrderResponseDto) result;
        OrderState updatedOrderState = responseDto.getUpdatedOrderState();

        System.out.println("Order Request Time: " + orderRequestTime);
        System.out.println("Updated Order State: " + updatedOrderState);
        System.out.println("Log: Order status has changed from " + currentOrderState + " to " + updatedOrderState);

        return result;
    }
}