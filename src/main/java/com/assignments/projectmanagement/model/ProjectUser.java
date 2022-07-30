package com.assignments.projectmanagement.model;

import java.util.Date;

public class ProjectUser {
    Integer id;
    String name;
    Role role;
    Integer team_id;
    UserStatus status;
    Board[] boards;
    Date createdAt;
    Date deletedAt;

    public enum UserStatus {
        ACTIVE, INACTIVE;
    }

    public enum Role {
        DEV, QA, MANAGER, ADMIN;
    }

}
