package com.assignments.projectmanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.TaskLinkRepository;
import com.assignments.projectmanagement.jpa.TaskRepository;
import com.assignments.projectmanagement.model.TaskLink;
import com.assignments.projectmanagement.model.TaskLink.LinkType;

@RestController
@RequestMapping("/api/v1")
public class TaskLinkController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskLinkRepository taskLinkRepositiry;

    @PostMapping("/tasks/{taskId}/link_task")
    public Optional<TaskLink> linkTask(@PathVariable Long taskId, @RequestParam(value = "task") Long linkedTaskId,
            @RequestParam(value = "type") LinkType linkType)
            throws Exception {
        return taskRepository.findById(taskId).map(task -> {
            return taskRepository.findById(linkedTaskId).map(linkedTask -> {
                TaskLink taskLink = new TaskLink();
                if (linkType != null) {
                    taskLink.setLinkType(linkType);
                }
                taskLink.setTask(task);
                taskLink.setLinkedTask(linkedTask);
                return taskLinkRepositiry.save(taskLink);
            });
        }).orElseThrow(() -> new NotFoundException());
    }

}
