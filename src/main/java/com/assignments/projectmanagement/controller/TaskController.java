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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.BoardRepository;
import com.assignments.projectmanagement.jpa.TaskRepository;
import com.assignments.projectmanagement.jpa.UserRepository;
import com.assignments.projectmanagement.model.Task;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/board/{boardId}/task")
    public Task create(@PathVariable Long boardId, @RequestBody Task task) throws Exception {
        return boardRepository.findById(boardId).map(board -> {
            task.setBoard(board);
            return taskRepository.save(task);
        }).orElseThrow(() -> new NotFoundException());
    }

    @GetMapping(path = "/tasks")
    public List<Task> geTasks() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/task/{taskId}")
    public Optional<Task> getTask(@PathVariable Long taskId) {
        return taskRepository.findById(taskId);
    }

    @PostMapping("/tasks/{taskId}/assign_task")
    public Optional<Task> assignTask(@PathVariable Long taskId, @RequestParam(value = "user") Long userId)
            throws Exception {
        return taskRepository.findById(taskId).map(task -> {
            return userRepository.findById(userId).map(user -> {
                task.setAssignee(user);
                task.setAssigneeName(user.getName());
                return taskRepository.save(task);
            });
        }).orElseThrow(() -> new NotFoundException());
    }

}
