package com.saha.amit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgrammerModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String programmerName;
    private int salary;
    private Set<ProjectForProgrammerModel> projectForProgrammerModels;
}
