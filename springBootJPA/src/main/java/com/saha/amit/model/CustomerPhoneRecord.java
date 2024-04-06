package com.saha.amit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPhoneRecord{
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Long id;
        private String name;
        private List<PhoneDetails> phoneDetailsArrayList;
}
