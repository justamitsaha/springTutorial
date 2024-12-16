package com.saha.amit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UUID orderUuid;
    private String orderNumber;
    private CustomerDto customer;
    private List<ProductDto> productDto;
    private PaymentDto paymentDto;
}
