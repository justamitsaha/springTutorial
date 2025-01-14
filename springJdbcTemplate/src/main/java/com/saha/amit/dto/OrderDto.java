package com.saha.amit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderUuid;
    private String orderNumber;
    Map<Long, ProductDto> products;
    private PaymentStatus paymentStatus;
}
