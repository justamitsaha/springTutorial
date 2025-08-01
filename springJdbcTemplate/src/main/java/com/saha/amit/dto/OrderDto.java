package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderUuid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderNumber;
    List<ProductDto> products;
    private PaymentStatus paymentStatus;
}
