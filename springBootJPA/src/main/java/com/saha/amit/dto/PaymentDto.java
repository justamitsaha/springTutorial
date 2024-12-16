package com.saha.amit.dto;

import com.saha.amit.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private Long paymentUuid;

    private PaymentStatus paymentStatus;

}
