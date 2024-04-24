package com.saha.amit.entity.relationShip.manyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Programmer {

    @Id
    private int id;
    private String name;
    private int salary;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programmers_projects",
            joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

}
