package com.tumblbug.project.contents.repository;

import com.tumblbug.project.contents.model.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Optional<Creator> findByEmail(String email);
}
