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
public class CustomerDto {
    private Long customerUuid;
    private String name;
    private ProfileDto profileDto;
    private List<OrderDto> orderDto;
}



