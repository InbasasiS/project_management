package com.assignments.projectmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

// This refers the association between 2 different tasks

@Entity
@Data
@Table(name = "task_links")
public class TaskLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    Task task;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "linked_task_id", nullable = false)
    @JsonIgnore
    Task linkedTask;

    @Column(name = "link_type")
    LinkType linkType = LinkType.RELATES_TO;

    public enum LinkType {
        PARENT_OF, CHILD_OF, BLOCKED_BY, BLOCKS, RELATES_TO, RELEASES, RELEASED_BY
    }
}
