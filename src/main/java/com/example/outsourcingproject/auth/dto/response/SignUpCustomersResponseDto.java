package com.example.outsourcingproject.auth.dto.response;

import com.example.outsourcingproject.entity.Customer;
import lombok.Getter;

@Getter
public class SignUpCustomersResponseDto {

    private final Long id;
    private final String email;

    public SignUpCustomersResponseDto(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
    }
}
