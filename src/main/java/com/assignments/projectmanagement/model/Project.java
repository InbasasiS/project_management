package com.assignments.projectmanagement.model;

import java.util.Date;

public class Project {
    Integer id;
    String title;
    Integer[] admin_ids;
    ProjectStatus status;
    Integer[] board_ids;
    Date createdAt;
    Date deletedAt;

    public enum ProjectStatus {
        ACTIVE, INACTIVE, PAUSED, DELETED;
    }
}
