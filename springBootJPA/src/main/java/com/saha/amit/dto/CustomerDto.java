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
    private String customerName;
    private ProfileDto profileDto;
    private List<OrderDto> orderDto;

    /**
     * This Constructor is needed for mapping the projection query in
     * Customer repository where it queries only for Customer and Profiles
     * @param customerUuid
     * @param customerName
     * @param profileDto
     */
    public CustomerDto(Long customerUuid, String customerName, ProfileDto profileDto) {
        this.customerUuid = customerUuid;
        this.customerName = customerName;
        this.profileDto = profileDto;
    }


}



