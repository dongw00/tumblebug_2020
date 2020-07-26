package com.tumblbug.project.contents.repository;

import com.tumblbug.project.contents.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
