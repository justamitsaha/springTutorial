package com.saha.amit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgrammerForProjectModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String programmerName;
    private int salary;
}
