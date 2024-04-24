package com.saha.amit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectForProgrammerModel {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String projectName;
}
