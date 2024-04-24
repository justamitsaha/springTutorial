package com.saha.amit.entity.relationShip.manyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String name;

    //@ManyToMany(mappedBy = "projects")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programmers_projects",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"))
    private Set<Programmer> programmers;
}
