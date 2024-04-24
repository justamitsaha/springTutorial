package com.saha.amit.repository;

import com.saha.amit.entity.relationShip.manyToMany.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
