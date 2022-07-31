package com.assignments.projectmanagement.model;

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

import lombok.Data;

// Currently, The user belongs to a board (Need to move them to a different model Team)
// The User can have multiple tasks & comments

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "role")
    Role role;

    @Column(name = "status")
    UserStatus status = UserStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "board_id")
    Board board;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Task> tasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> comments;

    public enum UserStatus {
        ACTIVE, INACTIVE;
    }

    public enum Role {
        DEV, QA, MANAGER, ADMIN;
    }

}
