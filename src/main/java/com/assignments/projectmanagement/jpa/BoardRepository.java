package com.assignments.projectmanagement.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.projectmanagement.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByProjectId(Long projectId);

}
