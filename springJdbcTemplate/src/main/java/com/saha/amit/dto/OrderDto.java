package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderUuid;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String orderNumber;
    // Since productUuid in ProductDto can't be updated due to READ_ONLY
    // hence adding List of productIds which will be sent from swagger/client
    @Schema(
            description = "List of product IDs",
            example = "[1, 2, 3]"
    )
    List<Integer> productIds;
    List<ProductDto> products;
    private PaymentStatus paymentStatus;
}
