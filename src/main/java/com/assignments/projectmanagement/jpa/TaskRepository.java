package com.assignments.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
