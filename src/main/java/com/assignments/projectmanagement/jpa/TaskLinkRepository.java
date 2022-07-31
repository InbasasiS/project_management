package com.assignments.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.TaskLink;

public interface TaskLinkRepository extends JpaRepository<TaskLink, Long> {

}