package com.assignments.projectmanagement.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	Optional<Project> findById(Long id);

	List<Project> findAll();
}
