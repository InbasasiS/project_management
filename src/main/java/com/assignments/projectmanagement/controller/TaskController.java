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
import com.assignments.projectmanagement.jpa.CommentRepository;
import com.assignments.projectmanagement.jpa.TaskRepository;
import com.assignments.projectmanagement.jpa.UserRepository;
import com.assignments.projectmanagement.model.Comment;
import com.assignments.projectmanagement.model.Task;
import com.assignments.projectmanagement.model.TaskLink;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

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
        Optional<Task> task = taskRepository.findById(taskId);
        Integer totalEstimates = task.get().getEstimation(); // This includes the linked tasks estimates
        if (task != null) {
            for (TaskLink taskLink : task.get().getLinkedTasks()) {
                totalEstimates += taskLink.getLinkedTask().getEstimation();
            }
            task.get().setTotalEstination(totalEstimates);
        }
        return task;
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

    @PostMapping("/tasks/{taskId}/comment")
    public Optional<Comment> createComment(@RequestBody Comment comment, @PathVariable Long taskId,
            @RequestParam(value = "user") Long userId)
            throws Exception {
        return taskRepository.findById(taskId).map(task -> {
            return userRepository.findById(userId).map(user -> {
                comment.setTask(task);
                comment.setUser(user);
                return commentRepository.save(comment);
            });
        }).orElseThrow(() -> new NotFoundException());
    }

}
