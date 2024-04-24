package com.saha.amit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectModel {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String projectName;
    private Set<ProgrammerForProjectModel> programmerForProjectModels;
}
