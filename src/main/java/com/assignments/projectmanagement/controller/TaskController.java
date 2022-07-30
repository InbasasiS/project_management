package com.assignments.projectmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.BoardRepository;
import com.assignments.projectmanagement.jpa.TaskRepository;
import com.assignments.projectmanagement.model.Task;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/board/{boardId}/task")
    public Task create(@PathVariable Long boardId, @RequestBody Task task) throws Exception {
        return boardRepository.findById(boardId).map(board -> {
            task.setBoard(board);
            return taskRepository.save(task);
        }).orElseThrow(() -> new NotFoundException());
    }

    @GetMapping(path = "/tasks")
    public List<Task> getProjects() {
        return taskRepository.findAll();
    }

}
