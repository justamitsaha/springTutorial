package com.saha.amit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long productUuid;
    @Schema(description = "Product name", example = "iPhone")
    private String name;
    @Schema(description = "Product price", example = "99.99")
    private double price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate createdDate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedDate;
    @Schema(description = "Product category add 11 to see exception and @Transaction", example = "[4,5,8]")
    private List<Integer> categoryIds;
}
