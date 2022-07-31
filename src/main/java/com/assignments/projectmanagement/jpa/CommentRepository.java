package com.assignments.projectmanagement.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}