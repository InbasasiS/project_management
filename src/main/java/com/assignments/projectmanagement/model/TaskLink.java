package com.assignments.projectmanagement.model;

import java.util.Date;

public class TaskLink {
    Integer id;
    Integer taskId;
    Integer userId;
    LinkType linkType;
    Date createdAt;
    Date deletedAt;

    public enum LinkType {
        PARENT_OF, CHILD_OF, BLOCKED_BY, BLOCKS, RELATES_TO, RELEASES, RELEASED_BY
    }
}
