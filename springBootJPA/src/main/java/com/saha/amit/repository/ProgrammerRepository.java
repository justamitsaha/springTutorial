package com.saha.amit.repository;

import com.saha.amit.entity.relationShip.manyToMany.Programmer;
import org.springframework.data.repository.CrudRepository;

public interface ProgrammerRepository extends CrudRepository<Programmer, Integer> {

}
