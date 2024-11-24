package com.saha.amit.repository;

import com.saha.amit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    public List<Profile> findByEmailContaining(String email);
}

