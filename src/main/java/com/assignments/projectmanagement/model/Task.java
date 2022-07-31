package com.assignments.projectmanagement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

// The Tasks belongs to Board
// The Task is one to one mapping to assignee
// The Task has many comments
// The Task can be linked to other tasks
// The Task may or may not be associated with a sprint

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    // Assumed the estimation in days
    @Column(name = "estimation")
    Integer estimation;

    // Refers the total estimation including linked tasks
    Integer totalEstination;

    @Column(name = "priority")
    Priority priority;

    @Column(name = "type")
    TaskType type;

    @Column(name = "due_date")
    Date dueDate;

    @Column(name = "label")
    String label;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    User assignee;

    @Column(name = "assignee_name")
    String assigneeName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id", nullable = false)
    @JsonIgnore
    Board board;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "sprint_id", nullable = true)
    @JsonIgnore
    Sprint sprint;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<TaskLink> linkedTasks;

    // ProjectUser created_by;
    // String[] attachments;

    public enum Priority {
        P0, P1, P2, P3;
    }

    public enum TaskType {
        FEATURE, STORY, DEFECT, BUG
    }

}
