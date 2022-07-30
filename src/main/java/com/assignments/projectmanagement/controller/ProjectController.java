package com.assignments.projectmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.BoardRepository;
import com.assignments.projectmanagement.jpa.ProjectRepository;
import com.assignments.projectmanagement.model.Board;
import com.assignments.projectmanagement.model.Project;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/project/{projectId}")
    public Optional<Project> getProject(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).map(project -> {
            List<Board> boards = boardRepository.findAllByProjectId(project.getId());
            project.setBoards(boards);
            return project;
        });
    }

    @PostMapping("/project")
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @GetMapping(path = "/projects")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }
}
