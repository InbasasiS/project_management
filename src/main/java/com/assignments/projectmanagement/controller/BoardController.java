package com.assignments.projectmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.BoardRepository;
import com.assignments.projectmanagement.jpa.ProjectRepository;
import com.assignments.projectmanagement.model.Board;

@RestController
@RequestMapping("/api/v1")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/board/{boardId}")
    public Optional<Board> getBoard(@PathVariable Long boardId) {
        return boardRepository.findById(boardId);
    }

    @PostMapping("/project/{projectId}/board")
    public Board create(@PathVariable Long projectId, @RequestBody Board board) throws Exception {
        return projectRepository.findById(projectId).map(project -> {
            board.setProject(project);
            return boardRepository.save(board);
        }).orElseThrow(() -> new NotFoundException());
    }

    @GetMapping("/board")
    public List<Board> fetchAllBoards() {
        return boardRepository.findAll();
    }

}
