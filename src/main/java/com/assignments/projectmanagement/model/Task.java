package com.assignments.projectmanagement.model;

import java.util.Date;

public class Task {
    Integer id;
    Integer board_id;
    String name;
    String description;
    String[] attachments;
    Integer created_by;
    Integer assignee;
    Integer[] sprints;
    Integer estimation;
    Priority priority;
    TaskType type;
    Date dueDate;
    Date createdAt;
    Date deletedAt;
    String label;
    Integer[] comments;
    Integer[] linkedTasks;

    public enum Priority {
        P0, P1, P2, P3;
    }

    public enum TaskType {
        FEATURE, STORY, DEFECT, BUG
    }

}
