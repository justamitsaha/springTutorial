package com.saha.amit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProfileOrderDto {
    private Long customerUuid;
    private String customerName;
    private ProfileDto profile;
    private List<OrderDto> orders;
}
